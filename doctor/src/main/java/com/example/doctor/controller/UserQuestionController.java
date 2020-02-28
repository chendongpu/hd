package com.example.doctor.controller;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.example.doctor.controller.request.NewUserQuestionRequest;
import com.example.doctor.controller.response.UserQuestionResponse;
import com.example.doctor.handler.BizException;
import com.example.doctor.handler.ResultBody;
import com.example.doctor.jwt.CheckToken;
import com.example.doctor.model.UserQuestion;
import com.example.doctor.model.UserQuestionChoice;
import com.example.doctor.service.UserQuestionChoiceService;
import com.example.doctor.service.UserQuestionService;
import com.example.doctor.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/user_question")
public class UserQuestionController {

    @Autowired
    private UserQuestionService userQuestionService;


    @Autowired
    private UserService userService;

    @Autowired
    private UserQuestionChoiceService userQuestionChoiceService;

    @Autowired
    HttpServletRequest request;

    //添加问题
    //{"title":"你选择网购的主要原因?","type":0,"score":10,"userQuestionChoices":[{"choice":"方便快捷"},{"choice":"品类齐全"},{"choice":"价格便宜"}]}
    @CheckToken
    @PostMapping("/create_user_question")
    public ResultBody createUserQuestion( @Valid @RequestBody NewUserQuestionRequest newUserQuestionReq) {
        Optional<UserQuestion> userQuestionChoice=userQuestionService.findByTitle(newUserQuestionReq.getTitle());
        if (userQuestionChoice.isPresent()) {
            throw new BizException("-1","问题已存在");
        }

        log.info("newUserQuestionReq{}",newUserQuestionReq);
        UserQuestion newUserQuestion=new UserQuestion();
        String token = request.getHeader("token");
        // 获取 token 中的 user id
        log.info("token:{}",token);
        String userId= JWT.decode(token).getClaim("id").asString();
        Long userid =Long.parseLong(userId);
        newUserQuestion.setUserid(userid);
        newUserQuestion.setTitle(newUserQuestionReq.getTitle());
        newUserQuestion.setType(newUserQuestionReq.getType());
        newUserQuestion.setScore(newUserQuestionReq.getScore());
        UserQuestion saved= userQuestionService.createUserQuestion(newUserQuestion);

        log.info("newUserQuestion{}",newUserQuestion);
        List<UserQuestionChoice> choices = newUserQuestionReq.getUserQuestionChoices();
        for(UserQuestionChoice choice:choices){
            choice.setQuestionid(saved.getId());
        }
        log.info("choices{}",choices);
        userQuestionChoiceService.createBatchUserQuestionChoice(choices);

        return ResultBody.success("保存成功");
    }

    private UserQuestionResponse userQuestionCastToUserQuestionResponse(UserQuestion  uq){
        UserQuestionResponse uqr =new UserQuestionResponse();
        uqr.setId(uq.getId());
        uqr.setUserid(uq.getUserid());
        uqr.setTitle(uq.getTitle());
        uqr.setType(uq.getType());
        uqr.setScore(uq.getScore());
        uqr.setCreatetime(uq.getCreatetime());
        List<UserQuestionChoice> list= userQuestionChoiceService.findByQuestionid(uq.getId());
        uqr.setUserQuestionChoices(list);
        return uqr;
    }


    //删除问题
    @CheckToken
    @GetMapping(value = "/remove_user_question",params = "id")
    public ResultBody removeUserQuestion(@RequestParam Long id) {
        Optional<UserQuestion> userQuestion=userQuestionService.findUserQuestionById(id);
        if(!userQuestion.isPresent()){
            throw new BizException("-1","问题不存在");
        }
        List<UserQuestionChoice> list = userQuestionChoiceService.findByQuestionid(userQuestion.get().getId());
        userQuestionChoiceService.removeBatchUserQuestionChoice(list);
        userQuestionService.removeUserQuestion(id);
        return ResultBody.success("问题删除成功");
    }




    //查询问题
    @CheckToken
    @PostMapping(value = "/all_user_question",params = "limit")
    public ResultBody allUserQuestion(Integer limit,Integer page){

        if (null == page || 0 == page){
            page = 1;
        }
        // 排序方式，这里是以“id”为标准进行降序
        Sort sort = Sort.by(Sort.Direction.DESC, "id");  // 这里的"id"是实体类的主键，记住一定要是实体类的属性，而不能是数据库的字段
        Pageable pageable = PageRequest.of(page - 1,limit,sort);
        String token = request.getHeader("token");
        // 获取 token 中的 user id
        log.info("token:{}",token);
        String userId= JWT.decode(token).getClaim("id").asString();
        Long userid =Long.parseLong(userId);
        UserQuestion userQuestion = UserQuestion.builder().userid(userid).build();
        JSONObject jsonObject = new JSONObject();
        Page<UserQuestion> pageQuestion = userQuestionService.allUserQuestion(userQuestion,pageable);

        List<UserQuestion> list1=pageQuestion.getContent();


        List<UserQuestionResponse> list2=new ArrayList<>();
        for(UserQuestion uq: list1){
            list2.add(this.userQuestionCastToUserQuestionResponse(uq));
        }

        jsonObject.put("list2", list2);
        return ResultBody.success(jsonObject);

    }

    @CheckToken
    @GetMapping(value = "/one_user_question",params = "id")
    public ResultBody oneUserQuestion(@RequestParam Long id){
        if(null==id|| 0==id){
            throw new BizException("-1","问题id不能为空");
        }

        Optional<UserQuestion> userQuestionChoice=userQuestionService.findUserQuestionById(id);
        if(!userQuestionChoice.isPresent()){
            throw new BizException("-1","问题不存在");
        }
        UserQuestionResponse uqr = this.userQuestionCastToUserQuestionResponse(userQuestionChoice.get());

        return ResultBody.success(uqr);
    }




}

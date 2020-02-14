package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.example.demo.controller.response.UserQuestionResponse;
import com.example.demo.controller.response.UserTestQuestionResponse;
import com.example.demo.handler.BizException;
import com.example.demo.handler.ResultBody;
import com.example.demo.jwt.CheckToken;
import com.example.demo.model.*;
import com.example.demo.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/user_test_question")
public class UserTestQuestionController {

    @Autowired
    private UserTestQuestionService userTestQuestionService;

    @Autowired
    private UserTestService userTestService;

    @Autowired
    private UserQuestionService userQuestionService;

    @Autowired
    private UserQuestionChoiceService userQuestionChoiceService;

    @Autowired
    HttpServletRequest request;

    //添加用户测评问题
    //questionids 传入1,2,3
    @CheckToken
    @PostMapping("/create_user_test_question")
    public ResultBody createUserTestQuestion( @RequestParam Long testid, @RequestParam Long[] questionids) {
        JSONObject jsonObject = new JSONObject();
        Optional<UserTest> userTestOption=userTestService.findUserTestById(testid);
        if(!userTestOption.isPresent()){
            throw new BizException("-1","该测评不存在");
        }



        String token = request.getHeader("token");
        // 获取 token 中的 user id
        log.info("token:{}",token);
        String userId= JWT.decode(token).getClaim("id").asString();
        Long userid =Long.parseLong(userId);

        List<UserTestQuestion> userTestQuestionList=new ArrayList<>();

        for(Long questionid:questionids){

            Optional<UserQuestion> userQuestionOption=userQuestionService.findUserQuestionById(Long.parseLong(questionid+""));
            if(!userQuestionOption.isPresent()){
                throw new BizException("-1","该测评不存在");
            }

            if(userid != userQuestionOption.get().getUserid()){
                throw new RuntimeException("越权访问");
            }

            UserTestQuestion userTestQuestion=new UserTestQuestion();
            userTestQuestion.setTestid(testid);
            userTestQuestion.setQuestionid(questionid);

            userTestQuestionList.add(userTestQuestion);
        }

        List<UserTestQuestion> saved= userTestQuestionService.createBatchUserTestQuestion(userTestQuestionList);
        return ResultBody.success(saved) ;
    }

    //删除用户测评问题
    @CheckToken
    @PostMapping("/remove_user_test_question")
    public ResultBody removeUserTestQuestion(@RequestParam Long testid,@RequestParam Long questionid){
        String token = request.getHeader("token");
        // 获取 token 中的 user id
        log.info("token:{}",token);
        String userId= JWT.decode(token).getClaim("id").asString();
        Long userid =Long.parseLong(userId);


        Optional<UserTestQuestion> userTestQuestionOption=userTestQuestionService.findOneUserTestQuestion(testid,questionid);
        if(!userTestQuestionOption.isPresent()){
            throw new BizException("-1","该测评没有此问题");
        }
        userTestQuestionService.removeUserTestQuestion(userTestQuestionOption.get());
        return ResultBody.success("删除测评问题成功") ;
    }

    private UserQuestionResponse userQuestionCastToUserQuestionResponse(UserQuestion  uq){
        log.info("uq:{}",uq);
        UserQuestionResponse uqr =new UserQuestionResponse();
        uqr.setId(uq.getId());
        uqr.setUserid(uq.getUserid());
        uqr.setTitle(uq.getTitle());
        uqr.setType(uq.getType());
        uqr.setScore(uq.getScore());
        uqr.setCreatetime(uq.getCreatetime());
        uqr.setUser(uq.getUser());
        log.info("id:{}",uq.getId());
        log.info("list:{}",userQuestionChoiceService.findByQuestionid(uq.getId()));
        List<UserQuestionChoice> list= userQuestionChoiceService.findByQuestionid(uq.getId());
        uqr.setUserQuestionChoices(list);
        return uqr;
    }

    //测评问题列表
    @CheckToken
    @PostMapping(value = "/one_user_test_question",params = "testid")
    public ResultBody oneUserTestQuestion(@NotNull  Long testid){

        String token = request.getHeader("token");
        // 获取 token 中的 user id
        log.info("token:{}",token);
        String userId= JWT.decode(token).getClaim("id").asString();
        Long userid =Long.parseLong(userId);

        UserTestQuestionResponse utqr =new UserTestQuestionResponse();

        Optional<UserTest> userTestOptional = userTestService.findUserTestById(testid);

        List<UserQuestion> userQuestionList = userTestQuestionService.findOneUserTestQuestionList(testid);

        log.info("userQuestionList:{}",userQuestionList);

        utqr.setUsertest(userTestOptional.get());

        List<UserQuestionResponse> userQuestionResponseList=new ArrayList<>();

        for (UserQuestion uq:userQuestionList){
            userQuestionResponseList.add(this.userQuestionCastToUserQuestionResponse(uq));
        }

        log.info("userQuestionResponseList:{}",userQuestionResponseList);
        utqr.setUserQuestionResponses(userQuestionResponseList);

        return ResultBody.success(utqr);
    }


}

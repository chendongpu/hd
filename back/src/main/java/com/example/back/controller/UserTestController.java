package com.example.back.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.back.controller.request.NewUserTestRequest;
import com.example.back.controller.response.UserQuestionResponse;
import com.example.back.controller.response.UserTestQuestionResponse;
import com.example.back.handler.BizException;
import com.example.back.handler.ResultBody;
import com.example.back.jwt.CheckToken;
import com.example.back.model.UserQuestion;
import com.example.back.model.UserQuestionChoice;
import com.example.back.model.UserTest;
import com.example.back.service.UserQuestionChoiceService;
import com.example.back.service.UserTestQuestionService;
import com.example.back.service.UserTestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

@Api(value = "/user_test", tags = "用户测评接口")
@Slf4j
@RestController
@RequestMapping("/user_test")
public class UserTestController {

    @Autowired
    private UserTestService userTestService;

    @Autowired
    private UserTestQuestionService userTestQuestionService;

    @Autowired
    private UserQuestionChoiceService userQuestionChoiceService;




    @ApiOperation(value = "删除用户测评", notes = "传入用户测评id")
    @CheckToken
    @GetMapping(value = "/remove_user_test",params = "id")
    public ResultBody removeUserTest(@RequestParam  Long id) {
        Optional<UserTest> userTestOption=userTestService.findUserTestById(id);
        if(!userTestOption.isPresent()){
            throw new BizException("-1","用户测评不存在");
        }

        userTestService.removeUserTest(id);
        return ResultBody.success("用户测评删除成功");
    }



    //{"title":"测评标题xxx","content":"测评介绍xxx","id":5}
    @ApiOperation(value = "修改用户测评", notes = "传入新的用户测评json")
    @CheckToken
    @PostMapping(value = "/update_user_test")
    public ResultBody updateUserTest(@Valid @RequestBody NewUserTestRequest newUserTestReq){

        if(null==newUserTestReq.getId() || 0==newUserTestReq.getId() ){
            throw new BizException("-1","用户测评id不能为空");
        }

        Optional<UserTest> userTestOption=userTestService.findUserTestById(newUserTestReq.getId());
        if(!userTestOption.isPresent()){
            throw new BizException("-1","用户测评不存在");
        }

        UserTest userTestBase = userTestOption.get();
        userTestBase.setTitle(newUserTestReq.getTitle());
        userTestBase.setContent(newUserTestReq.getContent());
        userTestService.updateUserTest(userTestBase);
        return ResultBody.success("用户测评修改成功");
    }

    @Autowired
    HttpServletRequest request;

    private UserQuestionResponse userQuestionCastToUserQuestionResponse(UserQuestion  uq){
        log.info("uq:{}",uq);
        UserQuestionResponse uqr =new UserQuestionResponse();
        uqr.setId(uq.getId());
        uqr.setUserid(uq.getUserid());
        uqr.setTitle(uq.getTitle());
        uqr.setType(uq.getType());
        uqr.setScore(uq.getScore());
        uqr.setCreatetime(uq.getCreatetime());
        log.info("id:{}",uq.getId());
        log.info("list:{}",userQuestionChoiceService.findByQuestionid(uq.getId()));
        List<UserQuestionChoice> list= userQuestionChoiceService.findByQuestionid(uq.getId());
        uqr.setUserQuestionChoices(list);
        return uqr;
    }


    @ApiOperation(value = "查询用户测评", notes = "limit表示每次查几条 page表示第几页")
    @CheckToken
    @PostMapping(value = "/all_user_test",params = "limit")
    public ResultBody allUserTest(Integer limit,Integer page){

        if (null == page || 0 == page){
            page = 1;
        }
        // 排序方式，这里是以“id”为标准进行降序
        Sort sort = Sort.by(Sort.Direction.DESC, "id");  // 这里的"id"是实体类的主键，记住一定要是实体类的属性，而不能是数据库的字段
        Pageable pageable = PageRequest.of(page - 1,limit,sort);

        UserTest userTest = UserTest.builder().build();
        JSONObject jsonObject = new JSONObject();
        Page<UserTest> pageTest = userTestService.allUserTest(userTest,pageable);

        List<UserTest> userTestList = pageTest.getContent();

        List<UserTestQuestionResponse> utqrl=new ArrayList<>();

        for(UserTest ut : userTestList){
            UserTestQuestionResponse utqr =new UserTestQuestionResponse();

            utqr.setUsertest(ut);

            List<UserQuestion> userQuestionList = userTestQuestionService.findOneUserTestQuestionList(ut.getId());

            List<UserQuestionResponse> userQuestionResponseList=new ArrayList<>();

            for (UserQuestion uq:userQuestionList){
                userQuestionResponseList.add(this.userQuestionCastToUserQuestionResponse(uq));
            }

            utqr.setUserQuestionResponses(userQuestionResponseList);
            utqrl.add(utqr);
        }



        jsonObject.put("list", utqrl);
        return ResultBody.success(jsonObject);

    }



    @ApiOperation(value = "用户测评详情", notes = "传入测评id")
    @CheckToken
    @GetMapping(value = "/one_user_test",params = "id")
    public ResultBody oneUserTest(@RequestParam Long id){
        if(null==id|| 0==id){
            throw new BizException("-1","测评id不能为空");
        }

        Optional<UserTest> userTestOption=userTestService.findUserTestById(id);
        if(!userTestOption.isPresent()){
            throw new BizException("-1","测评不存在");
        }

        Long testid=userTestOption.get().getId();

        List<UserQuestion> userQuestionList = userTestQuestionService.findOneUserTestQuestionList(testid);

        UserTestQuestionResponse utqr =new UserTestQuestionResponse();

        utqr.setUsertest(userTestOption.get());

        List<UserQuestionResponse> userQuestionResponseList=new ArrayList<>();

        for (UserQuestion uq:userQuestionList){
            userQuestionResponseList.add(this.userQuestionCastToUserQuestionResponse(uq));
        }

        log.info("userQuestionResponseList:{}",userQuestionResponseList);
        utqr.setUserQuestionResponses(userQuestionResponseList);


        return ResultBody.success(utqr);
    }




}

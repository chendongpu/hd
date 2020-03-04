package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.controller.response.UserQuestionResponse;
import com.example.demo.controller.response.UserTestQuestionResponse;
import com.example.demo.handler.BizException;
import com.example.demo.handler.ResultBody;
import com.example.demo.model.UserQuestion;
import com.example.demo.model.UserQuestionChoice;
import com.example.demo.model.UserTest;
import com.example.demo.service.UserQuestionChoiceService;
import com.example.demo.service.UserTestQuestionService;
import com.example.demo.service.UserTestService;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Api(value = "/test", tags = "前台测评接口")
@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private UserTestService userTestService;

    @Autowired
    private UserTestQuestionService userTestQuestionService;

    @Autowired
    private UserQuestionChoiceService userQuestionChoiceService;

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


    @ApiOperation(value = "查询测评", notes = "limit表示每次查几条 page表示第几页")
    @PostMapping(value = "/all_test",params = "limit")
    public ResultBody allTest(Integer limit,Integer page){

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



    @ApiOperation(value = "测评详情", notes = "传入测评id")
    @GetMapping(value = "/one_test",params = "id")
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

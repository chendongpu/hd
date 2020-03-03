package com.example.doctor.controller;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.example.doctor.controller.response.UserQuestionAnswerResponse;
import com.example.doctor.controller.response.UserQuestionResponse;
import com.example.doctor.handler.BizException;
import com.example.doctor.handler.ResultBody;
import com.example.doctor.jwt.CheckToken;
import com.example.doctor.model.*;
import com.example.doctor.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Api(value = "/user_question_answer", tags = "测评问题答案接口")
@Transactional
@Slf4j
@RestController
@RequestMapping("/user_question_answer")
public class UserQuestionAnswerController {

    @Autowired
    private UserQuestionAnswerService userQuestionAnswerService;


    @Autowired
    private UserQuestionService userQuestionService;

    @Autowired
    private UserQuestionChoiceService userQuestionChoiceService;

    @Autowired
    HttpServletRequest request;


    @ApiOperation(value = "添加用户测评问题答案", notes = "questionid 为问题id， choiceids为正确选项的id可以是多个，多个用逗号分隔")
    //questionid 1
    //choiceids 传入1,2,3
    @CheckToken
    @PostMapping("/create_user_question_answer")
    public ResultBody createUserQuestionAnswer(@RequestParam Long questionid, @RequestParam Long[] choiceids) {
        JSONObject jsonObject = new JSONObject();
        Optional<UserQuestion> userQuestionOption=userQuestionService.findUserQuestionById(questionid);
        if(!userQuestionOption.isPresent()){
            throw new BizException("-1","该问题不存在");
        }



        String token = request.getHeader("token");
        // 获取 token 中的 user id
        log.info("token:{}",token);
        String userId= JWT.decode(token).getClaim("id").asString();
        Long userid =Long.parseLong(userId);

        List<UserTestQuestion> userTestQuestionList=new ArrayList<>();

        for(Long choiceid:choiceids){

            Optional<UserQuestionChoice> userQuestionChoiceOptional=userQuestionChoiceService.findUserQuestionChoiceById(choiceid);
            if(!userQuestionChoiceOptional.isPresent()){
                throw new BizException("-1","该问题选项不存在");
            }

            UserQuestionAnswer userQuestionAnswer=new UserQuestionAnswer();
            userQuestionAnswer.setQuestionid(questionid);
            userQuestionAnswer.setChoiceid(choiceid);

            userQuestionAnswerService.createQuestionAnswer(userQuestionAnswer);
        }
        return ResultBody.success("添加问题答案成功") ;
    }


    @ApiOperation(value = "删除问题答案", notes = "questionid 为问题id， choiceid为答案选项，一个选项一个选项删除")
    @CheckToken
    @PostMapping("/remove_user_question_answer")
    public ResultBody removeUserQuestionAnswer(@RequestParam Long questionid, @RequestParam Long choiceid){
        String token = request.getHeader("token");
        // 获取 token 中的 user id
        log.info("token:{}",token);
        String userId= JWT.decode(token).getClaim("id").asString();
        Long userid =Long.parseLong(userId);


        Optional<UserQuestionAnswer> userQuestionAnswerOptional=userQuestionAnswerService.findByQuestionid(questionid,choiceid);
        if(!userQuestionAnswerOptional.isPresent()){
            throw new BizException("-1","问题没有此答案");
        }
        userQuestionAnswerService.removeQuestionAnswer(userQuestionAnswerOptional.get());
        return ResultBody.success("删除问题答案成功") ;
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
        log.info("id:{}",uq.getId());
        log.info("list:{}",userQuestionChoiceService.findByQuestionid(uq.getId()));
        List<UserQuestionChoice> list= userQuestionChoiceService.findByQuestionid(uq.getId());
        uqr.setUserQuestionChoices(list);
        return uqr;
    }

    @ApiOperation(value = "查询测评问题答案", notes = "questionid 为问题id")
    @CheckToken
    @PostMapping(value = "/one_user_question_answer",params = "questionid")
    public ResultBody oneUserQuestionAnswer(@NotNull Long questionid){

        String token = request.getHeader("token");
        // 获取 token 中的 user id
        log.info("token:{}",token);
        String userId= JWT.decode(token).getClaim("id").asString();
        Long userid =Long.parseLong(userId);

        UserQuestionAnswerResponse uqar =new UserQuestionAnswerResponse();

        Optional<UserQuestion> userQuestionOptional = userQuestionService.findUserQuestionById(questionid);

        List<UserQuestionChoice> userQuestionChoiceList = userQuestionChoiceService.findByQuestionid(questionid);

        List<UserQuestionAnswer> userQuestionAnswerList=userQuestionAnswerService.findByQuestionid(questionid);


        List<UserQuestionChoice> userQuestionChoiceList2=new ArrayList<>();
        if(userQuestionAnswerList.size()>0){
            for (UserQuestionAnswer uqa:userQuestionAnswerList){
                UserQuestionChoice uqc = userQuestionChoiceService.findUserQuestionChoiceById(uqa.getChoiceid()).get();
                userQuestionChoiceList2.add(uqc);
            }

        }

        uqar.setUserQuestion(userQuestionOptional.get());

        uqar.setUserQuestionChoices(userQuestionChoiceList);

        uqar.setUserQuestionAnswer(userQuestionChoiceList2);

        return ResultBody.success(uqar);
    }


}

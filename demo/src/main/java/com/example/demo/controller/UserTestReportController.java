package com.example.demo.controller;

import com.auth0.jwt.JWT;
import com.example.demo.controller.request.NewUserTestReportRequest;
import com.example.demo.handler.BizException;
import com.example.demo.handler.ResultBody;
import com.example.demo.jwt.CheckToken;
import com.example.demo.model.UserTest;
import com.example.demo.model.UserTestReport;
import com.example.demo.service.UserService;
import com.example.demo.service.UserTestReportService;
import com.example.demo.service.UserTestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/user_test_report")
public class UserTestReportController {

    @Autowired
    private UserTestReportService userTestReportService;

    @Autowired
    private UserTestService userTestService;

    @Autowired
    private UserService userService;

    @Autowired
    HttpServletRequest request;

    //添加测评报告
    //{"testid":1,"userTestReportList":[{"score":30,"title":"很差","content":"需要锻炼加强营养"},{"score":50,"title":"一般","content":"需要锻炼",{"score":70,"title":"很棒","content":"需要继续保持"}}]}
    @CheckToken
    @PostMapping("/create_user_test_report")
    public ResultBody createUserTestReport( @Valid @RequestBody NewUserTestReportRequest newUserTestReportReq) {
        Optional<UserTest> userTestOption=userTestService.findUserTestById(newUserTestReportReq.getTestid());
        if (!userTestOption.isPresent()) {
            throw new BizException("-1","测评不存在");
        }

        log.info("Receive new UserTestReport{}",newUserTestReportReq);
        UserTestReport newUserTestReport=new UserTestReport();
        String token = request.getHeader("token");
        // 获取 token 中的 user id
        log.info("token:{}",token);
        String userId= JWT.decode(token).getClaim("id").asString();
        Long userid =Long.parseLong(userId);

        if (userTestOption.get().getUserid()!=userid) {
            throw new BizException("-1","测评和用户不匹配");
        }
        List<UserTestReport> userTestReportListReq = newUserTestReportReq.getUserTestReportList();
        if(!(userTestReportListReq.size()>0)){
            throw new BizException("-1","测评报告不能为空");
        }
        List<UserTestReport> userTestReportList=new ArrayList<>();
        for(UserTestReport utr :userTestReportListReq){
            utr.setTestid(newUserTestReportReq.getTestid());
            userTestReportList.add(utr);
        }
        List<UserTestReport> saved= userTestReportService.createBatchUserTestReport(userTestReportList);
        return ResultBody.success(saved);
    }


    //修改测评报告
    //{"testid":1,"userTestReportList":[{"score":30,"title":"很差","content":"需要锻炼加强营养xxx"},{"score":50,"title":"一般","content":"需要锻炼xxx",{"score":70,"title":"很棒","content":"需要继续保持xxx"}}]}
    @CheckToken
    @PostMapping(value = "/update_user_test_report")
    public ResultBody updateUserTestReport(@Valid @RequestBody NewUserTestReportRequest newUserTestReportReq){
        Optional<UserTest> userTestOption=userTestService.findUserTestById(newUserTestReportReq.getTestid());
        if (!userTestOption.isPresent()) {
            throw new BizException("-1","测评不存在");
        }

        log.info("Receive new UserTestReport{}",newUserTestReportReq);
        UserTestReport newUserTestReport=new UserTestReport();
        String token = request.getHeader("token");
        // 获取 token 中的 user id
        log.info("token:{}",token);
        String userId= JWT.decode(token).getClaim("id").asString();
        Long userid =Long.parseLong(userId);

        if (userTestOption.get().getUserid()!=userid) {
            throw new BizException("-1","测评和用户不匹配");
        }
        List<UserTestReport> userTestReportListReq = newUserTestReportReq.getUserTestReportList();
        if(!(userTestReportListReq.size()>0)){
            throw new BizException("-1","测评报告不能为空");
        }
        List<UserTestReport> userTestReportList=new ArrayList<>();
        for(UserTestReport utr :userTestReportListReq){
            utr.setTestid(newUserTestReportReq.getTestid());
            userTestReportList.add(utr);
        }
        List<UserTestReport> oldUserTestReportList = userTestReportService.findByTestid(newUserTestReportReq.getTestid());
        userTestReportService.removeBatchUserTestReport(oldUserTestReportList);
        List<UserTestReport> saved= userTestReportService.createBatchUserTestReport(userTestReportList);
        return ResultBody.success("测评报告修改成功");
    }





}

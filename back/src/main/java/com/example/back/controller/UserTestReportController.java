package com.example.back.controller;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.example.back.controller.request.NewUserTestReportRequest;
import com.example.back.controller.response.UserQuestionResponse;
import com.example.back.controller.response.UserTestQuestionResponse;
import com.example.back.controller.response.UserTestReportResponse;
import com.example.back.handler.BizException;
import com.example.back.handler.ResultBody;
import com.example.back.jwt.CheckToken;
import com.example.back.model.UserQuestion;
import com.example.back.model.UserTest;
import com.example.back.model.UserTestLog;
import com.example.back.model.UserTestReport;
import com.example.back.service.UserService;
import com.example.back.service.UserTestReportService;
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

@Api(value = "/user_test_report", tags = "用户测评报告接口")
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


    @ApiOperation(value = "删除测评报告", notes = "传入测评id")
    @CheckToken
    @GetMapping(value = "/remove_user_test_report",params = "id")
    public ResultBody removeUserTestReport(@RequestParam  Long id) {
        List<UserTestReport> userTestReportList = userTestReportService.findByTestid(id);
        userTestReportService.removeBatchUserTestReport(userTestReportList);
        return ResultBody.success("测评报告删除成功");
    }



    @ApiOperation(value = "修改测评报告", notes = "传入新的测评报告json")
    //{"testid":1,"userTestReportList":[{"score":30,"title":"很差","content":"需要锻炼加强营养xxx"},{"score":50,"title":"一般","content":"需要锻炼xxx"},{"score":70,"title":"很棒","content":"需要继续保持xxx"}]}
    @CheckToken
    @PostMapping(value = "/update_user_test_report")
    public ResultBody updateUserTestReport(@Valid @RequestBody NewUserTestReportRequest newUserTestReportReq){
        Optional<UserTest> userTestOption=userTestService.findUserTestById(newUserTestReportReq.getTestid());
        if (!userTestOption.isPresent()) {
            throw new BizException("-1","测评不存在");
        }

        log.info("Receive new UserTestReport{}",newUserTestReportReq);
        UserTestReport newUserTestReport=new UserTestReport();

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



    @ApiOperation(value = "查询用户测评报告列表", notes = "limit表示每次查几条 page表示第几页")
    @CheckToken
    @PostMapping(value = "/all_user_test_report",params = "limit")
    public ResultBody allUserTestReport(Integer limit,Integer page){

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

        List<UserTestReportResponse> utrrl=new ArrayList<>();

        for(UserTest ut : userTestList){

            UserTestReportResponse utrr=new UserTestReportResponse();

            utrr.setUsertest(ut);

            List<UserTestReport> userTestReportList = userTestReportService.findByTestid(ut.getId());

            utrr.setUserTestReports(userTestReportList);

            utrrl.add(utrr);
        }
        jsonObject.put("list", utrrl);
        return ResultBody.success(jsonObject);

    }






}

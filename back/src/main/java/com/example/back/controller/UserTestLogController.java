package com.example.back.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.back.handler.BizException;
import com.example.back.handler.ResultBody;
import com.example.back.jwt.CheckToken;
import com.example.back.model.*;
import com.example.back.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@Api(value = "/user_test_log", tags = "用户测评记录接口")
@Slf4j
@RestController
@RequestMapping("/user_test_log")
public class UserTestLogController {



    @Autowired
    private UserTestLogService userTestLogService;

    @ApiOperation(value = "删除测评记录", notes = "传入测评记录id")
    @CheckToken
    @GetMapping(value = "/remove_user_test_log",params = "id")
    public ResultBody removeUserTestLog(@RequestParam  Long id) {
        Optional<UserTestLog> userTestOption=userTestLogService.findUserTestLogById(id);
        if(!userTestOption.isPresent()){
            throw new BizException("-1","测评记录不存在");
        }

        userTestLogService.removeUserTestLog(id);
        return ResultBody.success("测评记录删除成功");
    }





    @ApiOperation(value = "查询测评记录", notes = "limit表示每次查几条 page表示第几页")
    @CheckToken
    @PostMapping(value = "/all_user_test_log",params = "limit")
    public ResultBody allUserTestLog(Integer limit,Integer page){

        if (null == page || 0 == page){
            page = 1;
        }
        // 排序方式，这里是以“id”为标准进行降序
        Sort sort = Sort.by(Sort.Direction.DESC, "id");  // 这里的"id"是实体类的主键，记住一定要是实体类的属性，而不能是数据库的字段
        Pageable pageable = PageRequest.of(page - 1,limit,sort);

        UserTestLog userTestLog = UserTestLog.builder().build();
        JSONObject jsonObject = new JSONObject();
        Page<UserTestLog> pageTest = userTestLogService.allUserTestLog(userTestLog,pageable);
        jsonObject.put("list", pageTest.getContent());
        return ResultBody.success(jsonObject);

    }

    @ApiOperation(value = "查询测评记录", notes = "limit表示每次查几条 page表示第几页")
    @CheckToken
    @GetMapping(value = "/one_user_test_log",params = "id")
    public ResultBody oneUserTestLog(@RequestParam Long id){
        if(null==id|| 0==id){
            throw new BizException("-1","测评记录id不能为空");
        }

        Optional<UserTestLog> userTestLogOption=userTestLogService.findUserTestLogById(id);
        if(!userTestLogOption.isPresent()){
            throw new BizException("-1","测评记录不存在");
        }


        return ResultBody.success(userTestLogOption.get());
    }




}




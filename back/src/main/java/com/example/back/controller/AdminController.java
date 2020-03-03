package com.example.back.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.back.jwt.LoginToken;
import com.example.back.service.AdminService;
import com.example.back.handler.BizException;
import com.example.back.handler.ResultBody;
import com.example.back.jwt.CheckToken;
import com.example.back.jwt.JwtUtil;
import com.example.back.model.Admin;
import com.example.back.util.MD5Utils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.Optional;

@Api(value = "/admin", tags = "管理员接口")
@Slf4j
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;


    @ApiOperation(value = "管理员登录", notes = "管理员用户名密码登录")
    //{"username":"admin","password":"123456"}
    @PostMapping("/login")
    @LoginToken
    public ResultBody login(@RequestBody @Valid Admin admin) {
        JSONObject jsonObject = new JSONObject();
        Optional<Admin> adminOptional=null;
        if(null!=admin.getUsername() && !"".equals(admin.getUsername())){
            adminOptional = (adminService.findByUsername(admin.getUsername()));
        }


        log.info("adminOptional:{}",adminOptional);
        log.info("admin:{}",admin);

        if (!adminOptional.isPresent()) {
            throw new BizException("-1","登录失败,管理员不存在");
        }

        Admin adminForBase = adminOptional.get();

        if (!adminForBase.getPassword().equals(MD5Utils.stringToMD5(admin.getPassword()))) {
            throw new BizException("-1","登录失败,密码错误");
        }

        String token = JwtUtil.createJWT(60000000, adminForBase);
        jsonObject.put("token", token);
        jsonObject.put("user", adminForBase);
        return ResultBody.success(jsonObject) ;


    }


    @ApiOperation(value = "检查管理员登录", notes = "没有实际作用")
    @CheckToken
    @GetMapping("/check_login")
    public ResultBody checkLogin() {
        return ResultBody.success("你已通过验证");
    }



}

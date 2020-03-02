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
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    //登录
    //{"admin":"admin","password":"123456"}
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

    //查看管理员信息
    @CheckToken
    @GetMapping("/getMessage")
    public ResultBody getMessage() {
        return ResultBody.success("你已通过验证");
    }



}

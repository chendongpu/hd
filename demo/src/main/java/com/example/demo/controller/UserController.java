package com.example.demo.controller;

import com.example.demo.controller.request.NewUserRequest;
import com.example.demo.handler.BizException;
import com.example.demo.handler.ResultBody;
import com.example.demo.jwt.CheckToken;
import com.example.demo.jwt.JwtUtil;
import com.example.demo.jwt.LoginToken;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.util.MD5Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    //登录
    @PostMapping("/login")
    @LoginToken
    public ResultBody login(@RequestBody @Valid User user) {
        JSONObject jsonObject = new JSONObject();
        Optional<User> userOptional = (userService.findByUsername(user.getUsername()));
        log.info("userOptional:{}",userOptional);

        if (!userOptional.isPresent()) {
            throw new BizException("-1","登录失败,用户不存在");
        }

        User userForBase = userOptional.get();

        if (!userForBase.getPassword().equals(MD5Utils.stringToMD5(user.getPassword()))) {
            throw new BizException("-1","登录失败,密码错误");
        }

        String token = JwtUtil.createJWT(60000000, userForBase);
        jsonObject.put("token", token);
        jsonObject.put("user", userForBase);
        return ResultBody.success(jsonObject) ;


    }

    //查看个人信息
    @CheckToken
    @GetMapping("/getMessage")
    public ResultBody getMessage() {
        return ResultBody.success("你已通过验证");
    }

    //注册
    @PostMapping("/register")
    public ResultBody register(  @Valid @RequestBody NewUserRequest newUser){
        JSONObject jsonObject = new JSONObject();
        Optional<User> userOptional = userService.findByUsername(newUser.getUsername());
        if (userOptional.isPresent()) {
            throw new BizException("-1","用户名已存在");
        }
        log.info("Receive new User{}",newUser);
        return ResultBody.success(userService.createUser(newUser.getUsername(),newUser.getPassword())) ;
    }




}

package com.example.demo.controller;

import com.example.demo.controller.request.NewUserRequest;
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
    public Object login(@RequestBody @Valid User user) {

        JSONObject jsonObject = new JSONObject();
        User userForBase = (userService.findByUsername(user.getUsername())).get();
        log.info("userForBase:{}",userForBase);

        if (userForBase == null) {
            jsonObject.put("message", "登录失败,用户不存在");
            return jsonObject;
        } else {
            if (!userForBase.getPassword().equals(MD5Utils.stringToMD5(user.getPassword()))) {
                jsonObject.put("message", "登录失败,密码错误");
                return jsonObject;
            } else {
                String token = JwtUtil.createJWT(60000000, userForBase);
                jsonObject.put("token", token);
                jsonObject.put("user", userForBase);
                return jsonObject;
            }
        }
    }

    //查看个人信息
    @CheckToken
    @GetMapping("/getMessage")
    public String getMessage() {
        return "你已通过验证";
    }

    //注册
    @PostMapping("/register")
    public Object register(  @Valid @RequestBody NewUserRequest newUser){
        JSONObject jsonObject = new JSONObject();
        Optional<User> userOptional = userService.findByUsername(newUser.getUsername());
        if (userOptional.isPresent()) {
            jsonObject.put("message", "用户名已存在");
            return jsonObject;
        } else {
            log.info("Receive new User{}",newUser);
            return userService.createUser(newUser.getUsername(),newUser.getPassword());
        }

    }




}

package com.example.demo.controller;

import com.auth0.jwt.JWT;
import com.example.demo.controller.request.NewUserRequest;
import com.example.demo.handler.BizException;
import com.example.demo.handler.ResultBody;
import com.example.demo.jwt.CheckToken;
import com.example.demo.jwt.JwtUtil;
import com.example.demo.jwt.LoginToken;
import com.example.demo.model.User;
import com.example.demo.model.UserAddress;
import com.example.demo.service.UserService;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.util.FileUtil;
import com.example.demo.util.MD5Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.swing.text.html.Option;
import javax.validation.Valid;
import javax.validation.constraints.Null;
import java.io.File;
import java.util.Optional;
import java.util.UUID;

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


    @Autowired
    HttpServletRequest request;

    @CheckToken
    @PostMapping("/set")
    public ResultBody set(@Nullable @RequestParam("avatar") String avatar, @Nullable @RequestParam("nickname") String nickname,@Nullable  @RequestParam("content") String content){

        String token = request.getHeader("token");
        // 获取 token 中的 user id
        log.info("token:{}",token);
        String userId= JWT.decode(token).getClaim("id").asString();
        Long userid =Long.parseLong(userId);

        Optional<User> userOptional = userService.findUserById(userid);

        User newuser = userOptional.get();

        if(!(null == avatar)){
            newuser.setAvatar(avatar);
        }

        if(!(null==nickname)){
            newuser.setNickname(nickname);
        }

        if(!(null==content)){
            newuser.setContent(content);
        }

        userService.updateUser(newuser);

        return ResultBody.success(newuser);

    }



}

package com.example.doctor.controller;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.example.doctor.controller.request.NewUserRequest;
import com.example.doctor.handler.BizException;
import com.example.doctor.handler.ResultBody;
import com.example.doctor.jwt.CheckToken;
import com.example.doctor.jwt.JwtUtil;
import com.example.doctor.jwt.LoginToken;
import com.example.doctor.model.User;
import com.example.doctor.service.UserService;
import com.example.doctor.util.MD5Utils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;


@Api(value = "/user", tags = "医生接口")
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    //{"mobile":"15057190660","password":"123456"}
    @ApiOperation(value = "医生登录", notes = "医生用户名或者手机号登录")
    @PostMapping("/login")
    @LoginToken
    public ResultBody login(@RequestBody @Valid User user) {
        JSONObject jsonObject = new JSONObject();
        Optional<User> userOptional=null;
        if(null!=user.getUsername() && !"".equals(user.getUsername())){
            userOptional = (userService.findByUsername(user.getUsername()));
        }

        if(null!=user.getMobile() && !"".equals(user.getMobile())){
            userOptional = (userService.findByMobile(user.getMobile()));
        }


        log.info("userOptional:{}",userOptional);
        log.info("user:{}",user);

        if (!userOptional.isPresent()) {
            throw new BizException("-1","登录失败,用户不存在");
        }

        if (!(userOptional.get().getIsdoctor()==1)) {
            throw new BizException("-1","登录失败,账号为用户账号");
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

    @ApiOperation(value = "检查登录", notes = "没有实际作用")
    @CheckToken
    @GetMapping("/check_login")
    public ResultBody checkLogin() {
        return ResultBody.success("你已通过验证");
    }


    @ApiOperation(value = "医生注册", notes = "医生手机号加密码注册")
    //{"mobile":"15057190640","password":"123456"}
    @PostMapping("/register")
    public ResultBody register(  @Valid @RequestBody NewUserRequest newUser){
        JSONObject jsonObject = new JSONObject();
        Optional<User> userOptional = userService.findByMobile(newUser.getMobile());
        if (userOptional.isPresent()) {
            throw new BizException("-1","用户手机号已存在");
        }
        log.info("Receive new User{}",newUser);
        return ResultBody.success(userService.createUser(newUser.getMobile(),newUser.getPassword())) ;
    }


    @Autowired
    HttpServletRequest request;

    @ApiOperation(value = "医生设置头像、昵称、简介", notes = "每一项都是非必填")
    @CheckToken
    @PostMapping("/set")
    public ResultBody set(@Nullable @RequestParam("avatar") String avatar, @Nullable @RequestParam("nickname") String nickname, @Nullable @RequestParam("content") String content){

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

    @ApiOperation(value = "医生设置个人信息包括性别、省、市、医院、科室、职称、诊金、科室电话", notes = "每一项都是非必填")
    @CheckToken
    @PostMapping("/info")
    public ResultBody info(@Nullable @RequestParam("gender") Integer gender,
                           @Nullable @RequestParam("province") String province,
                           @Nullable @RequestParam("city") String city,
                           @Nullable @RequestParam("hospital") String hospital,
                           @Nullable @RequestParam("department") Long department,
                           @Nullable @RequestParam("level") String level,
                           @Nullable @RequestParam("money") Money money,
                           @Nullable @RequestParam("departmenttel") String departmenttel
    ){

        String token = request.getHeader("token");
        // 获取 token 中的 user id
        log.info("token:{}",token);
        String userId= JWT.decode(token).getClaim("id").asString();
        Long userid =Long.parseLong(userId);

        Optional<User> userOptional = userService.findUserById(userid);

        User newuser = userOptional.get();

        if(!(null == gender)){
            newuser.setGender(gender);
        }

        if(!(null==province)){
            newuser.setProvince(province);
        }

        if(!(null==city)){
            newuser.setCity(city);
        }

        if(!(null==hospital)){
            newuser.setHospital(hospital);
        }

        if(!(null==department)){
            newuser.setDepartment(department);
        }

        if(!(null==level)){
            newuser.setLevel(level);
        }

        if(!(null==money)){
            newuser.setMoney(money);
        }

        if(!(null==departmenttel)){
            newuser.setDepartmenttel(departmenttel);
        }

        userService.updateUser(newuser);

        return ResultBody.success(newuser);

    }





}

package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.example.demo.handler.BizException;
import com.example.demo.handler.ResultBody;
import com.example.demo.jwt.CheckToken;
import com.example.demo.model.User;
import com.example.demo.model.UserAddress;
import com.example.demo.model.UserConcern;
import com.example.demo.service.UserConcernService;
import com.example.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/user_concern")
public class UserConcernController {

    @Autowired
    private UserConcernService userConcernService;
    @Autowired
    private UserService userService;

    @Autowired
    HttpServletRequest request;

    //添加用户关注
    @CheckToken
    @PostMapping("/create_user_concern")
    public ResultBody createUserAddress( @RequestParam Integer concernid) {
        JSONObject jsonObject = new JSONObject();
        Optional<User> concernOption=userService.findUserById(Long.parseLong(concernid+""));
        if(!concernOption.isPresent()){
            throw new BizException("-1","该关注用户不存在");
        }

        String token = request.getHeader("token");
        // 获取 token 中的 user id
        log.info("token:{}",token);
        String userId= JWT.decode(token).getClaim("id").asString();
        Long userid =Long.parseLong(userId);

        UserConcern userConcern=new UserConcern();
        userConcern.setUserid(userid);
        userConcern.setConcernid(Long.parseLong(concernid+""));
        log.info("userConcern{}",userConcern);
        UserConcern saved= userConcernService.createUserConcern(userConcern);
        return ResultBody.success(saved) ;
    }

    //取消用户关注
    @CheckToken
    @PostMapping("/remove_user_concern")
    public ResultBody removeUserConcern(@RequestParam Integer concernid){
        String token = request.getHeader("token");
        // 获取 token 中的 user id
        log.info("token:{}",token);
        String userId= JWT.decode(token).getClaim("id").asString();
        Long userid =Long.parseLong(userId);
        Optional<UserConcern> userConcernOption=userConcernService.findOneUserConcern(userid,Long.parseLong(concernid+""));
        if(!userConcernOption.isPresent()){
            throw new BizException("-1","你还没有关注");
        }
        userConcernService.removeUserConcern(userConcernOption.get());
        return ResultBody.success("取消关注成功") ;
    }

    //关注列表
    @CheckToken
    @PostMapping(value = "/all_user_concern",params = "limit")
    public ResultBody allUserConcern(Integer limit,Integer page){
        if (null == page || 0 == page){
            page = 1;
        }
        // 排序方式，这里是以“id”为标准进行降序
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(page - 1,limit,sort);
        String token = request.getHeader("token");
        // 获取 token 中的 user id
        log.info("token:{}",token);
        String userId= JWT.decode(token).getClaim("id").asString();
        Long userid =Long.parseLong(userId);
        UserConcern userConcern = UserConcern.builder().userid(userid).build();
        JSONObject jsonObject = new JSONObject();
        Page<User> pageUser = userConcernService.allUserConcern(userConcern,pageable);
        jsonObject.put("list", pageUser.toList());

        return ResultBody.success(jsonObject);
    }

    //粉丝列表
    @CheckToken
    @PostMapping(value = "/all_fans",params = "limit")
    public ResultBody allFans(Integer limit,Integer page){
        if (null == page || 0 == page){
            page = 1;
        }
        // 排序方式，这里是以“id”为标准进行降序
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(page - 1,limit,sort);
        String token = request.getHeader("token");
        // 获取 token 中的 user id
        log.info("token:{}",token);
        String userId= JWT.decode(token).getClaim("id").asString();
        Long userid =Long.parseLong(userId);
        UserConcern userConcern = UserConcern.builder().concernid(userid).build();
        JSONObject jsonObject = new JSONObject();
        Page<User> pageUser = userConcernService.allFans(userConcern,pageable);
        jsonObject.put("list", pageUser.toList());
        return ResultBody.success(jsonObject);
    }




}

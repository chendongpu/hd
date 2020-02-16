package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.example.demo.controller.request.NewUserMsgRequest;
import com.example.demo.handler.BizException;
import com.example.demo.handler.ResultBody;
import com.example.demo.jwt.CheckToken;
import com.example.demo.model.UserMsg;
import com.example.demo.service.UserMsgService;
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
@RequestMapping("/user_msg")
public class UserMsgController {

    @Autowired
    private UserMsgService userMsgService;

    @Autowired
    private UserService userService;

    @Autowired
    HttpServletRequest request;

    //添加用户消息
    //{"msg":"密码修改成功","type":0,"otherid":0}
    @CheckToken
    @PostMapping("/create_user_msg")
    public ResultBody createUserMsg( @Valid @RequestBody NewUserMsgRequest newUserMsgReq) {
        log.info("Receive new UserMsg{}",newUserMsgReq);
        UserMsg newUserMsg=new UserMsg();
        String token = request.getHeader("token");
        // 获取 token 中的 user id
        log.info("token:{}",token);
        String userId= JWT.decode(token).getClaim("id").asString();
        Long userid =Long.parseLong(userId);
        newUserMsg.setUserid(userid);
        newUserMsg.setMsg(newUserMsgReq.getMsg());
        newUserMsg.setType(newUserMsgReq.getType());
        newUserMsg.setOtherid(newUserMsgReq.getOtherid());

        log.info("UserMsg{}",newUserMsg);
        UserMsg saved= userMsgService.createUserMsg(newUserMsg);
        return ResultBody.success(saved);
    }


    //删除用户消息
    @CheckToken
    @GetMapping(value = "/remove_user_msg",params = "id")
    public ResultBody removeUserMsg(@RequestParam  Long id) {
        Optional<UserMsg> userMsgOption=userMsgService.findUserMsgById(id);
        if(!userMsgOption.isPresent()){
            throw new BizException("-1","用户消息不存在");
        }

        userMsgService.removeUserMsg(id);
        return ResultBody.success("用户消息删除成功");
    }


    //修改用户消息
    //{"msg":"密码修改成功","type":0,"otherid":0,"isread":1,"id":1}
    @CheckToken
    @PostMapping(value = "/update_user_msg")
    public ResultBody updateUserMsg(@Valid @RequestBody NewUserMsgRequest newUserMsgReq){

        if(null==newUserMsgReq.getId() || 0==newUserMsgReq.getId() ){
            throw new BizException("-1","用户id不能为空");
        }

        Optional<UserMsg> userMsgOption=userMsgService.findUserMsgById(newUserMsgReq.getId());
        if(!userMsgOption.isPresent()){
            throw new BizException("-1","用户消息不存在");
        }

        UserMsg userMsgBase = userMsgOption.get();
        userMsgBase.setIsread(1);
        userMsgService.updateUserMsg(userMsgBase);
        return ResultBody.success("用户消息修改成功");
    }

    //查询用户消息
    @CheckToken
    @PostMapping(value = "/all_user_msg",params = "limit")
    public ResultBody allUserMsg(Integer limit,Integer page){

        if (null == page || 0 == page){
            page = 1;
        }
        // 排序方式，这里是以“id”为标准进行降序
        Sort sort = Sort.by(Sort.Direction.DESC, "id");  // 这里的"id"是实体类的主键，记住一定要是实体类的属性，而不能是数据库的字段
        Pageable pageable = PageRequest.of(page - 1,limit,sort);

        String token = request.getHeader("token");
        // 获取 token 中的 user id
        log.info("token:{}",token);
        String userId= JWT.decode(token).getClaim("id").asString();
        Long userid =Long.parseLong(userId);
        UserMsg userMsg = UserMsg.builder().userid(userid).build();
        JSONObject jsonObject = new JSONObject();
        Page<UserMsg> pageMsg = userMsgService.allUserMsg(userMsg,pageable);
        jsonObject.put("list", pageMsg.toList());
        return ResultBody.success(jsonObject);

    }




}

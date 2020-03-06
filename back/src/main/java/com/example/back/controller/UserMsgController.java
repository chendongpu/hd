package com.example.back.controller;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.example.back.controller.request.NewUserMsgRequest;
import com.example.back.handler.BizException;
import com.example.back.jwt.CheckToken;
import com.example.back.handler.ResultBody;
import com.example.back.model.UserMsg;
import com.example.back.service.UserMsgService;
import com.example.back.service.UserService;
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
import java.util.Optional;

@Api(value = "/user_msg", tags = "用户消息")
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



    @ApiOperation(value = "删除用户消息", notes = "传入消息id")
    @CheckToken
    @GetMapping(value = "/remove_user_msg",params = "id")
    public ResultBody removeUserMsg(@RequestParam Long id) {
        Optional<UserMsg> userMsgOption=userMsgService.findUserMsgById(id);
        if(!userMsgOption.isPresent()){
            throw new BizException("-1","用户消息不存在");
        }

        userMsgService.removeUserMsg(id);
        return ResultBody.success("用户消息删除成功");
    }



    @ApiOperation(value = "修改用户消息", notes = "传入消息json")
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


    @ApiOperation(value = "查询所有系统消息", notes = "limit表示每次查几条 page表示第几页")
    @CheckToken
    @PostMapping(value = "/all_system_msg",params = "limit")
    public ResultBody allSystemMsg(Integer limit,Integer page){

        if (null == page || 0 == page){
            page = 1;
        }
        // 排序方式，这里是以“id”为标准进行降序
        Sort sort = Sort.by(Sort.Direction.DESC, "id");  // 这里的"id"是实体类的主键，记住一定要是实体类的属性，而不能是数据库的字段
        Pageable pageable = PageRequest.of(page - 1,limit,sort);


        UserMsg userMsg = UserMsg.builder().type(0).build();
        JSONObject jsonObject = new JSONObject();
        Page<UserMsg> pageMsg = userMsgService.allUserMsg(userMsg,pageable);
        jsonObject.put("list", pageMsg.getContent());
        return ResultBody.success(jsonObject);

    }


    @ApiOperation(value = "查询所有评论消息", notes = "limit表示每次查几条 page表示第几页")
    @CheckToken
    @PostMapping(value = "/all_comment_msg",params = "limit")
    public ResultBody allCommentMsg(Integer limit,Integer page){

        if (null == page || 0 == page){
            page = 1;
        }
        // 排序方式，这里是以“id”为标准进行降序
        Sort sort = Sort.by(Sort.Direction.DESC, "id");  // 这里的"id"是实体类的主键，记住一定要是实体类的属性，而不能是数据库的字段
        Pageable pageable = PageRequest.of(page - 1,limit,sort);


        UserMsg userMsg = UserMsg.builder().type(1).build();
        JSONObject jsonObject = new JSONObject();
        Page<UserMsg> pageMsg = userMsgService.allUserMsg(userMsg,pageable);
        jsonObject.put("list", pageMsg.getContent());
        return ResultBody.success(jsonObject);

    }

    @ApiOperation(value = "查询所有点赞消息", notes = "limit表示每次查几条 page表示第几页")
    @CheckToken
    @PostMapping(value = "/all_like_msg",params = "limit")
    public ResultBody allLikeMsg(Integer limit,Integer page){

        if (null == page || 0 == page){
            page = 1;
        }
        // 排序方式，这里是以“id”为标准进行降序
        Sort sort = Sort.by(Sort.Direction.DESC, "id");  // 这里的"id"是实体类的主键，记住一定要是实体类的属性，而不能是数据库的字段
        Pageable pageable = PageRequest.of(page - 1,limit,sort);


        UserMsg userMsg = UserMsg.builder().type(2).build();
        JSONObject jsonObject = new JSONObject();
        Page<UserMsg> pageMsg = userMsgService.allUserMsg(userMsg,pageable);
        jsonObject.put("list", pageMsg.getContent());
        return ResultBody.success(jsonObject);

    }




}

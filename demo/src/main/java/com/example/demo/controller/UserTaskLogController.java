package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.example.demo.handler.BizException;
import com.example.demo.handler.ResultBody;
import com.example.demo.jwt.CheckToken;
import com.example.demo.model.*;
import com.example.demo.service.*;
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
import java.util.*;

@Api(value = "/user_task_log", tags = "任务完成记录")
@Slf4j
@RestController
@RequestMapping("/user_task_log")
public class UserTaskLogController {


    @Autowired
    private PointTaskService pointTaskService;

    @Autowired
    private UserTaskLogService userTaskLogService;

    @Autowired
    HttpServletRequest request;


    @ApiOperation(value = "添加任务完成记录", notes = "传入任务id")
    @CheckToken
    @PostMapping(value = "/create_user_task_log",params = "id")
    public ResultBody createUserTaskLog( @RequestParam  Long id) {
        Optional<PointTask> pointTaskOption=pointTaskService.findPointTaskById(id);
        if(!pointTaskOption.isPresent()){
            throw new BizException("-1","积分任务不存在");
        }

        PointTask task=pointTaskOption.get();

        String token = request.getHeader("token");
        // 获取 token 中的 user id
        log.info("token:{}",token);
        String userId= JWT.decode(token).getClaim("id").asString();
        Long userid =Long.parseLong(userId);
        log.info("userid:{}",userid);
        UserTaskLog newUserTaskLog=new UserTaskLog();
        newUserTaskLog.setUserid(userid);
        newUserTaskLog.setTaskid(task.getId());

        newUserTaskLog.setTitle(task.getTitle());
        newUserTaskLog.setPoint(task.getPoint());

        UserTaskLog saved= userTaskLogService.createUserTaskLog(newUserTaskLog);

        return ResultBody.success(saved);
    }





    @ApiOperation(value = "删除任务完成记录", notes = "传入任务id")
    @CheckToken
    @GetMapping(value = "/remove_user_task_log",params = "id")
    public ResultBody removeUserTaskLog(@RequestParam  Long id) {
        Optional<UserTaskLog> userTaskOption=userTaskLogService.findUserTaskLogById(id);
        if(!userTaskOption.isPresent()){
            throw new BizException("-1","任务完成记录不存在");
        }

        userTaskLogService.removeUserTaskLog(id);
        return ResultBody.success("任务完成记录删除成功");
    }


    


    @ApiOperation(value = "查询任务完成记录", notes = "limit表示每次查几条 page表示第几页")
    @CheckToken
    @PostMapping(value = "/all_user_task_log",params = "limit")
    public ResultBody allUserTaskLog(Integer limit,Integer page){

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
        UserTaskLog userTaskLog = UserTaskLog.builder().userid(userid).build();
        JSONObject jsonObject = new JSONObject();
        Page<UserTaskLog> pageTask = userTaskLogService.allUserTaskLog(userTaskLog,pageable);
        jsonObject.put("list", pageTask.getContent());
        return ResultBody.success(jsonObject);

    }






}




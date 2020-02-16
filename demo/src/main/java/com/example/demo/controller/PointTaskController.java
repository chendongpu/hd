package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.example.demo.controller.request.NewPointTaskRequest;
import com.example.demo.handler.BizException;
import com.example.demo.handler.ResultBody;
import com.example.demo.model.PointTask;
import com.example.demo.service.PointTaskService;
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
@RequestMapping("/point_task")
public class PointTaskController {

    @Autowired
    private PointTaskService pointTaskService;

    @Autowired
    private UserService userService;

    @Autowired
    HttpServletRequest request;

    //添加积分任务
    //{"title":"马上签到，领取签到奖励20","type":0,"point":10}
    @PostMapping("/create_point_task")
    public ResultBody createPointTask( @Valid @RequestBody NewPointTaskRequest newPointTaskReq) {
        Optional<PointTask> pointTaskOption=pointTaskService.findByTitle(newPointTaskReq.getTitle());
        if (pointTaskOption.isPresent()) {
            throw new BizException("-1","该积分任务已存在");
        }

        log.info("Receive new PointTask{}",newPointTaskReq);
        PointTask newPointTask=new PointTask();


        newPointTask.setTitle(newPointTaskReq.getTitle());
        newPointTask.setType(newPointTaskReq.getType());
        newPointTask.setPoint(newPointTaskReq.getPoint());

        log.info("PointTask{}",newPointTask);
        PointTask saved= pointTaskService.createPointTask(newPointTask);
        return ResultBody.success(saved);
    }


    //删除积分任务
    @GetMapping(value = "/remove_point_task",params = "id")
    public ResultBody removePointTask(@RequestParam  Long id) {
        Optional<PointTask> pointTaskOption=pointTaskService.findPointTaskById(id);
        if(!pointTaskOption.isPresent()){
            throw new BizException("-1","积分任务不存在");
        }

        pointTaskService.removePointTask(id);
        return ResultBody.success("积分任务删除成功");
    }


    //修改积分任务
    //{"title":"马上签到，领取签到奖励20","type":0,"point":10,id:1}
    @PostMapping(value = "/update_point_task")
    public ResultBody updatePointTask(@Valid @RequestBody NewPointTaskRequest newPointTaskReq){

        if(null==newPointTaskReq.getId() || 0==newPointTaskReq.getId() ){
            throw new BizException("-1","用户id不能为空");
        }

        Optional<PointTask> pointTaskOption=pointTaskService.findPointTaskById(newPointTaskReq.getId());
        if(!pointTaskOption.isPresent()){
            throw new BizException("-1","积分任务不存在");
        }

        PointTask pointTaskBase = pointTaskOption.get();
        pointTaskBase.setTitle(newPointTaskReq.getTitle());
        pointTaskBase.setType(newPointTaskReq.getType());
        pointTaskBase.setPoint(newPointTaskReq.getPoint());
        pointTaskService.updatePointTask(pointTaskBase);
        return ResultBody.success("积分任务修改成功");
    }

    //查询积分任务
    @PostMapping(value = "/all_point_task",params = "limit")
    public ResultBody allPointTask(Integer limit,Integer page){

        if (null == page || 0 == page){
            page = 1;
        }
        // 排序方式，这里是以“id”为标准进行降序
        Sort sort = Sort.by(Sort.Direction.DESC, "id");  // 这里的"id"是实体类的主键，记住一定要是实体类的属性，而不能是数据库的字段
        Pageable pageable = PageRequest.of(page - 1,limit,sort);


        PointTask pointTask = PointTask.builder().build();
        JSONObject jsonObject = new JSONObject();
        Page<PointTask> pageAddress = pointTaskService.allPointTask(pointTask,pageable);
        jsonObject.put("list", pageAddress.toList());
        return ResultBody.success(jsonObject);

    }




}

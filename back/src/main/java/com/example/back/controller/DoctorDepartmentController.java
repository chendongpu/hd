package com.example.back.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.back.controller.request.NewDoctorDepartmentRequest;
import com.example.back.handler.BizException;
import com.example.back.handler.ResultBody;
import com.example.back.jwt.CheckToken;
import com.example.back.model.DoctorDepartment;
import com.example.back.service.DoctorDepartmentService;
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


@Api(value = "/doctor_department", tags = "后台医生科室接口")
@Slf4j
@RestController
@RequestMapping("/doctor_department")
public class DoctorDepartmentController {

    @Autowired
    private DoctorDepartmentService doctorDepartmentService;

    @Autowired
    private UserService userService;

    @Autowired
    HttpServletRequest request;


    //{"title":"儿科","img":"/imgupload/d8744c4f60894af59ee233c4aaccba4e.jpg"}
    //{"title":"妇产科","img":"/imgupload/d8744c4f60894af59ee233c4aaccba4e.jpg"}
    //{"title":"内科","img":"/imgupload/d8744c4f60894af59ee233c4aaccba4e.jpg"}
    //{"title":"中医科","img":"/imgupload/d8744c4f60894af59ee233c4aaccba4e.jpg"}
    //{"title":"骨科","img":"/imgupload/d8744c4f60894af59ee233c4aaccba4e.jpg"}
    //{"title":"消化内科","img":"/imgupload/d8744c4f60894af59ee233c4aaccba4e.jpg"}
    //{"title":"男科","img":"/imgupload/d8744c4f60894af59ee233c4aaccba4e.jpg"}
    //{"title":"外科","img":"/imgupload/d8744c4f60894af59ee233c4aaccba4e.jpg"}
    //{"title":"耳鼻喉科","img":"/imgupload/d8744c4f60894af59ee233c4aaccba4e.jpg"}
    //{"title":"心理科","img":"/imgupload/d8744c4f60894af59ee233c4aaccba4e.jpg"}
    //{"title":"眼科","img":"/imgupload/d8744c4f60894af59ee233c4aaccba4e.jpg"}
    //{"title":"营养科","img":"/imgupload/d8744c4f60894af59ee233c4aaccba4e.jpg"}
    //{"title":"神经内科","img":"/imgupload/d8744c4f60894af59ee233c4aaccba4e.jpg"}
    //{"title":"肿瘤内科","img":"/imgupload/d8744c4f60894af59ee233c4aaccba4e.jpg"}
    //{"title":"口腔科","img":"/imgupload/d8744c4f60894af59ee233c4aaccba4e.jpg"}
    //{"title":"心内科","img":"/imgupload/d8744c4f60894af59ee233c4aaccba4e.jpg"}
    @ApiOperation(value = "添加医生科室", notes = "newDoctorDepartmentReq 为科室的标题和图片的json")
    @CheckToken
    @PostMapping("/create_doctor_department")
    public ResultBody createDoctorDepartment( @Valid @RequestBody NewDoctorDepartmentRequest newDoctorDepartmentReq) {
        Optional<DoctorDepartment> doctorDepartmentOption=doctorDepartmentService.findByTitle(newDoctorDepartmentReq.getTitle());
        if (doctorDepartmentOption.isPresent()) {
            throw new BizException("-1","该医生科室已存在");
        }

        log.info("Receive new DoctorDepartment{}",newDoctorDepartmentReq);
        DoctorDepartment newDoctorDepartment=new DoctorDepartment();


        newDoctorDepartment.setTitle(newDoctorDepartmentReq.getTitle());
        newDoctorDepartment.setImg(newDoctorDepartmentReq.getImg());

        log.info("DoctorDepartment{}",newDoctorDepartment);
        DoctorDepartment saved= doctorDepartmentService.createDoctorDepartment(newDoctorDepartment);
        return ResultBody.success(saved);
    }



    @ApiOperation(value = "删除医生科室", notes = "传入科室id")
    @CheckToken
    @GetMapping(value = "/remove_doctor_department",params = "id")
    public ResultBody removeDoctorDepartment(@RequestParam Long id) {
        Optional<DoctorDepartment> doctorDepartmentOption=doctorDepartmentService.findDoctorDepartmentById(id);
        if(!doctorDepartmentOption.isPresent()){
            throw new BizException("-1","医生科室不存在");
        }

        doctorDepartmentService.removeDoctorDepartment(id);
        return ResultBody.success("医生科室删除成功");
    }



    @ApiOperation(value = "修改医生科室", notes = "newDoctorDepartmentReq 为科室的标题和图片以及要修改的科室id的json")
    //{"title":"儿童科","img":"/imgupload/d8744c4f60894af59ee233c4aaccba4e.jpg"}
    @CheckToken
    @PostMapping(value = "/update_doctor_department")
    public ResultBody updateDoctorDepartment(@Valid @RequestBody NewDoctorDepartmentRequest newDoctorDepartmentReq){

        if(null==newDoctorDepartmentReq.getId() || 0==newDoctorDepartmentReq.getId() ){
            throw new BizException("-1","用户id不能为空");
        }

        Optional<DoctorDepartment> doctorDepartmentOption=doctorDepartmentService.findDoctorDepartmentById(newDoctorDepartmentReq.getId());
        if(!doctorDepartmentOption.isPresent()){
            throw new BizException("-1","医生科室不存在");
        }

        DoctorDepartment doctorDepartmentBase = doctorDepartmentOption.get();
        doctorDepartmentBase.setTitle(newDoctorDepartmentReq.getTitle());
        doctorDepartmentBase.setImg(newDoctorDepartmentReq.getImg());
        doctorDepartmentService.updateDoctorDepartment(doctorDepartmentBase);
        return ResultBody.success("医生科室修改成功");
    }


    @ApiOperation(value = "查询医生科室", notes = "limit表示每次查几条 page表示第几页")
    @CheckToken
    @PostMapping(value = "/all_doctor_department",params = "limit")
    public ResultBody allDoctorDepartment(Integer limit,Integer page){

        if (null == page || 0 == page){
            page = 1;
        }
        // 排序方式，这里是以“id”为标准进行降序
        Sort sort = Sort.by(Sort.Direction.DESC, "id");  // 这里的"id"是实体类的主键，记住一定要是实体类的属性，而不能是数据库的字段
        Pageable pageable = PageRequest.of(page - 1,limit,sort);


        DoctorDepartment doctorDepartment = DoctorDepartment.builder().build();
        JSONObject jsonObject = new JSONObject();
        Page<DoctorDepartment> pagedd= doctorDepartmentService.allDoctorDepartment(doctorDepartment,pageable);
        jsonObject.put("list", pagedd.getContent());
        return ResultBody.success(jsonObject);

    }




}

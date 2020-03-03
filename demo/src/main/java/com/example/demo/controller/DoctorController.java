package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.controller.response.DoctorResponse;
import com.example.demo.handler.ResultBody;
import com.example.demo.model.DoctorDepartment;
import com.example.demo.model.User;
import com.example.demo.service.DoctorDepartmentService;
import com.example.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    private UserService userService;

    @Autowired
    private DoctorDepartmentService doctorDepartmentService;

    @Autowired
    HttpServletRequest request;


    //查询医生列表
    @PostMapping(value = "/all_doctor",params = "limit")
    public ResultBody allDoctor(Integer limit,Integer page){

        if (null == page || 0 == page){
            page = 1;
        }
        // 排序方式，这里是以“id”为标准进行降序
        Sort sort = Sort.by(Sort.Direction.DESC, "id");  // 这里的"id"是实体类的主键，记住一定要是实体类的属性，而不能是数据库的字段
        Pageable pageable = PageRequest.of(page - 1,limit,sort);


        User doctor = User.builder().isdoctor(1).isdelete(0).build();
        JSONObject jsonObject = new JSONObject();
        Page<User> pageUser = userService.allDoctor(doctor,pageable);

        List<DoctorResponse> doctorResponseList =new ArrayList<>();

        List<User> userList =new ArrayList<>();

        userList = pageUser.getContent();

        for(User user:userList){

            Optional<DoctorDepartment> doctorDepartmentOptional = doctorDepartmentService.findDoctorDepartmentById(user.getDepartment());

            DoctorDepartment doctorDepartment = doctorDepartmentOptional.get();

            DoctorResponse doctorResponse=new DoctorResponse();

            doctorResponse.setUser(user);

            doctorResponse.setDoctorDepartment(doctorDepartment);

            doctorResponseList.add(doctorResponse);
        }

        jsonObject.put("list", doctorResponseList);
        return ResultBody.success(jsonObject);

    }

    //查询医生列表
    @PostMapping(value = "/find_doctor",params = "limit")
    public ResultBody findDoctor(Integer limit,Integer page,@Nullable String keyword){

        if (null == page || 0 == page){
            page = 1;
        }
        // 排序方式，这里是以“id”为标准进行降序
        Sort sort = Sort.by(Sort.Direction.DESC, "id");  // 这里的"id"是实体类的主键，记住一定要是实体类的属性，而不能是数据库的字段
        Pageable pageable = PageRequest.of(page - 1,limit,sort);


        User doctor = User.builder().isdoctor(1).isdelete(0).build();
        JSONObject jsonObject = new JSONObject();

        Page<User> pageUser;

        if(null!=keyword && !"".equals(keyword)){
            pageUser = userService.findByKeyword(keyword,pageable);
        }else{
            pageUser = userService.allDoctor(doctor,pageable);
        }



        List<DoctorResponse> doctorResponseList =new ArrayList<>();

        List<User> userList = pageUser.getContent();

        for(User user:userList){

            Optional<DoctorDepartment> doctorDepartmentOptional = doctorDepartmentService.findDoctorDepartmentById(user.getDepartment());

            DoctorDepartment doctorDepartment = doctorDepartmentOptional.get();

            DoctorResponse doctorResponse=new DoctorResponse();

            doctorResponse.setUser(user);

            doctorResponse.setDoctorDepartment(doctorDepartment);

            doctorResponseList.add(doctorResponse);
        }

        jsonObject.put("list", doctorResponseList);
        return ResultBody.success(jsonObject);

    }






}

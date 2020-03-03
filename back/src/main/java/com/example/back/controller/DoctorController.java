package com.example.back.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.back.controller.request.DoctorRequest;
import com.example.back.controller.request.UserRequest;
import com.example.back.controller.response.DoctorResponse;
import com.example.back.handler.BizException;
import com.example.back.handler.ResultBody;
import com.example.back.jwt.CheckToken;
import com.example.back.model.DoctorDepartment;
import com.example.back.model.User;
import com.example.back.service.DoctorDepartmentService;
import com.example.back.service.UserAddressService;
import com.example.back.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Api(value = "/doctor", tags = "后台医生接口")
@Slf4j
@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserAddressService userAddressService;

    @Autowired
    private DoctorDepartmentService doctorDepartmentService;



    @ApiOperation(value = "查询医生列表", notes = "limit表示每次查几条 page表示第几页 keyword表示关键词")
    @CheckToken
    @PostMapping(value = "/find_doctor",params = "limit")
    public ResultBody findDoctor(Integer limit, Integer page, @Nullable String keyword){

        if (null == page || 0 == page){
            page = 1;
        }
        // 排序方式，这里是以“id”为标准进行降序
        Sort sort = Sort.by(Sort.Direction.DESC, "id");  // 这里的"id"是实体类的主键，记住一定要是实体类的属性，而不能是数据库的字段
        Pageable pageable = PageRequest.of(page - 1,limit,sort);



        JSONObject jsonObject = new JSONObject();

        Page<User> pageUser;

        if(null!=keyword && !"".equals(keyword)){
            pageUser = userService.findByKeyword(keyword,pageable);
        }else{
            User doctor = User.builder().isdoctor(1).isdelete(0).build();
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


    @ApiOperation(value = "修改医生信息", notes = "可以修改真实名字、是否加入黑名单、科室、医院、职称、诊金、科室电话")
    @CheckToken
    @PostMapping(value = "/update_doctor")
    public ResultBody updateDoctor(@Valid @RequestBody DoctorRequest doctorRequest){
        Optional<User> userOption=userService.findUserById(doctorRequest.getId());
        if(!userOption.isPresent() || userOption.get().getIsdelete()==1){
            throw new BizException("-1","医生不存在");
        }
        User user =userOption.get();
        if(!(null==doctorRequest.getRealname())){
            user.setRealname(doctorRequest.getRealname());
        }

        if(!(null==doctorRequest.getIsblack())){
            user.setIsblack(doctorRequest.getIsblack());
        }

        if(!(null==doctorRequest.getDepartment())){
            user.setDepartment(doctorRequest.getDepartment());
        }

        if(!(null==doctorRequest.getHospital())){
            user.setHospital(doctorRequest.getHospital());
        }

        if(!(null==doctorRequest.getLevel())){
            user.setLevel(doctorRequest.getLevel());
        }

        if(!(null==doctorRequest.getMoney())){
            user.setMoney(doctorRequest.getMoney());
        }

        if(!(null==doctorRequest.getDepartmenttel())){
            user.setDepartmenttel(doctorRequest.getDepartmenttel());
        }

        userService.updateUser(user);
        return ResultBody.success("医生修改成功");
    }



    @ApiOperation(value = "删除医生信息", notes = "传入用户id")
    @CheckToken
    @GetMapping(value = "/remove_doctor",params = "id")
    public ResultBody removeDoctor(@RequestParam Long id) {
        Optional<User> userOption=userService.findUserById(id);
        if(!userOption.isPresent()){
            throw new BizException("-1","医生不存在");
        }

        userService.removeUser(id);
        return ResultBody.success("医生删除成功");
    }











}

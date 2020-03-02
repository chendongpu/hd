package com.example.back.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.back.controller.response.UserResponse;
import com.example.back.handler.ResultBody;
import com.example.back.jwt.CheckToken;
import com.example.back.model.DoctorDepartment;
import com.example.back.model.User;
import com.example.back.model.UserAddress;
import com.example.back.service.UserAddressService;
import com.example.back.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserAddressService userAddressService;


    //查询用户列表
    @CheckToken
    @PostMapping(value = "/find_user",params = "limit")
    public ResultBody findUser(Integer limit, Integer page, @Nullable String keyword){

        if (null == page || 0 == page){
            page = 1;
        }
        // 排序方式，这里是以“id”为标准进行降序
        Sort sort = Sort.by(Sort.Direction.DESC, "id");  // 这里的"id"是实体类的主键，记住一定要是实体类的属性，而不能是数据库的字段
        Pageable pageable = PageRequest.of(page - 1,limit,sort);


        JSONObject jsonObject = new JSONObject();

        Page<User> pageUser;

        if(null!=keyword && !"".equals(keyword)){
            pageUser = userService.findUserByKeyword(keyword,pageable);
        }else{
            User user = User.builder().isdoctor(0).build();

            pageUser = userService.allUser(user,pageable);
        }
        List<UserResponse> userResponseList =new ArrayList<>();

        List<User> userList = pageUser.getContent();

        for(User user:userList){

            Optional<UserAddress> userAddressOptional = userAddressService.findDefaultUserAddressByUserid(user.getId());

            UserResponse userResponse=new UserResponse();
            if(userAddressOptional.isPresent()){
                UserAddress userAddress = userAddressOptional.get();
                userResponse.setUserAddress(userAddress);

            }else{
                userResponse.setUserAddress(null);
            }
            userResponse.setUser(user);
            userResponseList.add(userResponse);
        }

        jsonObject.put("list", userResponseList);
        return ResultBody.success(jsonObject);

    }








}

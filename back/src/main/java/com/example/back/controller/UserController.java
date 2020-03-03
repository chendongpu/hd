package com.example.back.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.back.controller.request.UserRequest;
import com.example.back.controller.response.UserResponse;
import com.example.back.handler.BizException;
import com.example.back.handler.ResultBody;
import com.example.back.jwt.CheckToken;
import com.example.back.model.DoctorDepartment;
import com.example.back.model.User;
import com.example.back.model.UserAddress;
import com.example.back.service.UserAddressService;
import com.example.back.service.UserService;
import com.example.back.util.MD5Utils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
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

@Api(value = "/user", tags = "后台用户接口")
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserAddressService userAddressService;



    @ApiOperation(value = "查询用户列表", notes = "limit表示每次查几条 page表示第几页 keyword表示关键词")
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
            User user = User.builder().isdoctor(0).isdelete(0).build();
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


    @ApiOperation(value = "修改用户信息", notes = "可以修改真实名字、密码、是否加入黑名单")
    @CheckToken
    @PostMapping(value = "/update_user")
    public ResultBody updateUser(@Valid @RequestBody UserRequest userRequest){
        Optional<User> userOption=userService.findUserById(userRequest.getId());
        if(!userOption.isPresent() || userOption.get().getIsdelete()==1){
            throw new BizException("-1","用户不存在");
        }
        User user =userOption.get();
        if(!(null==userRequest.getRealname())){
            user.setRealname(userRequest.getRealname());
        }
        if(!(null==userRequest.getPassword())){
            user.setPassword(MD5Utils.stringToMD5(userRequest.getPassword()));
        }
        if(!(null==userRequest.getIsblack())){
            user.setIsblack(userRequest.getIsblack());
        }

        userService.updateUser(user);
        return ResultBody.success("用户修改成功");
    }



    @ApiOperation(value = "删除用户信息", notes = "传入用户id")
    @CheckToken
    @GetMapping(value = "/remove_user",params = "id")
    public ResultBody removeUser(@RequestParam  Long id) {
        Optional<User> userOption=userService.findUserById(id);
        if(!userOption.isPresent()){
            throw new BizException("-1","用户不存在");
        }

        userService.removeUser(id);
        return ResultBody.success("用户删除成功");
    }















}

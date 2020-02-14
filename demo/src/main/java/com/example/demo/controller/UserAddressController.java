package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.example.demo.controller.request.NewUserAddressRequest;
import com.example.demo.handler.BizException;
import com.example.demo.handler.ResultBody;
import com.example.demo.jwt.CheckToken;
import com.example.demo.model.User;
import com.example.demo.model.UserAddress;
import com.example.demo.service.UserAddressService;
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
@RequestMapping("/user_address")
public class UserAddressController {

    @Autowired
    private UserAddressService userAddressService;

    @Autowired
    private UserService userService;

    @Autowired
    HttpServletRequest request;

    //添加用户地址
    //{"realname":"陈东谱","mobile":"15057190640","province":"湖北省","city":"武穴市","area":"余川镇","address":"龟山村三组47号"}
    @CheckToken
    @PostMapping("/create_user_address")
    public ResultBody createUserAddress( @Valid @RequestBody NewUserAddressRequest newUserAddressReq) {
        Optional<UserAddress> userAddressOption=userAddressService.findByMobile(newUserAddressReq.getMobile());
        if (userAddressOption.isPresent()) {
            throw new BizException("-1","该手机号的地址已存在");
        }

        log.info("Receive new UserAddress{}",newUserAddressReq);
        UserAddress newUserAddress=new UserAddress();
        String token = request.getHeader("token");
        // 获取 token 中的 user id
        log.info("token:{}",token);
        String userId= JWT.decode(token).getClaim("id").asString();
        Long userid =Long.parseLong(userId);
        //newUserAddress.setUserid(userid);
        Optional<User> userOptional=userService.findUserById(userid);
        newUserAddress.setUser(userOptional.get());
        newUserAddress.setRealname(newUserAddressReq.getRealname());
        newUserAddress.setMobile(newUserAddressReq.getMobile());
        newUserAddress.setProvince(newUserAddressReq.getProvince());
        newUserAddress.setCity(newUserAddressReq.getCity());
        newUserAddress.setArea(newUserAddressReq.getArea());
        newUserAddress.setAddress(newUserAddressReq.getAddress());

        log.info("UserAddress{}",newUserAddress);
        UserAddress saved= userAddressService.createUserAddress(newUserAddress);
        return ResultBody.success(saved);
    }


    //删除用户地址
    @CheckToken
    @GetMapping(value = "/remove_user_address",params = "id")
    public ResultBody removeUserAddress(@RequestParam  Long id) {
        Optional<UserAddress> userAddressOption=userAddressService.findUserAddressById(id);
        if(!userAddressOption.isPresent()){
            throw new BizException("-1","用户地址不存在");
        }

        userAddressService.removeUserAddress(id);
        return ResultBody.success("用户地址删除成功");
    }


    //修改用户地址
    //{"realname":"陈东谱","mobile":"15057190640","province":"湖北省","city":"武穴市","area":"花桥镇","address":"龟山村三组47号","id":9}
    @CheckToken
    @PostMapping(value = "/update_user_address")
    public ResultBody updateUserAddress(@Valid @RequestBody NewUserAddressRequest newUserAddressReq){

        if(null==newUserAddressReq.getId() || 0==newUserAddressReq.getId() ){
            throw new BizException("-1","用户id不能为空");
        }

        Optional<UserAddress> userAddressOption=userAddressService.findUserAddressById(newUserAddressReq.getId());
        if(!userAddressOption.isPresent()){
            throw new BizException("-1","用户地址不存在");
        }

        UserAddress userAddressBase = userAddressOption.get();
        userAddressBase.setRealname(newUserAddressReq.getRealname());
        userAddressBase.setMobile(newUserAddressReq.getMobile());
        userAddressBase.setProvince(newUserAddressReq.getProvince());
        userAddressBase.setCity(newUserAddressReq.getCity());
        userAddressBase.setArea(newUserAddressReq.getArea());
        userAddressBase.setAddress(newUserAddressReq.getAddress());
        userAddressService.updateUserAddress(userAddressBase);
        return ResultBody.success("用户地址修改成功");
    }

    //查询用户地址
    @CheckToken
    @PostMapping(value = "/all_user_address",params = "limit")
    public ResultBody allUserAddress(Integer limit,Integer page){

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
        UserAddress userAddress = UserAddress.builder().userid(userid).build();
        JSONObject jsonObject = new JSONObject();
        Page<UserAddress> pageAddress = userAddressService.allUserAddress(userAddress,pageable);
        jsonObject.put("list", pageAddress.toList());
        return ResultBody.success(jsonObject);

    }




}

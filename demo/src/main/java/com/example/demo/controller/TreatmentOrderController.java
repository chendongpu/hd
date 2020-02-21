package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.example.demo.handler.BizException;
import com.example.demo.handler.ResultBody;
import com.example.demo.jwt.CheckToken;
import com.example.demo.model.TreatmentOrder;
import com.example.demo.model.User;
import com.example.demo.service.TreatmentOrderService;
import com.example.demo.service.UserService;
import com.example.demo.util.OrderIdUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/treatment_order")
public class TreatmentOrderController {

    @Autowired
    private TreatmentOrderService treatmentOrderService;

    @Autowired
    private UserService userService;

    @Autowired
    HttpServletRequest request;

    //添加接诊订单
    @CheckToken
    @PostMapping("/create_treatment_order")
    public ResultBody createTreatmentOrder( @NotNull Long id) {
        Optional<User> userOptional=userService.findUserById(id);
        if (!userOptional.isPresent()) {
            throw new BizException("-1","医生不存在");
        }
        User doctor=userOptional.get();
        if(doctor.getIsdoctor() != 1){
            throw new BizException("-1","不是医生");
        }

        TreatmentOrder newTreatmentOrder=new TreatmentOrder();


        String token = request.getHeader("token");
        // 获取 token 中的 user id
        log.info("token:{}",token);
        String userId= JWT.decode(token).getClaim("id").asString();
        Long userid =Long.parseLong(userId);
        newTreatmentOrder.setOrderid(OrderIdUtil.getOrderIdByUUId(userId));
        newTreatmentOrder.setUserid(userid);
        newTreatmentOrder.setDoctorid(id);
        newTreatmentOrder.setMoney(doctor.getMoney());
        newTreatmentOrder.setDuration(doctor.getDuration());

        log.info("TreatmentOrder{}",newTreatmentOrder);
        TreatmentOrder saved= treatmentOrderService.createTreatmentOrder(newTreatmentOrder);
        return ResultBody.success(saved);
    }


    //查询接诊订单
    @CheckToken
    @PostMapping(value = "/all_treatment_order",params = "limit")
    public ResultBody allTreatmentOrder(Integer limit,Integer page){

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
        TreatmentOrder treatmentOrder = TreatmentOrder.builder().userid(userid).build();
        JSONObject jsonObject = new JSONObject();
        Page<TreatmentOrder> pageto = treatmentOrderService.allTreatmentOrder(treatmentOrder,pageable);
        jsonObject.put("list", pageto.toList());
        return ResultBody.success(jsonObject);

    }







}

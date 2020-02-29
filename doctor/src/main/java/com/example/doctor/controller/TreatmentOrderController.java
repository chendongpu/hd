package com.example.doctor.controller;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.example.doctor.handler.BizException;
import com.example.doctor.handler.ResultBody;
import com.example.doctor.jwt.CheckToken;
import com.example.doctor.model.TreatmentOrder;
import com.example.doctor.model.User;
import com.example.doctor.service.TreatmentOrderService;
import com.example.doctor.service.UserService;
import com.example.doctor.util.OrderIdUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.util.Optional;

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


    //查询接诊订单
    //type 1 待接诊 2 进行中 3 已完结
    @CheckToken
    @PostMapping(value = "/all_treatment_order",params = "limit")
    public ResultBody allTreatmentOrder(Integer limit,Integer page,Integer type){

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
        Page<TreatmentOrder> pageto = treatmentOrderService.findTreatmentOrder(treatmentOrder,pageable,type);
        jsonObject.put("list", pageto.getContent());
        return ResultBody.success(jsonObject);

    }







}

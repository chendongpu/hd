package com.example.back.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.back.handler.BizException;
import com.example.back.handler.ResultBody;
import com.example.back.jwt.CheckToken;
import com.example.back.model.TreatmentOrder;
import com.example.back.service.TreatmentOrderService;
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
import java.util.Optional;


@Api(value = "/treatment_order", tags = "用户接诊订单")
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
    @ApiOperation(value = "查询用户接诊订单", notes = "limit表示每次查几条 page表示第几页")
    @CheckToken
    @PostMapping(value = "/all_treatment_order",params = "limit")
    public ResultBody allTreatmentOrder(Integer limit,Integer page){

        if (null == page || 0 == page){
            page = 1;
        }
        // 排序方式，这里是以“id”为标准进行降序
        Sort sort = Sort.by(Sort.Direction.DESC, "id");  // 这里的"id"是实体类的主键，记住一定要是实体类的属性，而不能是数据库的字段
        Pageable pageable = PageRequest.of(page - 1,limit,sort);

        TreatmentOrder treatmentOrder = TreatmentOrder.builder().build();
        JSONObject jsonObject = new JSONObject();
        Page<TreatmentOrder> pageto = treatmentOrderService.allTreatmentOrder(treatmentOrder,pageable);
        jsonObject.put("list", pageto.getContent());
        return ResultBody.success(jsonObject);

    }

    //查询接诊订单详情
    @ApiOperation(value = "查询接诊订单详情", notes = "传入接诊订单id")
    @CheckToken
    @GetMapping(value = "/one_treatment_order",params = "id")
    public ResultBody oneTreatmentOrder(@RequestParam Long id){
        if(null==id|| 0==id){
            throw new BizException("-1","接诊订单id不能为空");
        }

        Optional<TreatmentOrder> treatmentOrderOptional=treatmentOrderService.findTreatmentOrderById(id);
        if(!treatmentOrderOptional.isPresent()){
            throw new BizException("-1","接诊订单不存在");
        }


        return ResultBody.success(treatmentOrderOptional.get());
    }




}

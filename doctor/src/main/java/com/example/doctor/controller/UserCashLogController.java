package com.example.doctor.controller;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.example.doctor.handler.BizException;
import com.example.doctor.handler.ResultBody;
import com.example.doctor.jwt.CheckToken;
import com.example.doctor.model.UserCashLog;
import com.example.doctor.service.UserCashLogService;
import com.example.doctor.handler.BizException;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotEmpty;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/user_cash_log")
public class UserCashLogController {




    @Autowired
    private UserCashLogService userCashLogService;

    @Autowired
    HttpServletRequest request;

    //添加提现记录
    //传入参数 money  为 CNY 1000.92
    //传入参数 bank  为 中国银行
    //传入参数 card  为 62312321312313
    @CheckToken
    @PostMapping(value = "/create_user_cash_log")
    public ResultBody createUserCashLog(@NotEmpty @RequestParam("money")  Money money,@NotEmpty @RequestParam("bank") String bank,@NotEmpty  @RequestParam("card")String card) {


        String token = request.getHeader("token");
        // 获取 token 中的 user id
        log.info("token:{}",token);
        String userId= JWT.decode(token).getClaim("id").asString();
        Long userid =Long.parseLong(userId);
        log.info("userid:{}",userid);
        UserCashLog newUserCashLog=new UserCashLog();
        newUserCashLog.setUserid(userid);
        newUserCashLog.setType(0);

        newUserCashLog.setMoney(money);
        newUserCashLog.setBank(bank);
        newUserCashLog.setCard(card);

        UserCashLog saved= userCashLogService.createUserCashLog(newUserCashLog);

        return ResultBody.success(saved);
    }




    //删除提现记录
    @CheckToken
    @GetMapping(value = "/remove_user_cash_log",params = "id")
    public ResultBody removeUserCashLog(@RequestParam Long id) {
        Optional<UserCashLog> userCashOption=userCashLogService.findUserCashLogById(id);
        if(!userCashOption.isPresent()){
            throw new BizException("-1","提现记录不存在");
        }

        userCashLogService.removeUserCashLog(id);
        return ResultBody.success("提现记录删除成功");
    }


    

    //查询提现记录
    @CheckToken
    @PostMapping(value = "/all_user_cash_log",params = "limit")
    public ResultBody allUserCashLog(Integer limit,Integer page){

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
        UserCashLog userCashLog = UserCashLog.builder().userid(userid).type(0).build();
        JSONObject jsonObject = new JSONObject();
        Page<UserCashLog> pageCash = userCashLogService.allUserCashLog(userCashLog,pageable);
        jsonObject.put("list", pageCash.getContent());
        return ResultBody.success(jsonObject);

    }






}




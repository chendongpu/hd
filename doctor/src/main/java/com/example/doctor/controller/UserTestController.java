package com.example.doctor.controller;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.example.doctor.controller.request.NewUserTestRequest;
import com.example.doctor.handler.BizException;
import com.example.doctor.handler.ResultBody;
import com.example.doctor.jwt.CheckToken;
import com.example.doctor.model.UserTest;
import com.example.doctor.service.UserService;
import com.example.doctor.service.UserTestService;
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
@RequestMapping("/user_test")
public class UserTestController {

    @Autowired
    private UserTestService userTestService;

    @Autowired
    private UserService userService;


    @Autowired
    HttpServletRequest request;

    //添加测评
    //{"title":"测评标题bbbaaxxa","content":"测评介绍bbaaxx"}
    @CheckToken
    @PostMapping("/create_user_test")
    public ResultBody createUserTest( @Valid @RequestBody NewUserTestRequest newUserTestReq) {
        Optional<UserTest> userTestOption=userTestService.findByTitle(newUserTestReq.getTitle());
        if (userTestOption.isPresent()) {
            throw new BizException("-1","测评已存在");
        }

        log.info("Receive new UserTest{}",newUserTestReq);
        UserTest newUserTest=new UserTest();
        String token = request.getHeader("token");
        // 获取 token 中的 user id
        log.info("token:{}",token);
        String userId= JWT.decode(token).getClaim("id").asString();
        Long userid =Long.parseLong(userId);
        newUserTest.setUserid(userid);
        newUserTest.setTitle(newUserTestReq.getTitle());
        newUserTest.setContent(newUserTestReq.getContent());

        UserTest saved= userTestService.createUserTest(newUserTest);
        return ResultBody.success(saved);
    }


    //删除测评
    @CheckToken
    @GetMapping(value = "/remove_user_test",params = "id")
    public ResultBody removeUserTest(@RequestParam Long id) {
        Optional<UserTest> userTestOption=userTestService.findUserTestById(id);
        if(!userTestOption.isPresent()){
            throw new BizException("-1","测评不存在");
        }

        userTestService.removeUserTest(id);
        return ResultBody.success("测评删除成功");
    }


    //修改测评
    //{"title":"测评标题xxx","content":"测评介绍xxx","id":5}
    @CheckToken
    @PostMapping(value = "/update_user_test")
    public ResultBody updateUserTest(@Valid @RequestBody NewUserTestRequest newUserTestReq){

        if(null==newUserTestReq.getId() || 0==newUserTestReq.getId() ){
            throw new BizException("-1","测评id不能为空");
        }

        Optional<UserTest> userTestOption=userTestService.findUserTestById(newUserTestReq.getId());
        if(!userTestOption.isPresent()){
            throw new BizException("-1","测评不存在");
        }

        UserTest userTestBase = userTestOption.get();
        userTestBase.setTitle(newUserTestReq.getTitle());
        userTestBase.setContent(newUserTestReq.getContent());
        userTestService.updateUserTest(userTestBase);
        return ResultBody.success("测评修改成功");
    }

    //查询测评
    @CheckToken
    @PostMapping(value = "/all_user_test",params = "limit")
    public ResultBody allUserTest(Integer limit,Integer page){

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
        UserTest userTest = UserTest.builder().userid(userid).build();
        JSONObject jsonObject = new JSONObject();
        Page<UserTest> pageTest = userTestService.allUserTest(userTest,pageable);
        jsonObject.put("list", pageTest.getContent());
        return ResultBody.success(jsonObject);

    }

    //测评详情
    @CheckToken
    @GetMapping(value = "/one_user_test",params = "id")
    public ResultBody oneUserTest(@RequestParam Long id){
        if(null==id|| 0==id){
            throw new BizException("-1","测评id不能为空");
        }

        Optional<UserTest> userTestOption=userTestService.findUserTestById(id);
        if(!userTestOption.isPresent()){
            throw new BizException("-1","测评不存在");
        }


        return ResultBody.success(userTestOption.get());
    }




}

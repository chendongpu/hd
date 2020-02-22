package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.auth0.jwt.JWT;
import com.example.demo.controller.packclass.PackUserQuestion;
import com.example.demo.controller.packclass.PackUserTest;
import com.example.demo.controller.request.NewUserTestLogRequest;
import com.example.demo.controller.request.QuestionChoiceInput;
import com.example.demo.handler.BizException;
import com.example.demo.handler.ResultBody;
import com.example.demo.jwt.CheckToken;
import com.example.demo.model.*;
import com.example.demo.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.lang.reflect.Array;
import java.util.*;

@Slf4j
@RestController
@RequestMapping("/user_test_log")
public class UserTestLogController {


    @Autowired
    private UserTestService userTestService;

    @Autowired
    private UserTestLogService userTestLogService;

    @Autowired
    private UserQuestionService userQuestionService;

    @Autowired
    private UserQuestionChoiceService userQuestionChoiceService;

    @Autowired
    private UserQuestionAnswerService userQuestionAnswerService;

    @Autowired
    private UserTestReportService userTestReportService;


    @Autowired
    private UserService userService;

    @Autowired
    HttpServletRequest request;

    //添加测评记录
    //{"testid":1,"questionChoiceInputSet":[{"questionid":1,"choiceid":[2]},{"questionid":2,"choiceid":[3,4]},{"questionid":3,"choiceid":[6]}]}
    @CheckToken
    @PostMapping("/create_user_test_log")
    public ResultBody createUserTestLog( @Valid @RequestBody NewUserTestLogRequest newUserTestLogReq) {

        Optional<UserTest> userTestOption=userTestService.findUserTestById(newUserTestLogReq.getTestid());
        UserTest userTest=userTestOption.get();

        Set<QuestionChoiceInput> questionChoiceInputSet = newUserTestLogReq.getQuestionChoiceInputSet();

        String token = request.getHeader("token");
        // 获取 token 中的 user id
        log.info("token:{}",token);
        String userId= JWT.decode(token).getClaim("id").asString();
        Long userid =Long.parseLong(userId);

        UserTestLog newUserTestLog=new UserTestLog();
        newUserTestLog.setUserid(userid);
        newUserTestLog.setTestid(userTest.getId());

        newUserTestLog.setTitle(userTest.getTitle());
        newUserTestLog.setContent(userTest.getContent());

        List<PackUserQuestion> userQuestionList =this.generateResult(questionChoiceInputSet);

        Integer score=0;



        for(PackUserQuestion puq :userQuestionList){
            if(puq.isResult()){
                score+=puq.getUserQuestion().getScore();
            }
        }

        newUserTestLog.setScore(score);

        List<UserTestReport> userTestReportList = userTestReportService.findByTestid(userTest.getId());

        Integer[] array =new Integer[userTestReportList.size()];

        int i=0;
        for(UserTestReport userTestReport:userTestReportList){
            array[i]=userTestReport.getScore();
            i++;
        }

        Integer targetNumber = this.getTargetNumber(array,score);


        PackUserTest put = new PackUserTest();

        put.setPackUserQuestionList(userQuestionList);

        UserTestReport resultReport =new UserTestReport();

        for(UserTestReport userTestReport:userTestReportList){
            if(targetNumber == userTestReport.getScore()){
                resultReport=userTestReport;
            }
        }

        put.setResultReport(resultReport);

        put.setUserTestReportList(userTestReportList);

        String result = JSONObject.toJSONString(put, SerializerFeature.DisableCircularReferenceDetect);

        newUserTestLog.setResult(result);

        UserTestLog saved= userTestLogService.createUserTestLog(newUserTestLog);

        return ResultBody.success(saved);
    }


    //根据输入的问题及选项计算生成返回结果
    private List<PackUserQuestion> generateResult(Set<QuestionChoiceInput> questionChoiceInputSet){
        JSONObject jsonObject = new JSONObject();

        List<PackUserQuestion> packUserQuestionList = new ArrayList<>();

        for (QuestionChoiceInput qci:questionChoiceInputSet){

            PackUserQuestion puq =new PackUserQuestion();

            Long questionid = qci.getQuestionid();

            Long[] choiceid = qci.getChoiceid();

            Optional<UserQuestion> userQuestionOptional = userQuestionService.findUserQuestionById(questionid);

            UserQuestion userQuestion = userQuestionOptional.get();

            puq.setYourAnswer(choiceid);

            boolean result = this.checkQuestion(questionid,choiceid);

            puq.setUserQuestion(userQuestion);

            List<UserQuestionChoice> userQuestionChoiceList = userQuestionChoiceService.findByQuestionid(questionid);
            List<UserQuestionAnswer> userQuestionAnswerList = userQuestionAnswerService.findByQuestionid(questionid);


            puq.setUserQuestionChoiceList(userQuestionChoiceList);

            puq.setUserQuestionAnswerList(userQuestionAnswerList);

            puq.setResult(result);

            packUserQuestionList.add(puq);

        }


        return packUserQuestionList;
    }

    //判断问题是否正确
    private boolean checkQuestion(Long questionid, Long[] choiceid){
        List<UserQuestionAnswer> userQuestionAnswerList = userQuestionAnswerService.findByQuestionid(questionid);
        Long[] answerChoiceids = new Long[userQuestionAnswerList.size()];
        int index=0;
        for (UserQuestionAnswer uqa : userQuestionAnswerList){
            answerChoiceids[index] = uqa.getChoiceid();
            index++;
        }
        Arrays.sort(choiceid);
        Arrays.sort(answerChoiceids);
        if (Arrays.equals(choiceid, answerChoiceids)) {
            return true;
        } else {
            return false;
        }
    }

    //查找数组中与给定的数字最接近的那个值
    private  Integer getTargetNumber(Integer[] array,Integer number){
        int index = Math.abs(number-array[0]);
        int result = array[0];
        for (int i : array) {
            int abs = Math.abs(number-i);
            if(abs <= index){
                index = abs;
                result = i;
            }
        }
        return result;
    }


    //删除测评记录
    @CheckToken
    @GetMapping(value = "/remove_user_test_log",params = "id")
    public ResultBody removeUserTestLog(@RequestParam  Long id) {
        Optional<UserTestLog> userTestOption=userTestLogService.findUserTestLogById(id);
        if(!userTestOption.isPresent()){
            throw new BizException("-1","测评记录不存在");
        }

        userTestLogService.removeUserTestLog(id);
        return ResultBody.success("测评记录删除成功");
    }


    

    //查询测评记录
    @CheckToken
    @PostMapping(value = "/all_user_test_log",params = "limit")
    public ResultBody allUserTestLog(Integer limit,Integer page){

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
        UserTestLog userTestLog = UserTestLog.builder().userid(userid).build();
        JSONObject jsonObject = new JSONObject();
        Page<UserTestLog> pageTest = userTestLogService.allUserTestLog(userTestLog,pageable);
        jsonObject.put("list", pageTest.getContent());
        return ResultBody.success(jsonObject);

    }

    //测评记录详情
    @CheckToken
    @GetMapping(value = "/one_user_test_log",params = "id")
    public ResultBody oneUserTestLog(@RequestParam Long id){
        if(null==id|| 0==id){
            throw new BizException("-1","测评记录id不能为空");
        }

        Optional<UserTestLog> userTestLogOption=userTestLogService.findUserTestLogById(id);
        if(!userTestLogOption.isPresent()){
            throw new BizException("-1","测评记录不存在");
        }


        return ResultBody.success(userTestLogOption.get());
    }




}




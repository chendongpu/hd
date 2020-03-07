package com.example.back.service;

import com.example.back.model.UserQuestionAnswer;
import com.example.back.repository.UserQuestionAnswerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserQuestionAnswerService {

    @Autowired
    private UserQuestionAnswerRepository userQuestionAnswerRepository;

    //根据问题id查询问题答案选项
    public List<UserQuestionAnswer> findByQuestionid(Long questionid){
        UserQuestionAnswer uqa=UserQuestionAnswer.builder().questionid(questionid).build();
        Example<UserQuestionAnswer> example = Example.of(uqa);
        List<UserQuestionAnswer> userQuestionAnswerList =userQuestionAnswerRepository.findAll(example);
        return userQuestionAnswerList;
    }

    //根据问题id和选项id查询答案
    public Optional<UserQuestionAnswer> findByQuestionid(Long questionid,Long choiceid){
        UserQuestionAnswer uqa=UserQuestionAnswer.builder().questionid(questionid).choiceid(choiceid).build();
        Example<UserQuestionAnswer> example = Example.of(uqa);
        Optional<UserQuestionAnswer> userQuestionAnswerOptional =userQuestionAnswerRepository.findOne(example);
        return userQuestionAnswerOptional;
    }


    //添加问题答案
    public UserQuestionAnswer createQuestionAnswer(UserQuestionAnswer userQuestionAnswer) {
        UserQuestionAnswer saved = userQuestionAnswerRepository.save(userQuestionAnswer);
        return saved;
    }

    //删除答案
    public void removeQuestionAnswer(UserQuestionAnswer userQuestionAnswer) {
        userQuestionAnswerRepository.delete(userQuestionAnswer);
    }




}

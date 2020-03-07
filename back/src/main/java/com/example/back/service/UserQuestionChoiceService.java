package com.example.back.service;

import com.example.back.model.UserQuestionChoice;
import com.example.back.repository.UserQuestionChoiceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserQuestionChoiceService {

    @Autowired
    private UserQuestionChoiceRepository userQuestionChoiceRepository;

    //根据问题id查询问题选项
    public List<UserQuestionChoice> findByQuestionid(Long questionid){
        UserQuestionChoice uqc=UserQuestionChoice.builder().questionid(questionid).build();
        Example<UserQuestionChoice> example = Example.of(uqc);
        List<UserQuestionChoice> userQuestionChoiceList =userQuestionChoiceRepository.findAll(example);
        return userQuestionChoiceList;
    }

    //查询单个选项
    public Optional<UserQuestionChoice> findUserQuestionChoiceById(Long id){
        Optional<UserQuestionChoice> userQuestionChoiceOptional =userQuestionChoiceRepository.findById(id);
        log.info("userQuestionChoiceOptional Found:{}",userQuestionChoiceOptional);
        return userQuestionChoiceOptional;
    }


    //批量添加
    public List<UserQuestionChoice> createBatchUserQuestionChoice(List<UserQuestionChoice> userQuestionChoices) {
        List<UserQuestionChoice> saved = userQuestionChoiceRepository.saveAll(userQuestionChoices);
        return saved;
    }

    //批量删除
    public void removeBatchUserQuestionChoice(List<UserQuestionChoice> userQuestionChoices) {
        userQuestionChoiceRepository.deleteAll(userQuestionChoices);
    }




}

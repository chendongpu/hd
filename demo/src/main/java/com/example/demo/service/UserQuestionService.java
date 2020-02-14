package com.example.demo.service;

import com.example.demo.model.UserQuestion;
import com.example.demo.repository.UserQuestionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact;

@Slf4j
@Service
public class UserQuestionService {

    @Autowired
    private UserQuestionRepository userQuestionRepository;

    public Optional<UserQuestion> findByTitle(String title){
        ExampleMatcher matcher=ExampleMatcher.matching().withMatcher("title",exact().ignoreCase());
        Optional<UserQuestion> userQuestion =userQuestionRepository.findOne(Example.of(UserQuestion.builder().title(title).build(),matcher));
        log.info("userQuestion Found:{}",userQuestion);
        return userQuestion;
    }

    public Optional<UserQuestion> findUserQuestionById(Long id){
        Optional<UserQuestion> userQuestion =userQuestionRepository.findById(id);
        log.info("userQuestion Found:{}",userQuestion);
        return userQuestion;
    }

    public UserQuestion createUserQuestion(UserQuestion newUserQuestion) {
        log.info("UserQuestion:{}",newUserQuestion);
        UserQuestion saved = userQuestionRepository.save(newUserQuestion);
        log.info("New UserQuestion:{}", saved);
        return saved;
    }

    public void removeUserQuestion(Long id){
        userQuestionRepository.deleteById(id);
    }

    public void updateUserQuestion(UserQuestion newUserQuestion){
        userQuestionRepository.save(newUserQuestion);
    }

    public Page<UserQuestion> allUserQuestion(UserQuestion UserQuestion,Pageable pageable){
        Example<UserQuestion> example = Example.of(UserQuestion);
        return userQuestionRepository.findAll(example,pageable);
    }
}

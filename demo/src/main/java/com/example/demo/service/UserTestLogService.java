package com.example.demo.service;


import com.example.demo.model.UserTestLog;
import com.example.demo.repository.UserTestLogRepository;
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
public class UserTestLogService {

    @Autowired
    private UserTestLogRepository userTestLogRepository;

    public Optional<UserTestLog> findByTitle(Long userid){
        ExampleMatcher matcher=ExampleMatcher.matching().withMatcher("userid",exact().ignoreCase());
        Optional<UserTestLog> userTestLog =userTestLogRepository.findOne(Example.of(UserTestLog.builder().userid(userid).build(),matcher));
        log.info("userTestLog Found:{}",userTestLog);
        return userTestLog;
    }

    public Optional<UserTestLog> findUserTestLogById(Long id){
        Optional<UserTestLog> userTestLog =userTestLogRepository.findById(id);
        log.info("userTestLog Found:{}",userTestLog);
        return userTestLog;
    }

    public UserTestLog createUserTestLog(UserTestLog newUserTestLog) {
        log.info("UserTestLog:{}",newUserTestLog);
        UserTestLog saved = userTestLogRepository.save(newUserTestLog);
        log.info("New UserTestLog:{}", saved);
        return saved;
    }

    public void removeUserTestLog(Long id){
        userTestLogRepository.deleteById(id);
    }

    public Page<UserTestLog> allUserTestLog(UserTestLog UserTestLog,Pageable pageable){
        Example<UserTestLog> example = Example.of(UserTestLog);
        return userTestLogRepository.findAll(example,pageable);
    }
}

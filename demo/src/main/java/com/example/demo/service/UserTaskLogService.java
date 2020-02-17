package com.example.demo.service;


import com.example.demo.model.UserTaskLog;
import com.example.demo.repository.UserTaskLogRepository;
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
public class UserTaskLogService {

    @Autowired
    private UserTaskLogRepository userTaskLogRepository;

    public Optional<UserTaskLog> findByTitle(Long userid){
        ExampleMatcher matcher=ExampleMatcher.matching().withMatcher("userid",exact().ignoreCase());
        Optional<UserTaskLog> userTaskLog =userTaskLogRepository.findOne(Example.of(UserTaskLog.builder().userid(userid).build(),matcher));
        log.info("userTaskLog Found:{}",userTaskLog);
        return userTaskLog;
    }

    public Optional<UserTaskLog> findUserTaskLogById(Long id){
        Optional<UserTaskLog> userTaskLog =userTaskLogRepository.findById(id);
        log.info("userTaskLog Found:{}",userTaskLog);
        return userTaskLog;
    }

    public UserTaskLog createUserTaskLog(UserTaskLog newUserTaskLog) {
        log.info("UserTaskLog:{}",newUserTaskLog);
        UserTaskLog saved = userTaskLogRepository.save(newUserTaskLog);
        log.info("New UserTaskLog:{}", saved);
        return saved;
    }

    public void removeUserTaskLog(Long id){
        userTaskLogRepository.deleteById(id);
    }

    public Page<UserTaskLog> allUserTaskLog(UserTaskLog UserTaskLog,Pageable pageable){
        Example<UserTaskLog> example = Example.of(UserTaskLog);
        return userTaskLogRepository.findAll(example,pageable);
    }
}

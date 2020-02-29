package com.example.doctor.service;

import com.example.doctor.model.UserCashLog;
import com.example.doctor.repository.UserCashLogRepository;
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
public class UserCashLogService {

    @Autowired
    private UserCashLogRepository userCashLogRepository;

    public Optional<UserCashLog> findByTitle(Long userid){
        ExampleMatcher matcher= ExampleMatcher.matching().withMatcher("userid",exact().ignoreCase());
        Optional<UserCashLog> userCashLog =userCashLogRepository.findOne(Example.of(UserCashLog.builder().userid(userid).build(),matcher));
        log.info("userCashLog Found:{}",userCashLog);
        return userCashLog;
    }

    public Optional<UserCashLog> findUserCashLogById(Long id){
        Optional<UserCashLog> userCashLog =userCashLogRepository.findById(id);
        log.info("userCashLog Found:{}",userCashLog);
        return userCashLog;
    }

    public UserCashLog createUserCashLog(UserCashLog newUserCashLog) {
        log.info("UserCashLog:{}",newUserCashLog);
        UserCashLog saved = userCashLogRepository.save(newUserCashLog);
        log.info("New UserCashLog:{}", saved);
        return saved;
    }

    public void updateUserCash(UserCashLog newUserCashLog){
        userCashLogRepository.save(newUserCashLog);
    }

    public void removeUserCashLog(Long id){
        userCashLogRepository.deleteById(id);
    }

    public Page<UserCashLog> allUserCashLog(UserCashLog userCashLog, Pageable pageable){
        Example<UserCashLog> example = Example.of(userCashLog);
        return userCashLogRepository.findAll(example,pageable);
    }
}

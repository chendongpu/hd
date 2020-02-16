package com.example.demo.service;

import com.example.demo.model.UserMsg;
import com.example.demo.repository.UserMsgRepository;
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
public class UserMsgService {

    @Autowired
    private UserMsgRepository userMsgRepository;


    public Optional<UserMsg> findUserMsgById(Long id){
        Optional<UserMsg> userMsg =userMsgRepository.findById(id);
        log.info("UserMsg Found:{}",userMsg);
        return userMsg;
    }

    public UserMsg createUserMsg(UserMsg newUserMsg) {
        log.info("UserMsg:{}",newUserMsg);
        UserMsg saved = userMsgRepository.save(newUserMsg);
        log.info("New UserMsg:{}", saved);
        return saved;
    }

    public void removeUserMsg(Long id){
        userMsgRepository.deleteById(id);
    }

    public void updateUserMsg(UserMsg newUserMsg){
        userMsgRepository.save(newUserMsg);
    }

    public Page<UserMsg> allUserMsg(UserMsg userMsg,Pageable pageable){
        Example<UserMsg> example = Example.of(userMsg);
        return userMsgRepository.findAll(example,pageable);
    }
}

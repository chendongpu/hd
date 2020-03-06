package com.example.back.service;

import com.example.back.model.UserMsg;
import com.example.back.repository.UserMsgRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    public Page<UserMsg> allUserMsg(UserMsg userMsg, Pageable pageable){
        Example<UserMsg> example = Example.of(userMsg);
        return userMsgRepository.findAll(example,pageable);
    }
}

package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.util.MD5Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact;

@Slf4j
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> findByUsername(String username){
        ExampleMatcher matcher=ExampleMatcher.matching().withMatcher("username",exact().ignoreCase());
        Optional<User> user =userRepository.findOne(Example.of(User.builder().username(username).build(),matcher));
        log.info("User Found:{}",user);
        return user;
    }


    public Optional<User> findUserById(Long id){
        Optional<User> user =userRepository.findById(id);
        log.info("User Found:{}",user);
        return user;
    }

    public User createUser(String username, String password) {
        User user = User.builder().username(username).password(MD5Utils.stringToMD5(password)).build();

        log.info("User:{}",user);
        User saved = userRepository.save(user);
        log.info("New User:{}", saved);
        return saved;
    }

}

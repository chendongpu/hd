package com.example.demo.service;

import com.example.demo.model.UserTest;
import com.example.demo.repository.UserTestRepository;
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
public class UserTestService {

    @Autowired
    private UserTestRepository userTestRepository;

    public Optional<UserTest> findByTitle(String title){
        ExampleMatcher matcher=ExampleMatcher.matching().withMatcher("title",exact().ignoreCase());
        Optional<UserTest> userTest =userTestRepository.findOne(Example.of(UserTest.builder().title(title).build(),matcher));
        log.info("userTest Found:{}",userTest);
        return userTest;
    }

    public Optional<UserTest> findUserTestById(Long id){
        Optional<UserTest> userTest =userTestRepository.findById(id);
        log.info("userTest Found:{}",userTest);
        return userTest;
    }

    public UserTest createUserTest(UserTest newUserTest) {
        log.info("UserTest:{}",newUserTest);
        UserTest saved = userTestRepository.save(newUserTest);
        log.info("New UserTest:{}", saved);
        return saved;
    }

    public void removeUserTest(Long id){
        userTestRepository.deleteById(id);
    }

    public void updateUserTest(UserTest newUserTest){
        userTestRepository.save(newUserTest);
    }

    public Page<UserTest> allUserTest(UserTest UserTest,Pageable pageable){
        Example<UserTest> example = Example.of(UserTest);
        return userTestRepository.findAll(example,pageable);
    }
}

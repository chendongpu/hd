package com.example.demo.service;


import com.example.demo.model.UserTestQuestion;
import com.example.demo.model.UserTestReport;
import com.example.demo.repository.UserTestReportRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact;

@Slf4j
@Service
public class UserTestReportService {

    @Autowired
    private UserTestReportRepository userTestReportRepository;

    public List<UserTestReport> findByTestid(Long testid){
        ExampleMatcher matcher=ExampleMatcher.matching().withMatcher("testid",exact().ignoreCase());
        List<UserTestReport> userTestReportList =userTestReportRepository.findAll(Example.of(UserTestReport.builder().testid(testid).build(),matcher));
        log.info("userTestReportList Found:{}",userTestReportList);
        return userTestReportList;
    }

    public Optional<UserTestReport> findUserTestReportById(Long id){
        Optional<UserTestReport> userTestReport =userTestReportRepository.findById(id);
        log.info("userTestReport Found:{}",userTestReport);
        return userTestReport;
    }

    public UserTestReport createUserTestReport(UserTestReport newUserTestReport) {
        log.info("UserTestReport:{}",newUserTestReport);
        UserTestReport saved = userTestReportRepository.save(newUserTestReport);
        log.info("New UserTestReport:{}", saved);
        return saved;
    }


    public List<UserTestReport> createBatchUserTestReport(List<UserTestReport> userTestReportList) {
        List<UserTestReport> saved = userTestReportRepository.saveAll(userTestReportList);
        log.info("New UserTestReport:{}", saved);
        return saved;
    }


    public void removeUserTestReport(Long id){
        userTestReportRepository.deleteById(id);
    }

    public void removeBatchUserTestReport(List<UserTestReport> userTestReportList) {
        userTestReportRepository.deleteAll(userTestReportList);
    }
}

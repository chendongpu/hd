package com.example.back.service;

import com.example.back.model.Admin;
import com.example.back.repository.AdminRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import java.util.Optional;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact;

@Slf4j
@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public Optional<Admin> findByUsername(String username){
        ExampleMatcher matcher= ExampleMatcher.matching().withMatcher("username",exact().ignoreCase());
        Optional<Admin> admin =adminRepository.findOne(Example.of(Admin.builder().username(username).build(),matcher));
        log.info("User Found:{}",admin);
        return admin;
    }


    public Optional<Admin> findAdminById(Long id){
        Optional<Admin> admin =adminRepository.findById(id);
        log.info("User Found:{}",admin);
        return admin;
    }

}

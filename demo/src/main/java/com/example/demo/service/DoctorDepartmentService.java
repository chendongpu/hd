package com.example.demo.service;


import com.example.demo.model.DoctorDepartment;
import com.example.demo.repository.DoctorDepartmentRepository;
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
public class DoctorDepartmentService {

    @Autowired
    private DoctorDepartmentRepository dotorDepartmentRepository;

    public Optional<DoctorDepartment> findByTitle(String title){
        ExampleMatcher matcher=ExampleMatcher.matching().withMatcher("title",exact().ignoreCase());
        Optional<DoctorDepartment> user =dotorDepartmentRepository.findOne(Example.of(DoctorDepartment.builder().title(title).build(),matcher));
        log.info("dotorDepartment Found:{}",user);
        return user;
    }


    public Optional<DoctorDepartment> findDoctorDepartmentById(Long id){
        Optional<DoctorDepartment> dotorDepartment =dotorDepartmentRepository.findById(id);
        log.info("dotorDepartment Found:{}",dotorDepartment);
        return dotorDepartment;
    }

    public DoctorDepartment createDoctorDepartment(DoctorDepartment dotorDepartment) {
        log.info("dotorDepartment:{}",dotorDepartment);
        DoctorDepartment saved = dotorDepartmentRepository.save(dotorDepartment);
        log.info("New DoctorDepartment:{}", saved);
        return saved;
    }

    public void updateDoctorDepartment(DoctorDepartment newDoctorDepartment){
        dotorDepartmentRepository.save(newDoctorDepartment);
    }

    public void removeDoctorDepartment(Long id){
        dotorDepartmentRepository.deleteById(id);
    }


    public Page<DoctorDepartment> allDoctorDepartment(DoctorDepartment dotorDepartment, Pageable pageable){
        Example<DoctorDepartment> example = Example.of(dotorDepartment);
        return dotorDepartmentRepository.findAll(example,pageable);
    }



}

package com.example.back.service;


import com.example.back.model.DoctorDepartment;
import com.example.back.repository.DoctorDepartmentRepository;
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
    private DoctorDepartmentRepository doctorDepartmentRepository;

    public Optional<DoctorDepartment> findByTitle(String title){
        ExampleMatcher matcher= ExampleMatcher.matching().withMatcher("title",exact().ignoreCase());
        Optional<DoctorDepartment> user =doctorDepartmentRepository.findOne(Example.of(DoctorDepartment.builder().title(title).build(),matcher));
        log.info("doctorDepartment Found:{}",user);
        return user;
    }


    public Optional<DoctorDepartment> findDoctorDepartmentById(Long id){
        Optional<DoctorDepartment> doctorDepartment =doctorDepartmentRepository.findById(id);
        log.info("doctorDepartment Found:{}",doctorDepartment);
        return doctorDepartment;
    }

    public DoctorDepartment createDoctorDepartment(DoctorDepartment doctorDepartment) {
        log.info("doctorDepartment:{}",doctorDepartment);
        DoctorDepartment saved = doctorDepartmentRepository.save(doctorDepartment);
        log.info("New DoctorDepartment:{}", saved);
        return saved;
    }

    public void updateDoctorDepartment(DoctorDepartment newDoctorDepartment){
        doctorDepartmentRepository.save(newDoctorDepartment);
    }

    public void removeDoctorDepartment(Long id){
        doctorDepartmentRepository.deleteById(id);
    }


    public Page<DoctorDepartment> allDoctorDepartment(DoctorDepartment doctorDepartment, Pageable pageable){
        Example<DoctorDepartment> example = Example.of(doctorDepartment);
        return doctorDepartmentRepository.findAll(example,pageable);
    }



}

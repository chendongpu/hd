package com.example.demo.service;

import com.example.demo.model.PointTask;
import com.example.demo.repository.PointTaskRepository;
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
public class PointTaskService {

    @Autowired
    private PointTaskRepository pointTaskRepository;

    public Optional<PointTask> findByTitle(String title){
        ExampleMatcher matcher=ExampleMatcher.matching().withMatcher("title",exact().ignoreCase());
        Optional<PointTask> user =pointTaskRepository.findOne(Example.of(PointTask.builder().title(title).build(),matcher));
        log.info("pointTask Found:{}",user);
        return user;
    }


    public Optional<PointTask> findPointTaskById(Long id){
        Optional<PointTask> pointTask =pointTaskRepository.findById(id);
        log.info("pointTask Found:{}",pointTask);
        return pointTask;
    }

    public PointTask createPointTask(PointTask pointTask) {
        log.info("pointTask:{}",pointTask);
        PointTask saved = pointTaskRepository.save(pointTask);
        log.info("New PointTask:{}", saved);
        return saved;
    }

    public void updatePointTask(PointTask newPointTask){
        pointTaskRepository.save(newPointTask);
    }

    public void removePointTask(Long id){
        pointTaskRepository.deleteById(id);
    }


    public Page<PointTask> allPointTask(PointTask pointTask, Pageable pageable){
        Example<PointTask> example = Example.of(pointTask);
        return pointTaskRepository.findAll(example,pageable);
    }



}

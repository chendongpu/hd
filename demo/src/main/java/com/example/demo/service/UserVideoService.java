package com.example.demo.service;

import com.example.demo.model.UserVideo;
import com.example.demo.repository.UserVideoRepository;
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
public class UserVideoService {

    @Autowired
    private UserVideoRepository userVideoRepository;

    public Optional<UserVideo> findByTitle(String title){
        ExampleMatcher matcher=ExampleMatcher.matching().withMatcher("title",exact().ignoreCase());
        Optional<UserVideo> userVideo =userVideoRepository.findOne(Example.of(UserVideo.builder().title(title).build(),matcher));
        log.info("userVideo Found:{}",userVideo);
        return userVideo;
    }

    public Optional<UserVideo> findUserVideoById(Long id){
        Optional<UserVideo> userVideo =userVideoRepository.findById(id);
        log.info("userVideo Found:{}",userVideo);
        return userVideo;
    }

    public UserVideo createUserVideo(UserVideo newUserVideo) {
        log.info("UserVideo:{}",newUserVideo);
        UserVideo saved = userVideoRepository.save(newUserVideo);
        log.info("New UserVideo:{}", saved);
        return saved;
    }

    public void removeUserVideo(Long id){
        userVideoRepository.deleteById(id);
    }

    public void updateUserVideo(UserVideo newUserVideo){
        userVideoRepository.save(newUserVideo);
    }

    public Page<UserVideo> allUserVideo(UserVideo UserVideo,Pageable pageable){
        Example<UserVideo> example = Example.of(UserVideo);
        return userVideoRepository.findAll(example,pageable);
    }
}

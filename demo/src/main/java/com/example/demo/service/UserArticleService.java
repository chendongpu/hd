package com.example.demo.service;

import com.example.demo.model.UserArticle;
import com.example.demo.repository.UserArticleRepository;
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
public class UserArticleService {

    @Autowired
    private UserArticleRepository userArticleRepository;

    public Optional<UserArticle> findByTitle(String title){
        ExampleMatcher matcher=ExampleMatcher.matching().withMatcher("title",exact().ignoreCase());
        Optional<UserArticle> userArticle =userArticleRepository.findOne(Example.of(UserArticle.builder().title(title).build(),matcher));
        log.info("userArticle Found:{}",userArticle);
        return userArticle;
    }

    public Optional<UserArticle> findUserArticleById(Long id){
        Optional<UserArticle> userArticle =userArticleRepository.findById(id);
        log.info("userArticle Found:{}",userArticle);
        return userArticle;
    }

    public UserArticle createUserArticle(UserArticle newUserArticle) {
        log.info("UserArticle:{}",newUserArticle);
        UserArticle saved = userArticleRepository.save(newUserArticle);
        log.info("New UserArticle:{}", saved);
        return saved;
    }

    public void removeUserArticle(Long id){
        userArticleRepository.deleteById(id);
    }

    public void updateUserArticle(UserArticle newUserArticle){
        userArticleRepository.save(newUserArticle);
    }

    public Page<UserArticle> allUserArticle(UserArticle UserArticle,Pageable pageable){
        Example<UserArticle> example = Example.of(UserArticle);
        return userArticleRepository.findAll(example,pageable);
    }
}

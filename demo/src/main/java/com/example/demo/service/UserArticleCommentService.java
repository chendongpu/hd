package com.example.demo.service;


import com.example.demo.model.UserArticleComment;
import com.example.demo.repository.UserArticleCommentRepository;
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
public class UserArticleCommentService {

    @Autowired
    private UserArticleCommentRepository userArticleCommentRepository;


    public List<UserArticleComment> findByArticle(Long articleid){
        ExampleMatcher matcher=ExampleMatcher.matching().withMatcher("articleid",exact().ignoreCase());
        List<UserArticleComment> userArticleCommentList =userArticleCommentRepository.findAll(Example.of(UserArticleComment.builder().articleid(articleid).build(),matcher));
        log.info("userArticleCommentList Found:{}",userArticleCommentList);
        return userArticleCommentList;
    }

    public Optional<UserArticleComment> findUserArticleCommentById(Long id){
        Optional<UserArticleComment> userArticleComment =userArticleCommentRepository.findById(id);
        log.info("userArticleComment Found:{}",userArticleComment);
        return userArticleComment;
    }

    public UserArticleComment createUserArticleComment(UserArticleComment newUserArticleComment) {
        log.info("UserArticleComment:{}",newUserArticleComment);
        UserArticleComment saved = userArticleCommentRepository.save(newUserArticleComment);
        log.info("New UserArticleComment:{}", saved);
        return saved;
    }

    public void removeUserArticleComment(Long id){
        userArticleCommentRepository.deleteById(id);
    }

    public Page<UserArticleComment> allUserArticleComment(UserArticleComment UserArticleComment,Pageable pageable){
        Example<UserArticleComment> example = Example.of(UserArticleComment);
        return userArticleCommentRepository.findAll(example,pageable);
    }
}

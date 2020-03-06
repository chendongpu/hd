package com.example.back.service;


import com.example.back.model.DoctorDepartment;
import com.example.back.model.UserArticle;
import com.example.back.model.UserArticleComment;
import com.example.back.repository.UserArticleCommentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact;

@Slf4j
@Service
public class UserArticleCommentService {

    @Autowired
    private UserArticleCommentRepository userArticleCommentRepository;


    public List<UserArticleComment> findByArticle(Long articleid){
        ExampleMatcher matcher= ExampleMatcher.matching().withMatcher("articleid",exact().ignoreCase());
        List<UserArticleComment> userArticleCommentList =userArticleCommentRepository.findAll(Example.of(UserArticleComment.builder().articleid(articleid).build(),matcher));
        log.info("userArticleCommentList Found:{}",userArticleCommentList);
        return userArticleCommentList;
    }

    public List<UserArticleComment> findByPid(Long pid){
        ExampleMatcher matcher= ExampleMatcher.matching().withMatcher("pid",exact().ignoreCase());
        List<UserArticleComment> userArticleCommentList =userArticleCommentRepository.findAll(Example.of(UserArticleComment.builder().pid(pid).build(),matcher));
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

    public void updateUserArticleComment(UserArticleComment userArticleComment) {
        userArticleCommentRepository.save(userArticleComment);
    }

    public void removeUserArticleComment(Long id){
        userArticleCommentRepository.deleteById(id);
    }

    public Page<UserArticleComment> allUserArticleComment(UserArticleComment UserArticleComment, Pageable pageable){
        Example<UserArticleComment> example = Example.of(UserArticleComment);
        return userArticleCommentRepository.findAll(example,pageable);
    }


    public Page<UserArticleComment> findByKeyword(String keyword, Pageable pageable){
        UserArticleComment userArticleComment =new UserArticleComment();
        userArticleComment.setComment(keyword);
        ExampleMatcher matcher = ExampleMatcher.matching().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);  //改变默认字符串匹配为:模糊查询

        Example<UserArticleComment> example = Example.of(userArticleComment,matcher);
        return  userArticleCommentRepository.findAll(example,pageable);
    }
}

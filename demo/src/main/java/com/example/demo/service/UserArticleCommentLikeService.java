package com.example.demo.service;


import com.example.demo.model.UserArticleCollect;
import com.example.demo.model.UserArticleCommentLike;
import com.example.demo.repository.UserArticleCommentLikeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserArticleCommentLikeService {

    @Autowired
    private UserArticleCommentLikeRepository userArticleCommentLikeRepository;


    public UserArticleCommentLike createUserArticleCommentLike(UserArticleCommentLike userArticleCommentLike) {
        log.info("UserArticleCommentLike:{}",userArticleCommentLike);
        UserArticleCommentLike saved = userArticleCommentLikeRepository.save(userArticleCommentLike);
        log.info("New UserArticleRead:{}", saved);
        return saved;
    }

    public Optional<UserArticleCommentLike> findOneUserArticleCommentLike(Long userid, Long articleid,Long commentid){
        UserArticleCommentLike userArticleCommentLike = UserArticleCommentLike.builder().userid(userid).commentid(commentid).build();
        Example<UserArticleCommentLike> example = Example.of(userArticleCommentLike);
        Optional<UserArticleCommentLike> userArticleCommentLikeOptional= userArticleCommentLikeRepository.findOne(example);
        return userArticleCommentLikeOptional;
    }



    public Integer findCommentLikeNumByCommentid(Long commentid){
        UserArticleCommentLike userArticleCommentLike = UserArticleCommentLike.builder().commentid(commentid).build();
        Example<UserArticleCommentLike> example = Example.of(userArticleCommentLike);
        List<UserArticleCommentLike> userArticleCommentLikeList= userArticleCommentLikeRepository.findAll(example);
        return userArticleCommentLikeList.size();
    }

    public void removeUserArticleCommentLike(UserArticleCommentLike userArticleCommentLike){
        userArticleCommentLikeRepository.delete(userArticleCommentLike);
    }


}

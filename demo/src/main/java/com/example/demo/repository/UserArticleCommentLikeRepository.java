package com.example.demo.repository;


import com.example.demo.model.UserArticleCommentLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserArticleCommentLikeRepository extends JpaRepository<UserArticleCommentLike,Long>, JpaSpecificationExecutor<UserArticleCommentLike> {

}

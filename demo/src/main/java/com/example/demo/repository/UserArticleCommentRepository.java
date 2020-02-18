package com.example.demo.repository;


import com.example.demo.model.UserArticleComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserArticleCommentRepository extends JpaRepository<UserArticleComment,Long>, JpaSpecificationExecutor<UserArticleComment> {

}

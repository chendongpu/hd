package com.example.back.repository;


import com.example.back.model.UserArticleComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserArticleCommentRepository extends JpaRepository<UserArticleComment,Long>, JpaSpecificationExecutor<UserArticleComment> {

}

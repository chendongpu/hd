package com.example.back.repository;


import com.example.back.model.UserArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserArticleRepository extends JpaRepository<UserArticle,Long>, JpaSpecificationExecutor<UserArticle> {

}

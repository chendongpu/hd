package com.example.demo.repository;


import com.example.demo.model.UserArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserArticleRepository extends JpaRepository<UserArticle,Long>, JpaSpecificationExecutor<UserArticle> {

}

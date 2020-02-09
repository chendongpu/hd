package com.example.demo.repository;


import com.example.demo.model.UserArticle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserArticleRepository extends JpaRepository<UserArticle,Long> {

}

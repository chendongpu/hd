package com.example.doctor.repository;


import com.example.doctor.model.UserArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserArticleRepository extends JpaRepository<UserArticle,Long>, JpaSpecificationExecutor<UserArticle> {

}

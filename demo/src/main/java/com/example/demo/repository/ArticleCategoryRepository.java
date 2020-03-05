package com.example.demo.repository;

import com.example.demo.model.ArticleCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ArticleCategoryRepository extends JpaRepository<ArticleCategory,Long>, JpaSpecificationExecutor<ArticleCategory> {

}

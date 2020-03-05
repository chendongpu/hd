package com.example.doctor.repository;

import com.example.doctor.model.ArticleCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ArticleCategoryRepository extends JpaRepository<ArticleCategory,Long>, JpaSpecificationExecutor<ArticleCategory> {

}

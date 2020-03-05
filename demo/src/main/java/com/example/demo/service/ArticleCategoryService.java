package com.example.demo.service;


import com.example.demo.model.ArticleCategory;
import com.example.demo.repository.ArticleCategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact;

@Slf4j
@Service
public class ArticleCategoryService {

    @Autowired
    private ArticleCategoryRepository articleCategoryRepository;

    public Optional<ArticleCategory> findByTitle(String title){
        ExampleMatcher matcher= ExampleMatcher.matching().withMatcher("title",exact().ignoreCase());
        Optional<ArticleCategory> user =articleCategoryRepository.findOne(Example.of(ArticleCategory.builder().title(title).build(),matcher));
        log.info("articleCategory Found:{}",user);
        return user;
    }


    public Optional<ArticleCategory> findArticleCategoryById(Long id){
        Optional<ArticleCategory> articleCategory =articleCategoryRepository.findById(id);
        log.info("articleCategory Found:{}",articleCategory);
        return articleCategory;
    }

    public ArticleCategory createArticleCategory(ArticleCategory articleCategory) {
        log.info("articleCategory:{}",articleCategory);
        ArticleCategory saved = articleCategoryRepository.save(articleCategory);
        log.info("New ArticleCategory:{}", saved);
        return saved;
    }

    public void updateArticleCategory(ArticleCategory newArticleCategory){
        articleCategoryRepository.save(newArticleCategory);
    }

    public void removeArticleCategory(Long id){
        articleCategoryRepository.deleteById(id);
    }


    public Page<ArticleCategory> allArticleCategory(ArticleCategory articleCategory, Pageable pageable){
        Example<ArticleCategory> example = Example.of(articleCategory);
        return articleCategoryRepository.findAll(example,pageable);
    }



}

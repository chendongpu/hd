package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;

import com.example.demo.handler.ResultBody;
import com.example.demo.model.ArticleCategory;
import com.example.demo.service.ArticleCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;


@Api(value = "/article_category", tags = "文章分类接口")
@Slf4j
@RestController
@RequestMapping("/article_category")
public class ArticleCategoryController {

    @Autowired
    private ArticleCategoryService articleCategoryService;

    @ApiOperation(value = "查询文章分类", notes = "limit表示每次查几条 page表示第几页")
    @PostMapping(value = "/all_article_category",params = "limit")
    public ResultBody allArticleCategory(Integer limit,Integer page){

        if (null == page || 0 == page){
            page = 1;
        }
        // 排序方式，这里是以“id”为标准进行降序
        Sort sort = Sort.by(Sort.Direction.DESC, "id");  // 这里的"id"是实体类的主键，记住一定要是实体类的属性，而不能是数据库的字段
        Pageable pageable = PageRequest.of(page - 1,limit,sort);


        ArticleCategory articleCategory = ArticleCategory.builder().build();
        JSONObject jsonObject = new JSONObject();
        Page<ArticleCategory> pagedd= articleCategoryService.allArticleCategory(articleCategory,pageable);
        jsonObject.put("list", pagedd.getContent());
        return ResultBody.success(jsonObject);

    }




}

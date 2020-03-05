package com.example.back.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.back.controller.request.NewArticleCategoryRequest;
import com.example.back.handler.BizException;
import com.example.back.handler.ResultBody;
import com.example.back.jwt.CheckToken;
import com.example.back.model.ArticleCategory;
import com.example.back.service.ArticleCategoryService;
import com.example.back.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;


@Api(value = "/article_category", tags = "后台文章分类接口")
@Slf4j
@RestController
@RequestMapping("/article_category")
public class ArticleCategoryController {

    @Autowired
    private ArticleCategoryService articleCategoryService;

    @Autowired
    private UserService userService;

    @Autowired
    HttpServletRequest request;


    //{"title":"饮食健康"}
    @ApiOperation(value = "添加文章分类", notes = "newArticleCategoryReq 为文章分类的json")
    @CheckToken
    @PostMapping("/create_article_category")
    public ResultBody createArticleCategory( @Valid @RequestBody NewArticleCategoryRequest newArticleCategoryReq) {
        Optional<ArticleCategory> articleCategoryOption=articleCategoryService.findByTitle(newArticleCategoryReq.getTitle());
        if (articleCategoryOption.isPresent()) {
            throw new BizException("-1","该文章分类已存在");
        }

        log.info("Receive new ArticleCategory{}",newArticleCategoryReq);
        ArticleCategory newArticleCategory=new ArticleCategory();


        newArticleCategory.setTitle(newArticleCategoryReq.getTitle());

        log.info("ArticleCategory{}",newArticleCategory);
        ArticleCategory saved= articleCategoryService.createArticleCategory(newArticleCategory);
        return ResultBody.success(saved);
    }


    //{"title":"饮食健康","id":"1"}
    @ApiOperation(value = "删除文章分类", notes = "传入分类id")
    @CheckToken
    @GetMapping(value = "/remove_article_category",params = "id")
    public ResultBody removeArticleCategory(@RequestParam Long id) {
        Optional<ArticleCategory> articleCategoryOption=articleCategoryService.findArticleCategoryById(id);
        if(!articleCategoryOption.isPresent()){
            throw new BizException("-1","文章分类不存在");
        }

        articleCategoryService.removeArticleCategory(id);
        return ResultBody.success("文章分类删除成功");
    }



    @ApiOperation(value = "修改文章分类", notes = "newArticleCategoryReq 为文章分类的json")
    //{"title":"儿童科"}
    @CheckToken
    @PostMapping(value = "/update_article_category")
    public ResultBody updateArticleCategory(@Valid @RequestBody NewArticleCategoryRequest newArticleCategoryReq){

        if(null==newArticleCategoryReq.getId() || 0==newArticleCategoryReq.getId() ){
            throw new BizException("-1","用户id不能为空");
        }

        Optional<ArticleCategory> articleCategoryOption=articleCategoryService.findArticleCategoryById(newArticleCategoryReq.getId());
        if(!articleCategoryOption.isPresent()){
            throw new BizException("-1","文章分类不存在");
        }

        ArticleCategory articleCategoryBase = articleCategoryOption.get();
        articleCategoryBase.setTitle(newArticleCategoryReq.getTitle());
        articleCategoryService.updateArticleCategory(articleCategoryBase);
        return ResultBody.success("文章分类修改成功");
    }


    @ApiOperation(value = "查询文章分类", notes = "limit表示每次查几条 page表示第几页")
    @CheckToken
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

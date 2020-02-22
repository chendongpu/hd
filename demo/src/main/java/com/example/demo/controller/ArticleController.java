package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.controller.response.ArticleResponse;
import com.example.demo.controller.response.DoctorResponse;
import com.example.demo.handler.ResultBody;
import com.example.demo.model.DoctorDepartment;
import com.example.demo.model.User;
import com.example.demo.model.UserArticle;
import com.example.demo.service.UserArticleService;
import com.example.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserArticleService userArticleService;

    @Autowired
    HttpServletRequest request;


    //查询文章列表
    @PostMapping(value = "/all_article",params = "limit")
    public ResultBody allArticle(Integer limit,Integer page){

        if (null == page || 0 == page){
            page = 1;
        }
        // 排序方式，这里是以“id”为标准进行降序
        Sort sort = Sort.by(Sort.Direction.DESC, "id");  // 这里的"id"是实体类的主键，记住一定要是实体类的属性，而不能是数据库的字段
        Pageable pageable = PageRequest.of(page - 1,limit,sort);


        UserArticle article = UserArticle.builder().state(1).build();
        JSONObject jsonObject = new JSONObject();
        Page<UserArticle> pageArticle = userArticleService.allUserArticle(article,pageable);

        List<ArticleResponse> articleResponseList =new ArrayList<>();

        List<UserArticle> articleList = pageArticle.getContent();

        for(UserArticle userArticle:articleList){

            Optional<User> userOptional = userService.findUserById(userArticle.getUserid());

            User user = userOptional.get();

            ArticleResponse articleResponse=new ArticleResponse();

            articleResponse.setArticleUser(user);

            articleResponse.setUserArticle(userArticle);

            articleResponseList.add(articleResponse);
        }

        jsonObject.put("list", articleResponseList);
        return ResultBody.success(jsonObject);

    }

    //查询文章列表
    @PostMapping(value = "/find_article",params = "limit")
    public ResultBody findArticle(Integer limit,Integer page,@Nullable String keyword){

        if (null == page || 0 == page){
            page = 1;
        }
        // 排序方式，这里是以“id”为标准进行降序
        Sort sort = Sort.by(Sort.Direction.DESC, "id");  // 这里的"id"是实体类的主键，记住一定要是实体类的属性，而不能是数据库的字段
        Pageable pageable = PageRequest.of(page - 1,limit,sort);


        UserArticle article = UserArticle.builder().state(1).build();
        JSONObject jsonObject = new JSONObject();

        Page<UserArticle> pageArticle;

        if(null!=keyword && !"".equals(keyword)){
            pageArticle = userArticleService.findByKeyword(keyword,pageable);
        }else{
            pageArticle = userArticleService.allUserArticle(article,pageable);
        }



        List<ArticleResponse> articleResponseList =new ArrayList<>();

        List<UserArticle> articleList = pageArticle.getContent();

        for(UserArticle userArticle:articleList){

            Optional<User> userOptional = userService.findUserById(userArticle.getUserid());

            User user = userOptional.get();

            ArticleResponse articleResponse=new ArticleResponse();

            articleResponse.setArticleUser(user);

            articleResponse.setUserArticle(userArticle);

            articleResponseList.add(articleResponse);
        }

        jsonObject.put("list", articleResponseList);
        return ResultBody.success(jsonObject);

    }






}

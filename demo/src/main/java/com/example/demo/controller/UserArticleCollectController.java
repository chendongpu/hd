package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.example.demo.handler.BizException;
import com.example.demo.handler.ResultBody;
import com.example.demo.jwt.CheckToken;
import com.example.demo.model.UserArticle;
import com.example.demo.model.UserArticleCollect;
import com.example.demo.service.UserArticleService;
import com.example.demo.service.UserArticleCollectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/user_article_collect")
public class UserArticleCollectController {

    @Autowired
    private UserArticleCollectService userArticleCollectService;
    @Autowired
    private UserArticleService userArticleService;

    @Autowired
    HttpServletRequest request;

    //添加文章收藏
    @CheckToken
    @PostMapping("/create_user_article_collect")
    public ResultBody createUserArticleCollect( @RequestParam Integer aid) {
        JSONObject jsonObject = new JSONObject();
        Optional<UserArticle> concernOption=userArticleService.findUserArticleById(Long.parseLong(aid+""));
        if(!concernOption.isPresent()){
            throw new BizException("-1","该文章不存在");
        }

        String token = request.getHeader("token");
        // 获取 token 中的 user id
        log.info("token:{}",token);
        String userId= JWT.decode(token).getClaim("id").asString();
        Long userid =Long.parseLong(userId);

        UserArticleCollect userArticleCollect=new UserArticleCollect();
        userArticleCollect.setUserid(userid);
        userArticleCollect.setAid(Long.parseLong(aid+""));
        log.info("userArticleCollect{}",userArticleCollect);
        UserArticleCollect saved= userArticleCollectService.createUserArticleCollect(userArticleCollect);
        return ResultBody.success(saved) ;
    }

    //取消文章收藏
    @CheckToken
    @PostMapping("/remove_user_article_collect")
    public ResultBody removeUserArticleCollect(@RequestParam Integer aid){
        String token = request.getHeader("token");
        // 获取 token 中的 user id
        log.info("token:{}",token);
        String userId= JWT.decode(token).getClaim("id").asString();
        Long userid =Long.parseLong(userId);
        Optional<UserArticleCollect> userArticleCollectOption=userArticleCollectService.findOneUserArticleCollect(userid,Long.parseLong(aid+""));
        if(!userArticleCollectOption.isPresent()){
            throw new BizException("-1","你还没有收藏");
        }
        userArticleCollectService.removeUserArticleCollect(userArticleCollectOption.get());
        return ResultBody.success("取消收藏成功") ;
    }

    //收藏列表
    @CheckToken
    @PostMapping(value = "/all_user_article_collect",params = "limit")
    public ResultBody allUserArticleCollect(Integer limit,Integer page){
        if (null == page || 0 == page){
            page = 1;
        }
        // 排序方式，这里是以“id”为标准进行降序
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(page - 1,limit,sort);
        String token = request.getHeader("token");
        // 获取 token 中的 user id
        log.info("token:{}",token);
        String userId= JWT.decode(token).getClaim("id").asString();
        Long userid =Long.parseLong(userId);
        UserArticleCollect userArticleCollect = UserArticleCollect.builder().userid(userid).build();
        JSONObject jsonObject = new JSONObject();
        Page<UserArticle> pageUser = userArticleCollectService.allUserArticleCollect(userArticleCollect,pageable);
        jsonObject.put("list", pageUser.toList());

        return ResultBody.success(jsonObject);
    }






}

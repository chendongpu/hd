package com.example.demo.controller;

import com.auth0.jwt.JWT;
import com.example.demo.handler.BizException;
import com.example.demo.handler.ResultBody;
import com.example.demo.jwt.CheckToken;
import com.example.demo.model.UserArticle;
import com.example.demo.model.UserArticleComment;
import com.example.demo.model.UserArticleCommentLike;
import com.example.demo.service.UserArticleCommentLikeService;
import com.example.demo.service.UserArticleCommentService;
import com.example.demo.service.UserArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/user_article_comment_like")
public class UserArticleCommentLikeController {

    @Autowired
    private UserArticleService userArticleService;

    @Autowired
    private UserArticleCommentService userArticleCommentService;

    @Autowired
    private UserArticleCommentLikeService uerArticleCommentLikeService;


    @Autowired
    HttpServletRequest request;

    //添加文章评论点赞记录
    @CheckToken
    @PostMapping("/create_user_article_comment_like")
    public ResultBody createUserArticleCommentLike( @RequestParam Long articleid,@RequestParam Long commentid) {

        Optional<UserArticle> concernOption=userArticleService.findUserArticleById(Long.parseLong(articleid+""));
        if(!concernOption.isPresent()){
            throw new BizException("-1","该文章不存在");
        }

        Optional<UserArticleComment> commentOptional=userArticleCommentService.findUserArticleCommentById(commentid);
        if(!commentOptional.isPresent()){
            throw new BizException("-1","评论不存在");
        }



        String token = request.getHeader("token");
        // 获取 token 中的 user id
        log.info("token:{}",token);
        String userId= JWT.decode(token).getClaim("id").asString();
        Long userid =Long.parseLong(userId);

        Optional<UserArticleCommentLike> userArticleCommentLikeOptional=uerArticleCommentLikeService.findOneUserArticleCommentLike(userid,articleid,commentid);
        if(userArticleCommentLikeOptional.isPresent()){
            throw new BizException("-1","你已经评论点赞过了");
        }

        UserArticleCommentLike userArticleCommentLike=new UserArticleCommentLike();
        userArticleCommentLike.setUserid(userid);
        userArticleCommentLike.setArticleid(articleid);
        userArticleCommentLike.setCommentid(commentid);
        log.info("userArticleCommentLike{}",userArticleCommentLike);
        UserArticleCommentLike saved= uerArticleCommentLikeService.createUserArticleCommentLike(userArticleCommentLike);
        return ResultBody.success(saved) ;
    }

    //删除文章评论点赞记录
    @CheckToken
    @PostMapping("/remove_user_article_comment_like")
    public ResultBody removeUserArticleCommentLike(@RequestParam Long articleid,@RequestParam Long commentid){

        Optional<UserArticle> concernOption=userArticleService.findUserArticleById(Long.parseLong(articleid+""));
        if(!concernOption.isPresent()){
            throw new BizException("-1","该文章不存在");
        }

        Optional<UserArticleComment> commentOptional=userArticleCommentService.findUserArticleCommentById(commentid);
        if(!commentOptional.isPresent()){
            throw new BizException("-1","评论不存在");
        }


        String token = request.getHeader("token");
        // 获取 token 中的 user id
        log.info("token:{}",token);
        String userId= JWT.decode(token).getClaim("id").asString();
        Long userid =Long.parseLong(userId);

        Optional<UserArticleCommentLike> userArticleCommentLikeOptional=uerArticleCommentLikeService.findOneUserArticleCommentLike(userid,articleid,commentid);
        if(!userArticleCommentLikeOptional.isPresent()){
            throw new BizException("-1","你还没有评论点赞记录");
        }
        uerArticleCommentLikeService.removeUserArticleCommentLike(userArticleCommentLikeOptional.get());
        return ResultBody.success("删除评论点赞记录成功") ;
    }








}

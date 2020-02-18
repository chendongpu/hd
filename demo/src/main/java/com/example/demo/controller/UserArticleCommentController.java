package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.example.demo.controller.request.NewUserArticleCommentRequest;
import com.example.demo.handler.BizException;
import com.example.demo.handler.ResultBody;
import com.example.demo.jwt.CheckToken;
import com.example.demo.model.UserArticle;
import com.example.demo.model.UserArticleComment;
import com.example.demo.service.UserArticleCommentService;
import com.example.demo.service.UserArticleService;
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

@Slf4j
@RestController
@RequestMapping("/user_article_comment")
public class UserArticleCommentController {

    @Autowired
    private UserArticleService userArticleService;

    @Autowired
    private UserArticleCommentService userArticleCommentService;

    @Autowired
    HttpServletRequest request;

    //添加任务文章评论
    @CheckToken
    @PostMapping(value = "/create_user_article_comment")
    public ResultBody createUserArticleComment(@Valid @RequestBody NewUserArticleCommentRequest newUserArticleCommentRequest) {
        Optional<UserArticle> userArticleOptional=userArticleService.findUserArticleById(newUserArticleCommentRequest.getArticleid());
        if(!userArticleOptional.isPresent()){
            throw new BizException("-1","文章不存在");
        }

        UserArticle task=userArticleOptional.get();

        String token = request.getHeader("token");
        // 获取 token 中的 user id
        log.info("token:{}",token);
        String userId= JWT.decode(token).getClaim("id").asString();
        Long userid =Long.parseLong(userId);
        log.info("userid:{}",userid);
        UserArticleComment newUserArticleComment=new UserArticleComment();

        newUserArticleComment.setUserid(userid);
        newUserArticleComment.setArticleid(newUserArticleCommentRequest.getArticleid());

        newUserArticleComment.setComment(newUserArticleCommentRequest.getComment());

        UserArticleComment saved= userArticleCommentService.createUserArticleComment(newUserArticleComment);

        return ResultBody.success(saved);
    }




    //删除任务文章评论
    @CheckToken
    @GetMapping(value = "/remove_user_article_comment",params = "id")
    public ResultBody removeUserArticleComment(@RequestParam  Long id) {
        Optional<UserArticleComment> userTaskOption=userArticleCommentService.findUserArticleCommentById(id);
        if(!userTaskOption.isPresent()){
            throw new BizException("-1","任务文章评论不存在");
        }

        userArticleCommentService.removeUserArticleComment(id);
        return ResultBody.success("任务文章评论删除成功");
    }


    

    //查询任务文章评论
    @CheckToken
    @PostMapping(value = "/all_user_article_comment",params = "limit")
    public ResultBody allUserArticleComment(Integer limit,Integer page){

        if (null == page || 0 == page){
            page = 1;
        }
        // 排序方式，这里是以“id”为标准进行降序
        Sort sort = Sort.by(Sort.Direction.DESC, "id");  // 这里的"id"是实体类的主键，记住一定要是实体类的属性，而不能是数据库的字段
        Pageable pageable = PageRequest.of(page - 1,limit,sort);
        String token = request.getHeader("token");
        // 获取 token 中的 user id
        log.info("token:{}",token);
        String userId= JWT.decode(token).getClaim("id").asString();
        Long userid =Long.parseLong(userId);
        UserArticleComment userArticleComment = UserArticleComment.builder().userid(userid).build();
        JSONObject jsonObject = new JSONObject();
        Page<UserArticleComment> pageTask = userArticleCommentService.allUserArticleComment(userArticleComment,pageable);
        jsonObject.put("list", pageTask.toList());
        return ResultBody.success(jsonObject);

    }






}




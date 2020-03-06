package com.example.back.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.back.jwt.CheckToken;
import com.example.back.handler.BizException;
import com.example.back.handler.ResultBody;
import com.example.back.model.UserArticleComment;
import com.example.back.service.UserArticleCommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Api(value = "/user_article_comment", tags = "用户文章评论")
@Slf4j
@RestController
@RequestMapping("/user_article_comment")
public class UserArticleCommentController {


    @Autowired
    private UserArticleCommentService userArticleCommentService;


    @ApiOperation(value = "删除文章评论", notes = "传入文章评论id")
    @CheckToken
    @GetMapping(value = "/remove_user_article_comment",params = "id")
    public ResultBody removeUserArticleComment(@RequestParam Long id) {
        Optional<UserArticleComment> userTaskOption=userArticleCommentService.findUserArticleCommentById(id);
        if(!userTaskOption.isPresent()){
            throw new BizException("-1","文章评论不存在");
        }

        userArticleCommentService.removeUserArticleComment(id);
        return ResultBody.success("文章评论删除成功");
    }

    @ApiOperation(value = "修改文章评论", notes = "可以修改评论的审核状态")
    //{"state":1,"id":8}
    @CheckToken
    @PostMapping(value = "/update_user_article_comment")
    public ResultBody updateUserArticleComment(@Valid @RequestBody UserArticleComment userArticleComment){

        if(null == userArticleComment.getId() || 0==userArticleComment.getId()){
            throw new BizException("-1","文章id不能为空");
        }

        Optional<UserArticleComment> userArticleCommentOptional=userArticleCommentService.findUserArticleCommentById(userArticleComment.getId());
        if(!userArticleCommentOptional.isPresent() ){
            throw new BizException("-1","文章评论不存在");
        }
        UserArticleComment uac =userArticleCommentOptional.get();
        if(!(null==userArticleComment.getState())){
            uac.setState(userArticleComment.getState());
        }

        userArticleCommentService.updateUserArticleComment(uac);
        return ResultBody.success("文章评论修改成功");
    }


    @ApiOperation(value = "查询文章评论", notes = "limit表示每次查几条 page表示第几页")
    @CheckToken
    @PostMapping(value = "/all_user_article_comment",params = "limit")
    public ResultBody allUserArticleComment(Integer limit,Integer page){

        if (null == page || 0 == page){
            page = 1;
        }
        // 排序方式，这里是以“id”为标准进行降序
        Sort sort = Sort.by(Sort.Direction.DESC, "id");  // 这里的"id"是实体类的主键，记住一定要是实体类的属性，而不能是数据库的字段
        Pageable pageable = PageRequest.of(page - 1,limit,sort);

        UserArticleComment userArticleComment = UserArticleComment.builder().build();

        JSONObject jsonObject = new JSONObject();

        Page<UserArticleComment> pageTask = userArticleCommentService.allUserArticleComment(userArticleComment,pageable);

        List<UserArticleComment>  userArticleCommentList = pageTask.getContent();

        jsonObject.put("list", userArticleCommentList);

        return ResultBody.success(jsonObject);

    }


    @ApiOperation(value = "根据关键字查询所有评论", notes = "limit表示每次查几条 page表示第几页keyword表示关键字")
    @CheckToken
    @PostMapping(value = "/find_user_article_comment",params = "limit")
    public ResultBody findUserArticleComment(Integer limit,Integer page,@Nullable String keyword){

        if (null == page || 0 == page){
            page = 1;
        }
        // 排序方式，这里是以“id”为标准进行降序
        Sort sort = Sort.by(Sort.Direction.DESC, "id");  // 这里的"id"是实体类的主键，记住一定要是实体类的属性，而不能是数据库的字段
        Pageable pageable = PageRequest.of(page - 1,limit,sort);


        UserArticleComment userArticleComment = UserArticleComment.builder().build();
        JSONObject jsonObject = new JSONObject();

        Page<UserArticleComment> pageArticleComment;

        if(null!=keyword && !"".equals(keyword)){
            pageArticleComment = userArticleCommentService.findByKeyword(keyword,pageable);
        }else{
            pageArticleComment = userArticleCommentService.allUserArticleComment(userArticleComment,pageable);
        }

        List<UserArticleComment>  userArticleCommentList = pageArticleComment.getContent();

        jsonObject.put("list", userArticleCommentList);
        return ResultBody.success(jsonObject);

    }






}




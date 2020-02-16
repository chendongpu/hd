package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.example.demo.controller.request.NewUserArticleRequest;
import com.example.demo.handler.BizException;
import com.example.demo.handler.ResultBody;
import com.example.demo.jwt.CheckToken;
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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/user_article")
public class UserArticleController {

    @Autowired
    private UserArticleService userArticleService;

    @Autowired
    private UserService userService;

    @Autowired
    HttpServletRequest request;

    //添加文章
    //{"title":"武汉,加油","img":"/imgupload/d8744c4f60894af59ee233c4aaccba4e.jpg","content":"武汉一定要挺住，向白衣天使致敬，你们辛苦了，熬过这个艰难的时刻，武汉加油！","author":"陈东谱","keyword":"武汉"}
    @CheckToken
    @PostMapping("/create_user_article")
    public ResultBody createUserArticle( @Valid @RequestBody NewUserArticleRequest newUserArticleReq) {
        Optional<UserArticle> userArticleOption=userArticleService.findByTitle(newUserArticleReq.getTitle());
        if (userArticleOption.isPresent()) {
            throw new BizException("-1","文章已存在");
        }

        log.info("Receive new UserArticle{}",newUserArticleReq);
        UserArticle newUserArticle=new UserArticle();
        String token = request.getHeader("token");
        // 获取 token 中的 user id
        log.info("token:{}",token);
        String userId= JWT.decode(token).getClaim("id").asString();
        Long userid =Long.parseLong(userId);
        newUserArticle.setUserid(userid);
        newUserArticle.setTitle(newUserArticleReq.getTitle());
        newUserArticle.setImg(newUserArticleReq.getImg());
        newUserArticle.setContent(newUserArticleReq.getContent());
        newUserArticle.setAuthor(newUserArticleReq.getAuthor());
        newUserArticle.setKeyword(newUserArticleReq.getKeyword());
        userArticleService.updateUserArticle(newUserArticle);

        log.info("UserArticle{}",newUserArticle);
        UserArticle saved= userArticleService.createUserArticle(newUserArticle);
        return ResultBody.success(saved);
    }


    //删除文章
    @CheckToken
    @GetMapping(value = "/remove_user_article",params = "id")
    public ResultBody removeUserArticle(@RequestParam  Long id) {
        Optional<UserArticle> userArticleOption=userArticleService.findUserArticleById(id);
        if(!userArticleOption.isPresent()){
            throw new BizException("-1","文章不存在");
        }

        userArticleService.removeUserArticle(id);
        return ResultBody.success("文章删除成功");
    }


    //修改文章
    //{"title":"武汉,加油,中国加油","img":"/imgupload/d8744c4f60894af59ee233c4aaccba4e.jpg","content":"武汉一定要挺住，向白衣天使致敬，你们辛苦了，熬过这个艰难的时刻，武汉加油！","author":"陈东谱","keyword":"武汉","id":9}
    @CheckToken
    @PostMapping(value = "/update_user_article")
    public ResultBody updateUserArticle(@Valid @RequestBody NewUserArticleRequest newUserArticleReq){

        if(null==newUserArticleReq.getId() || 0==newUserArticleReq.getId() ){
            throw new BizException("-1","文章id不能为空");
        }

        Optional<UserArticle> userArticleOption=userArticleService.findUserArticleById(newUserArticleReq.getId());
        if(!userArticleOption.isPresent()){
            throw new BizException("-1","文章不存在");
        }

        UserArticle userArticleBase = userArticleOption.get();
        userArticleBase.setTitle(newUserArticleReq.getTitle());
        userArticleBase.setImg(newUserArticleReq.getImg());
        userArticleBase.setContent(newUserArticleReq.getContent());
        userArticleBase.setAuthor(newUserArticleReq.getAuthor());
        userArticleBase.setKeyword(newUserArticleReq.getKeyword());
        userArticleService.updateUserArticle(userArticleBase);
        return ResultBody.success("文章修改成功");
    }

    //查询文章
    @CheckToken
    @PostMapping(value = "/all_user_article",params = "limit")
    public ResultBody allUserArticle(Integer limit,Integer page){

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
        UserArticle userArticle = UserArticle.builder().userid(userid).build();
        JSONObject jsonObject = new JSONObject();
        Page<UserArticle> pageArticle = userArticleService.allUserArticle(userArticle,pageable);
        jsonObject.put("list", pageArticle.toList());
        return ResultBody.success(jsonObject);

    }

    @CheckToken
    @GetMapping(value = "/one_user_article",params = "id")
    public ResultBody oneUserArticle(@RequestParam Long id){
        if(null==id|| 0==id){
            throw new BizException("-1","文章id不能为空");
        }

        Optional<UserArticle> userArticleOption=userArticleService.findUserArticleById(id);
        if(!userArticleOption.isPresent()){
            throw new BizException("-1","文章不存在");
        }


        return ResultBody.success(userArticleOption.get());
    }




}

package com.example.back.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.back.controller.request.NewUserArticleRequest;
import com.example.back.controller.response.ArticleResponse;
import com.example.back.handler.BizException;
import com.example.back.handler.ResultBody;
import com.example.back.jwt.CheckToken;
import com.example.back.model.User;
import com.example.back.model.UserArticle;
import com.example.back.service.UserArticleService;
import com.example.back.service.UserService;
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

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Api(value = "/article", tags = "用户文章")
@Slf4j
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private UserArticleService userArticleService;

    @Autowired
    private UserService userService;

    @Autowired
    HttpServletRequest request;


    @ApiOperation(value = "删除文章", notes = "传入文章id ")
    @CheckToken
    @GetMapping(value = "/remove_user_article",params = "id")
    public ResultBody removeUserArticle(@RequestParam Long id) {
        Optional<UserArticle> userArticleOption=userArticleService.findUserArticleById(id);
        if(!userArticleOption.isPresent()){
            throw new BizException("-1","文章不存在");
        }

        userArticleService.removeUserArticle(id);
        return ResultBody.success("文章删除成功");
    }


    @ApiOperation(value = "修改文章", notes = "修改文章标题、图片、内容、作者、关键字 ")
    //{"categoryid":1,"type":0,"title":"武汉,加油,中国加油","img":"/imgupload/d8744c4f60894af59ee233c4aaccba4e.jpg","content":"武汉一定要挺住，向白衣天使致敬，你们辛苦了，熬过这个艰难的时刻，武汉加油！","author":"陈东谱","keyword":"武汉","id":9}
    @CheckToken
    @PostMapping(value = "/update_article")
    public ResultBody updateArticle(@Valid @RequestBody NewUserArticleRequest newUserArticleReq){

        if(null==newUserArticleReq.getId() || 0==newUserArticleReq.getId() ){
            throw new BizException("-1","文章id不能为空");
        }

        Optional<UserArticle> userArticleOption=userArticleService.findUserArticleById(newUserArticleReq.getId());
        if(!userArticleOption.isPresent()){
            throw new BizException("-1","文章不存在");
        }

        UserArticle userArticleBase = userArticleOption.get();

        userArticleBase.setCategoryid(newUserArticleReq.getCategoryid());
        userArticleBase.setType(newUserArticleReq.getType());
        userArticleBase.setTitle(newUserArticleReq.getTitle());
        userArticleBase.setImg(newUserArticleReq.getImg());
        userArticleBase.setContent(newUserArticleReq.getContent());
        userArticleBase.setAuthor(newUserArticleReq.getAuthor());
        userArticleBase.setKeyword(newUserArticleReq.getKeyword());
        userArticleService.updateUserArticle(userArticleBase);
        return ResultBody.success("文章修改成功");
    }

    @ApiOperation(value = "查询所有文章", notes = "limit表示每次查几条 page表示第几页")
    @CheckToken
    @PostMapping(value = "/all_article",params = "limit")
    public ResultBody allArticle(Integer limit,Integer page){

        if (null == page || 0 == page){
            page = 1;
        }
        // 排序方式，这里是以“id”为标准进行降序
        Sort sort = Sort.by(Sort.Direction.DESC, "id");  // 这里的"id"是实体类的主键，记住一定要是实体类的属性，而不能是数据库的字段
        Pageable pageable = PageRequest.of(page - 1,limit,sort);

        UserArticle userArticle = UserArticle.builder().build();
        JSONObject jsonObject = new JSONObject();
        Page<UserArticle> pageArticle = userArticleService.allUserArticle(userArticle,pageable);
        jsonObject.put("list", pageArticle.getContent());
        return ResultBody.success(jsonObject);

    }

    @ApiOperation(value = "根据关键字查询所有文章", notes = "limit表示每次查几条 page表示第几页keyword表示关键字")
    @CheckToken
    @PostMapping(value = "/find_article",params = "limit")
    public ResultBody findArticle(Integer limit,Integer page,@Nullable String keyword){

        if (null == page || 0 == page){
            page = 1;
        }
        // 排序方式，这里是以“id”为标准进行降序
        Sort sort = Sort.by(Sort.Direction.DESC, "id");  // 这里的"id"是实体类的主键，记住一定要是实体类的属性，而不能是数据库的字段
        Pageable pageable = PageRequest.of(page - 1,limit,sort);


        UserArticle article = UserArticle.builder().build();
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




    @ApiOperation(value = "查询文章详情", notes = "传入文章id")
    @CheckToken
    @GetMapping(value = "/one_article",params = "id")
    public ResultBody oneArticle(@RequestParam Long id){
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

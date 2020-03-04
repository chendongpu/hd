package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.example.demo.controller.request.NewUserVideoRequest;
import com.example.demo.handler.BizException;
import com.example.demo.handler.ResultBody;
import com.example.demo.jwt.CheckToken;
import com.example.demo.model.User;
import com.example.demo.model.UserVideo;
import com.example.demo.service.UserService;
import com.example.demo.service.UserVideoService;
import com.fasterxml.jackson.annotation.JsonFilter;
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

@Api(value = "/user_video", tags = "用户视频接口")
@Slf4j
@RestController
@RequestMapping("/user_video")
public class UserVideoController {

    @Autowired
    private UserVideoService userVideoService;

    @Autowired
    private UserService userService;

    @Autowired
    HttpServletRequest request;


    @ApiOperation(value = "添加视频", notes = "传入用户视频json")
    //{"title":"野生动物","img":"/imgupload/d8744c4f60894af59ee233c4aaccba4e.jpg","video":"/movupload/4fd6bb372dc8427eb856d2bbd2704a96.wmv"}
    @CheckToken
    @PostMapping("/create_user_video")
    public ResultBody createUserVideo( @Valid @RequestBody NewUserVideoRequest newUserVideoReq) {
        Optional<UserVideo> userVideoOption=userVideoService.findByTitle(newUserVideoReq.getTitle());
        if (userVideoOption.isPresent()) {
            throw new BizException("-1","视频已存在");
        }

        log.info("Receive new UserVideo{}",newUserVideoReq);
        UserVideo newUserVideo=new UserVideo();
        String token = request.getHeader("token");
        // 获取 token 中的 user id
        log.info("token:{}",token);
        String userId= JWT.decode(token).getClaim("id").asString();
        Long userid =Long.parseLong(userId);
        newUserVideo.setUserid(userid);
        newUserVideo.setTitle(newUserVideoReq.getTitle());
        newUserVideo.setImg(newUserVideoReq.getImg());
        newUserVideo.setVideo(newUserVideoReq.getVideo());
        userVideoService.updateUserVideo(newUserVideo);

        log.info("UserVideo{}",newUserVideo);
        UserVideo saved= userVideoService.createUserVideo(newUserVideo);
        return ResultBody.success(saved);
    }


    @ApiOperation(value = "删除视频", notes = "传入用户视频id")
    @CheckToken
    @GetMapping(value = "/remove_user_video",params = "id")
    public ResultBody removeUserVideo(@RequestParam  Long id) {
        Optional<UserVideo> userVideoOption=userVideoService.findUserVideoById(id);
        if(!userVideoOption.isPresent()){
            throw new BizException("-1","视频不存在");
        }

        userVideoService.removeUserVideo(id);
        return ResultBody.success("视频删除成功");
    }


    @ApiOperation(value = "修改视频", notes = "传入用户视频json")
    //{"title":"野生动物01","img":"/imgupload/d8744c4f60894af59ee233c4aaccba4e.jpg","video":"/movupload/4fd6bb372dc8427eb856d2bbd2704a96.wmv","id":2}
    @CheckToken
    @PostMapping(value = "/update_user_video")
    public ResultBody updateUserVideo(@Valid @RequestBody NewUserVideoRequest newUserVideoReq){

        if(null==newUserVideoReq.getId() || 0==newUserVideoReq.getId() ){
            throw new BizException("-1","视频id不能为空");
        }

        Optional<UserVideo> userVideoOption=userVideoService.findUserVideoById(newUserVideoReq.getId());
        if(!userVideoOption.isPresent()){
            throw new BizException("-1","视频不存在");
        }

        UserVideo userVideoBase = userVideoOption.get();
        userVideoBase.setTitle(newUserVideoReq.getTitle());
        userVideoBase.setImg(newUserVideoReq.getImg());
        userVideoBase.setVideo(newUserVideoReq.getVideo());
        userVideoService.updateUserVideo(userVideoBase);
        return ResultBody.success("视频修改成功");
    }


    @ApiOperation(value = "查询用户视频", notes = "limit表示每次查几条 page表示第几页")
    @CheckToken
    @PostMapping(value = "/all_user_video",params = "limit")
    public ResultBody allUserVideo(Integer limit,Integer page){

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
        UserVideo userVideo = UserVideo.builder().userid(userid).build();
        JSONObject jsonObject = new JSONObject();
        Page<UserVideo> pageVideo = userVideoService.allUserVideo(userVideo,pageable);
        jsonObject.put("list", pageVideo.getContent());
        return ResultBody.success(jsonObject);

    }

    @ApiOperation(value = "查询用户视频详情", notes = "传入视频id")
    @CheckToken
    @GetMapping(value = "/one_user_video",params = "id")
    public ResultBody oneUserVideo(@RequestParam Long id){
        if(null==id|| 0==id){
            throw new BizException("-1","视频id不能为空");
        }

        Optional<UserVideo> userVideoOption=userVideoService.findUserVideoById(id);
        if(!userVideoOption.isPresent()){
            throw new BizException("-1","视频不存在");
        }


        return ResultBody.success(userVideoOption.get());
    }




}

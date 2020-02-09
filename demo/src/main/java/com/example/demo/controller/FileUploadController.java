package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.controller.request.NewUserRequest;
import com.example.demo.handler.BizException;
import com.example.demo.handler.ResultBody;
import com.example.demo.jwt.CheckToken;
import com.example.demo.jwt.JwtUtil;
import com.example.demo.jwt.LoginToken;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import com.example.demo.util.FileUtil;
import com.example.demo.util.MD5Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/file")
public class FileUploadController {

    @Autowired
    HttpServletRequest request;

    //上传
    @PostMapping("/upload")
    public ResultBody upload(@RequestParam("file") MultipartFile file) {

        if (file == null) {
            throw new BizException("-1","没有上传图片！");
        }
        String contentType = file.getContentType();
        String fileName = file.getOriginalFilename();
        log.info("fileName:{}",fileName);
        log.info("getContentType:{}",contentType);

        String suffix=new String();
        int suffixIndex = fileName.lastIndexOf(".");
        if (suffixIndex == -1) {    // 无后缀
            throw new BizException("-1","图片上传不合法！");
        } else {                    // 存在后缀
            suffix= fileName.substring(suffixIndex, fileName.length());
        }
        String targetFileName = (UUID.randomUUID().toString().replace("-", ""))+suffix;
        log.info("targetFileName:{}",targetFileName);
        String filePath = request.getSession().getServletContext().getRealPath("imgupload/");
        try {
            FileUtil.uploadFile(file.getBytes(), filePath, targetFileName);
        } catch (Exception e) {
            // TODO: handle exception
            throw new BizException("-1","图片上传出错！");
        }

        return ResultBody.success("/imgupload/"+targetFileName);


    }






}

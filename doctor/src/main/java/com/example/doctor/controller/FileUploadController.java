package com.example.doctor.controller;

import com.example.doctor.handler.BizException;
import com.example.doctor.handler.ResultBody;
import com.example.doctor.util.FileUtil;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

@Api(value = "/file", tags = "上传文件")
@Slf4j
@RestController
@RequestMapping("/file")
public class FileUploadController {

    @Autowired
    HttpServletRequest request;


    @ApiOperation(value = "医生端上传图片", notes = "图片必须是jpg、jpeg、png、bmp、gif格式")
    @PostMapping("/uploadimg")
    public ResultBody uploadImg(@Nullable @RequestParam("img") MultipartFile file) {
        // 首先校验图片格式
        List<String> imageType = Lists.newArrayList(".jpg",".jpeg", ".png", ".bmp", ".gif");


        if (file == null) {
            throw new BizException("-1","没有上传图片！");
        }
        String contentType = file.getContentType();
        String fileName = file.getOriginalFilename();
        log.info("fileName:{}",fileName);
        log.info("getContentType:{}",contentType);

        String suffix="";
        int suffixIndex = fileName.lastIndexOf(".");
        if (suffixIndex == -1) {    // 无后缀
            throw new BizException("-1","图片上传不合法！");
        }

        // 存在后缀
        suffix= fileName.substring(suffixIndex, fileName.length());

        if (!imageType.contains(suffix)) {
            throw new BizException("-1","图片类型不合法！");
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





    @ApiOperation(value = "医生端上传视频", notes = "视频必须是mp4、flv、avi、rm、rmvb、wmv格式")
    @PostMapping("/uploadmov")
    public ResultBody uploadMov(@Nullable @RequestParam("mov") MultipartFile mov) {

        if (mov == null) {
            throw new BizException("-1","没有上传视频！");
        }
        String contentType = mov.getContentType();
        String fileName = mov.getOriginalFilename();
        log.info("fileName:{}",fileName);
        log.info("getContentType:{}",contentType);

        String suffix=new String();
        int suffixIndex = fileName.lastIndexOf(".");
        if (suffixIndex == -1) {    // 无后缀
            throw new BizException("-1","视频上传不合法！");
        }

        // 存在后缀
        suffix= fileName.substring(suffixIndex, fileName.length());

        String reg = "(mp4|flv|avi|rm|rmvb|wmv)";
        Pattern p = Pattern.compile(reg);
        if(!p.matcher(suffix).find()){
            throw new BizException("-1","视频类型不合法！");
        }

        String targetFileName = (UUID.randomUUID().toString().replace("-", ""))+suffix;
        log.info("targetFileName:{}",targetFileName);
        String filePath = request.getSession().getServletContext().getRealPath("movupload/");
        try {
            FileUtil.uploadFile(mov.getBytes(), filePath, targetFileName);
        } catch (Exception e) {
            // TODO: handle exception
            throw new BizException("-1","视频上传出错！");
        }

        return ResultBody.success("/movupload/"+targetFileName);


    }






}

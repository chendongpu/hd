package com.example.demo.controller;

import com.example.demo.handler.BizException;
import com.example.demo.handler.ResultBody;
import com.example.demo.im.NIMPost;
import com.example.demo.im.UUIDUtil;
import com.example.demo.jwt.CheckToken;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 */
@Slf4j
@RestController
@RequestMapping("/im")
public class IMController {

    private static Logger logger = LoggerFactory.getLogger(IMController.class);

    private static String APPKEY = "";  //AppKey
    private static String SECRET = "";  //AppSecret

    //创建用户
    @CheckToken
    @GetMapping("/create_user")
    public ResultBody createUser(@NotEmpty String accid, @NotEmpty String name){
        String url = "https://api.netease.im/nimserver/user/create.action";
        List<NameValuePair> params = new ArrayList<NameValuePair>();

        params.add(new BasicNameValuePair("accid", accid));
        params.add(new BasicNameValuePair("name", name));
        params.add(new BasicNameValuePair("token", UUIDUtil.getUUID()));

        try{
            //UTF-8编码,解决中文问题
            HttpEntity entity = new UrlEncodedFormEntity(params, "UTF-8");

            String res = NIMPost.postNIMServer(url, entity, APPKEY, SECRET);
            logger.info("createUser httpRes: {}", res);
            return ResultBody.success(res);
            //TODO: 对结果的业务处理，如解析返回结果，并保存成功注册的用户
        }catch (IOException e){
            throw new BizException("-1","请求第三方服务器出现异常！");
        }

    }


}

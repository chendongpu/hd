package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.handler.BizException;
import com.example.demo.handler.ResultBody;
import com.example.demo.jwt.CheckToken;
import com.example.demo.util.CheckSumBuilder;
import com.example.demo.util.UUIDUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.concurrent.TimeUnit;


@Slf4j
@RestController
@RequestMapping("/im")
public class IMController {


    @Bean
    public HttpComponentsClientHttpRequestFactory requestFactory(){
        PoolingHttpClientConnectionManager connectionManager=new PoolingHttpClientConnectionManager(30, TimeUnit.MINUTES);
        connectionManager.setMaxTotal(200);
        connectionManager.setDefaultMaxPerRoute(20);
        CloseableHttpClient httpClient= HttpClients.custom()
                .setConnectionManager(connectionManager)
                .evictIdleConnections(30, TimeUnit.SECONDS)
                .disableAutomaticRetries()
                // 有 Keep-Alive 认里面的值，没有的话永久有效
                .setKeepAliveStrategy(DefaultConnectionKeepAliveStrategy.INSTANCE)
                // 换成自定义的
                //.setKeepAliveStrategy(new CustomConnectionKeepAliveStrategy())
                .build();
        HttpComponentsClientHttpRequestFactory requestFactory=new HttpComponentsClientHttpRequestFactory(httpClient);
        return requestFactory;
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder){
        return restTemplateBuilder.build();
    }

    @Autowired
    private RestTemplate restTemplate;

    private static String APPKEY = "405d4ef6d2b95039c1df033622fe527b";  //AppKey
    private static String SECRET = "365fa143c0d1";  //AppSecret

    private HttpHeaders makeHeaders(String appKey, String appSecret){
        String nonce = UUIDUtil.getUUID();
        String curTime = String.valueOf((new Date()).getTime() / 1000L);
        String checkSum = CheckSumBuilder.getCheckSum(appSecret, nonce ,curTime);//参考 计算CheckSum的java代码
        HttpHeaders headers = new HttpHeaders();

        headers.add("AppKey", appKey);
        headers.add("Nonce", nonce);
        headers.add("CurTime", curTime);
        headers.add("CheckSum", checkSum);
        headers.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

        return headers;
    }

    //创建用户
    //accid:apple
    //name:张三
    @CheckToken
    @PostMapping("/create_user")
    public ResultBody createUser(@NotEmpty String accid, @NotEmpty String name){

        String url = "https://api.netease.im/nimserver/user/create.action";

        HttpHeaders headers=makeHeaders(APPKEY,SECRET);

        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();

        map.add("accid", accid);
        map.add("name", name);
        map.add("token", UUIDUtil.getUUID());

        HttpEntity<MultiValueMap<String, String>> request = new org.springframework.http.HttpEntity<>(map, headers);
        ResponseEntity<JSONObject> exchange = restTemplate.exchange(url, HttpMethod.POST, request, JSONObject.class);

        try{
            return ResultBody.success(exchange.getBody());
            //TODO: 对结果的业务处理，如解析返回结果，并保存成功注册的用户
        }catch (Exception e){
            throw new BizException("-1","请求第三方服务器出现异常！");
        }

    }


    //获取用户名片
    //apple,helloworld
    @CheckToken
    @PostMapping("/get_uinfos")
    public ResultBody getUinfos(String[] accids){

        String url = "https://api.netease.im/nimserver/user/getUinfos.action";

        HttpHeaders headers=makeHeaders(APPKEY,SECRET);

        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();

        map.add("accids", JSON.toJSONString(accids));

        org.springframework.http.HttpEntity<MultiValueMap<String, String>> request = new org.springframework.http.HttpEntity<>(map, headers);

        ResponseEntity<JSONObject> exchange = restTemplate.exchange(url,HttpMethod.POST, request, JSONObject.class);

        try{
            return ResultBody.success(exchange.getBody());
        }catch (Exception e){
            throw new BizException("-1","请求第三方服务器出现异常！");
        }

    }

    //更新用户名片
    //accid:apple
    //name:老王
    @CheckToken
    @PostMapping("/update_uinfo")
    public ResultBody updateUinfo(@NotEmpty String accid, @NotEmpty String name){
        String url = "https://api.netease.im/nimserver/user/updateUinfo.action";

        HttpHeaders headers=makeHeaders(APPKEY,SECRET);

        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();

        map.add("accid", accid);
        map.add("name", name);

        HttpEntity<MultiValueMap<String, String>> request = new org.springframework.http.HttpEntity<>(map, headers);
        ResponseEntity<JSONObject> exchange = restTemplate.exchange(url, HttpMethod.POST, request, JSONObject.class);

        try{
            return ResultBody.success(exchange.getBody());
        }catch (Exception e){
            throw new BizException("-1","请求第三方服务器出现异常！");
        }
    }


    //加好友
    //accid:apple
    //faccid:helloworld
    //type:1
    @CheckToken
    @PostMapping("/add_friend")
    public ResultBody addFriend(@NotEmpty String accid, @NotEmpty String faccid,Integer type){
        String url = "https://api.netease.im/nimserver/friend/add.action";

        HttpHeaders headers=makeHeaders(APPKEY,SECRET);

        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();

        map.add("accid", accid);
        map.add("faccid", faccid);
        map.add("type", type.toString());

        HttpEntity<MultiValueMap<String, String>> request = new org.springframework.http.HttpEntity<>(map, headers);
        ResponseEntity<JSONObject> exchange = restTemplate.exchange(url, HttpMethod.POST, request, JSONObject.class);

        try{
            return ResultBody.success(exchange.getBody());
        }catch (Exception e){
            throw new BizException("-1","请求第三方服务器出现异常！");
        }
    }

    //获取好友关系
    //accid:apple
    //createtime:1443599631111
    @CheckToken
    @PostMapping("/get_friend")
    public ResultBody getFriend(@NotEmpty String accid,@NotEmpty Long createtime){
        String url = "https://api.netease.im/nimserver/friend/get.action";

        HttpHeaders headers=makeHeaders(APPKEY,SECRET);

        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();

        map.add("accid", accid);
        map.add("createtime", createtime.toString());

        HttpEntity<MultiValueMap<String, String>> request = new org.springframework.http.HttpEntity<>(map, headers);
        ResponseEntity<JSONObject> exchange = restTemplate.exchange(url, HttpMethod.POST, request, JSONObject.class);

        try{
            return ResultBody.success(exchange.getBody());
        }catch (Exception e){
            throw new BizException("-1","请求第三方服务器出现异常！");
        }
    }


    //删除好友
    //accid:apple
    //faccid:helloworld
    @CheckToken
    @PostMapping("/delete_friend")
    public ResultBody deleteFriend(@NotEmpty String accid, @NotEmpty String faccid){
        String url = "https://api.netease.im/nimserver/friend/delete.action";
        HttpHeaders headers=makeHeaders(APPKEY,SECRET);
        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        map.add("accid", accid);
        map.add("faccid", faccid);
        HttpEntity<MultiValueMap<String, String>> request = new org.springframework.http.HttpEntity<>(map, headers);
        ResponseEntity<JSONObject> exchange = restTemplate.exchange(url, HttpMethod.POST, request, JSONObject.class);

        try{
            return ResultBody.success(exchange.getBody());
        }catch (Exception e){
            throw new BizException("-1","请求第三方服务器出现异常！");
        }
    }


    //更新好友相关信息(备注名)
    //accid:apple
    //faccid:helloworld
    //alias:lisi
    @CheckToken
    @PostMapping("/update_friend")
    public ResultBody updateFriend(@NotEmpty String accid, @NotEmpty String faccid, @NotEmpty String alias){
        String url = "https://api.netease.im/nimserver/friend/update.action";
        HttpHeaders headers=makeHeaders(APPKEY,SECRET);
        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        map.add("accid", accid);
        map.add("faccid", faccid);
        map.add("alias", alias);
        HttpEntity<MultiValueMap<String, String>> request = new org.springframework.http.HttpEntity<>(map, headers);
        ResponseEntity<JSONObject> exchange = restTemplate.exchange(url, HttpMethod.POST, request, JSONObject.class);

        try{
            return ResultBody.success(exchange.getBody());
        }catch (Exception e){
            throw new BizException("-1","请求第三方服务器出现异常！");
        }
    }










}

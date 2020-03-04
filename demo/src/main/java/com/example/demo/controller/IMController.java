package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.controller.request.MsgRequest;
import com.example.demo.handler.BizException;
import com.example.demo.handler.ResultBody;
import com.example.demo.jwt.CheckToken;
import com.example.demo.model.User;
import com.example.demo.util.CheckSumBuilder;
import com.example.demo.util.UUIDUtil;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
import org.springframework.lang.Nullable;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Api(value = "/im", tags = "对接云信接口")
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


    @ApiOperation(value = "创建用户", notes = "accid 用户名 name 昵称")
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



    @ApiOperation(value = "获取用户名片", notes = "accids传入多个用户名按照逗号分隔")
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


    @ApiOperation(value = "更新用户名片", notes = "accid 用户名 name 昵称")
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



    @ApiOperation(value = "加好友", notes = "accid 用户名 faccid 好友用户名 type 类型默认为1")
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


    @ApiOperation(value = "获取好友关系", notes = "accid 用户名 createtime 时间戳")
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



    @ApiOperation(value = "删除好友", notes = "accid 用户名 faccid 好友的用户名")
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



    @ApiOperation(value = "更新好友相关信息(备注名)", notes = "accid 用户名 faccid 好友的用户名 alias 备注名")
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



    @ApiOperation(value = "发送普通消息", notes = "from 用户名 to 好友的用户名 ope 默认为0 type 默认为0 body 消息的json字符串")
    //{"from":"apple","to":"helloworld","ope":0,"type":0,"body":{"msg":"hello"}}
    @CheckToken
    @PostMapping("/send_msg")
    public ResultBody sendMsg(@RequestBody @Valid MsgRequest msgRequest){
        String url = "https://api.netease.im/nimserver/msg/sendMsg.action";
        HttpHeaders headers=makeHeaders(APPKEY,SECRET);
        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        map.add("from", msgRequest.getFrom());
        map.add("to", msgRequest.getTo());
        map.add("ope", msgRequest.getOpe().toString());
        map.add("type", msgRequest.getType().toString());
        map.add("body", JSON.toJSONString(msgRequest.getBody()));
        HttpEntity<MultiValueMap<String, String>> request = new org.springframework.http.HttpEntity<>(map, headers);
        ResponseEntity<JSONObject> exchange = restTemplate.exchange(url, HttpMethod.POST, request, JSONObject.class);

        try{
            return ResultBody.success(exchange.getBody());
        }catch (Exception e){
            throw new BizException("-1","请求第三方服务器出现异常！");
        }
    }


    @ApiOperation(value = "单聊云端历史消息查询", notes = "from 用户名 to 好友的用户名 begintime 开始时间 endtime 结束时间 limit 查多少条")
    //from:apple
    //to:helloworld
    //begintime:1582685390901
    //endtime:1582686378000
    //limit:50
    @CheckToken
    @PostMapping("/query_session_msg")
    public ResultBody querySessionMsg(@NotEmpty String from, @NotEmpty String to ,@NotNull Long begintime, @NotNull Long endtime, @NotNull Long limit){
        String url = "https://api.netease.im/nimserver/history/querySessionMsg.action";
        HttpHeaders headers=makeHeaders(APPKEY,SECRET);
        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        map.add("from", from);
        map.add("to", to);
        map.add("begintime", begintime.toString());
        map.add("endtime", endtime.toString());
        map.add("limit", limit.toString());
        HttpEntity<MultiValueMap<String, String>> request = new org.springframework.http.HttpEntity<>(map, headers);
        ResponseEntity<JSONObject> exchange = restTemplate.exchange(url, HttpMethod.POST, request, JSONObject.class);

        try{
            return ResultBody.success(exchange.getBody());
        }catch (Exception e){
            throw new BizException("-1","请求第三方服务器出现异常！");
        }
    }



    @ApiOperation(value = "文件上传", notes = "图片必须是jpg、jpeg、png、bmp、gif格式")
    //img:选择图片
    //http://nim-nosdn.netease.im/MTY3Nzk0ODA=/bmltd18wXzE1ODI2ODk2MDcxNDNfZTg0OWIwNWEtMTA1Ni00NTU0LWE4YjAtMTljNGFiMDc1YTFh
    @CheckToken
    @PostMapping("/upload")
    public ResultBody upload(@Nullable @RequestParam("img") MultipartFile file) {
        String url = "https://api.netease.im/nimserver/msg/upload.action";
        // 首先校验图片格式
        List<String> imageType = Lists.newArrayList(".jpg", ".jpeg", ".png", ".bmp", ".gif");

        if (file == null) {
            throw new BizException("-1", "没有上传图片！");
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

        HttpHeaders headers=makeHeaders(APPKEY,SECRET);
        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        InputStream in = null;
        byte[] data = null;
        //读取图片字节数组
        try
        {
            in = file.getInputStream();
            data = new byte[in.available()];
            in.read(data);
            in.close();
        }catch (IOException e)
        {
            throw new BizException("-1","文件转base64出现异常！");
        }
        //对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        map.add("content", encoder.encode(data));
        HttpEntity<MultiValueMap<String, String>> request = new org.springframework.http.HttpEntity<>(map, headers);
        ResponseEntity<JSONObject> exchange = restTemplate.exchange(url, HttpMethod.POST, request, JSONObject.class);

        try{
            return ResultBody.success(exchange.getBody());
        }catch (Exception e){
            throw new BizException("-1","请求第三方服务器出现异常！");
        }
    }




    @ApiOperation(value = "消息撤回", notes = "from 用户名 to 好友的用户名 deleteMsgid 消息id timetag 时间戳 type 默认为7 msg 备注")
    //from:apple
    //to:helloworld
    //deleteMsgid:359980429841
    //timetag:1582695507633
    //type:7
    //msg:"这是一条撤回消息"
    @CheckToken
    @PostMapping("/recall_msg")
    public ResultBody recallMsg(@NotEmpty String from,@NotEmpty String to,@NotNull Long deleteMsgid, @NotNull Long timetag, @NotNull Integer type, @Nullable String msg) {
        String url = "https://api.netease.im/nimserver/msg/recall.action";
        HttpHeaders headers=makeHeaders(APPKEY,SECRET);
        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        map.add("from", from);
        map.add("to", to);
        map.add("deleteMsgid", deleteMsgid.toString());
        map.add("timetag", timetag.toString());
        map.add("type", type.toString());
        map.add("msg", msg);
        HttpEntity<MultiValueMap<String, String>> request = new org.springframework.http.HttpEntity<>(map, headers);
        ResponseEntity<JSONObject> exchange = restTemplate.exchange(url, HttpMethod.POST, request, JSONObject.class);

        try{
            return ResultBody.success(exchange.getBody());
        }catch (Exception e){
            throw new BizException("-1","请求第三方服务器出现异常！");
        }
    }






}

package com.example.doctor.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.example.doctor.jwt.CheckToken;
import com.example.doctor.jwt.JwtUtil;
import com.example.doctor.jwt.LoginToken;
import com.example.doctor.model.User;
import com.example.doctor.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@Slf4j
public class AuthenticationInterceptor implements HandlerInterceptor {
    @Autowired
    UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {

        // 从 http 请求头中取出 token
        String token = httpServletRequest.getHeader("token");


        // 如果不是映射到方法直接通过
        if (!(object instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) object;
        Method method = handlerMethod.getMethod();
        //检查是否有LoginToken注释，有则跳过认证
        if (method.isAnnotationPresent(LoginToken.class)) {
            LoginToken loginToken = method.getAnnotation(LoginToken.class);
            if (loginToken.required()) {
                return true;
            }
        }

        //检查有没有需要用户权限的注解
        if (method.isAnnotationPresent(CheckToken.class)) {
            CheckToken checkToken = method.getAnnotation(CheckToken.class);
            if (checkToken.required()) {
                // 执行认证
                if (token == null) {
                    throw new RuntimeException("无token，请重新登录");
                }
                // 获取 token 中的 user id
                log.info("token:{}",token);

                String userId;
                String expireStr;
                try {
                    userId = JWT.decode(token).getClaim("id").asString();
                    expireStr = JWT.decode(token).getClaim("expire").asString();
                } catch (JWTDecodeException j) {
                    throw new RuntimeException("访问异常！");
                }

                log.info("userId:{}",userId);
                log.info("expireStr:{}",expireStr);

                if(System.currentTimeMillis()>Long.parseLong(expireStr)){
                    throw new RuntimeException("token已过期！");
                }
                User user = (userService.findUserById(Long.parseLong(userId))).get();
                if (user == null) {
                    throw new RuntimeException("用户不存在，请重新登录");
                }
                Boolean verify = JwtUtil.isVerify(token, user);
                if (!verify) {
                    throw new RuntimeException("非法访问！");
                }
                return true;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}

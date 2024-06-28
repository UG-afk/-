package com.itheima.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.github.pagehelper.util.StringUtil;
import com.itheima.pojo.Result;
import com.itheima.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import org.springframework.web.servlet.HandlerInterceptor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j

public class LoginInterceptor implements HandlerInterceptor {
//目标资源方法运行前完成
    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {
//       1获取url
        String url=req.getRequestURI().toString();
        log.info("请求的url:{}",url);
//      2获取请求头的令牌

        String jwt=req.getHeader("Authorization");
        log.info("请求头:",jwt);
//      3判断令牌是否存在，如果不存在，返回错误结果(未登录)
        if(!StringUtils.hasLength(jwt)) {
            log.info("请求头token为空，返回未登录的信息");
            Result error = Result.error("NOT_LOGIN");
            String notLogin = JSONObject.toJSONString(error);
            resp.getWriter().write(notLogin);
            return false;
        }

//       4解析token,如果解析失败,返回错误结果
        try{
            JwtUtils.parseJWT(jwt);
        }catch (Exception e){
            log.info("解析令牌失败，返回未登录错误信息");
            Result error=Result.error("NOT_LOGIN");
            String notLogin = JSONObject.toJSONString(error);
            resp.getWriter().write(notLogin);
            return false;
        }

        log.info("令牌合法，放行");
        return true;
    }
    //目标资源方法运行前完成
//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
//    }
    //视图前完成
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
//    }
}

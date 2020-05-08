package com.example.springbootmybatis.entity.UtilEntity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class CheckSessionIdInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    RedisUtil redisUtil;
    public CheckSessionIdInterceptor() {
        super();
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String method = request.getMethod();
        String sessionId;
        if(method.equals("GET"))
            sessionId=request.getParameter("sessionId");
        else
        {
            RequestWrapper requestWrapper = new RequestWrapper(request);
            String body = requestWrapper.getBody();
            System.out.println("string:  "+body);
            JSONObject jsonObject = JSONObject.parseObject(body);
            System.out.println("jsonobject111111:  "+jsonObject);
            sessionId=jsonObject.getString("sessionId");
            System.out.println(("111111111111"+sessionId));
        }
        if(sessionId==null && request.getServletPath().equals("/user/login"))
            return true;
        if(redisUtil.hasKey(sessionId) && !request.getServletPath().equals("/user/login"))
        {
            System.out.println("x");
            return true;
        }
        else {
            System.out.println("会话过期");
            Status status = new Status(10,"会话失效请重新登录");
            String statusJson = JSONObject.toJSONString(status);
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html; charset=utf-8");
            PrintWriter pw = response.getWriter();
            pw.print(statusJson);
            return false;
        }
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }

    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        super.afterConcurrentHandlingStarted(request, response, handler);
    }
}

package com.heaiai.reggie.filter;

import com.alibaba.fastjson.JSONObject;
import com.heaiai.reggie.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description 登录拦截器
 * @author: Heaiai
 * @create: 2023-02-19 22:04:17
 */
@WebFilter(filterName = "loginCheckFilter",urlPatterns = "/*")
@Slf4j
public class LoginCheckFilter implements Filter {

    //路径匹配器
    public static final AntPathMatcher ANT_PATH_MATCHER = new AntPathMatcher();
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //1、获取本次请求的URL
        String url = request.getRequestURI();
        //2、判断本次请求是否需要处理
        String[] urls = new String[]{
                "/employee/login",
                "/employee/logout",
                "/backend/**",
                "/front/**"
        };
        //3、如果不需要处理，则直接放行
        if(checkLogin(urls,url)){
            filterChain.doFilter(request,response);
            return;
        }
        //4、判断登录状态，如果已登录，则直接放行
        if(null != request.getSession().getAttribute("employee")){
            filterChain.doFilter(request,response);
            return;
        }
        //5、如果未登录返回未登录结果
        response.getWriter().write(JSONObject.toJSONString(R.error("NOTLOGIN")));
    }

    /***
     * @Description:判断是否需要处理
     * @Author:Heaiai
     * @Create:2023/2/19 22:24
     */
    public boolean checkLogin(String[] urls,String requestUrl){
        for(String url:urls){
            if(ANT_PATH_MATCHER.match(url,requestUrl)){
                return true;
            }
        }
        return false;
    }
}

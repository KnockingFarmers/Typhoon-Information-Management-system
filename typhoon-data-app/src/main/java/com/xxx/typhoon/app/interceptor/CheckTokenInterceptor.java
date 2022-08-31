package com.xxx.typhoon.app.interceptor;

import com.xxx.common.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author
 */
public class CheckTokenInterceptor implements HandlerInterceptor {


    JwtUtil jwtUtil=new JwtUtil();

    /**
     * 目标方法执行之前验证token
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getParameter("token");
        if ("/".equals(request.getRequestURI())||
                "/index.html".equals(request.getRequestURI())||
                "/favicon.ico".equals(request.getRequestURI())) {
            return true;
        }
        if (token==null){
            return false;
        }
        Integer checkToken = jwtUtil.checkToken(token);

        return checkToken == 1 ? true : false;
    }
}

package com.xxx.typhoon.app.config;

import com.xxx.typhoon.app.interceptor.CheckTokenInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 甘龙
 * 拦截器配置
 */
@Configuration
public class InterceptorConfiguration implements WebMvcConfigurer {
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        String[] excludePath = {"/user/login",
//                "/userAvatar/**",
//                "/static/index.html",
//                "/css/**",
//                "/js/**",
//                "/img/**"};
//        registry.addInterceptor(new CheckTokenInterceptor())
//                .addPathPatterns("/**")
//                .excludePathPatterns(excludePath);
//    }
}

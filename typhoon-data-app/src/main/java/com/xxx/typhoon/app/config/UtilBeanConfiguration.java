package com.xxx.typhoon.app.config;

import com.xxx.common.util.JwtUtil;
import com.xxx.common.util.RedisUtil;
import com.xxx.typhoon.app.entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author
 * @Date 2022/8/30 22:49
 * @PackageName:com.xxx.typhoon.app.config
 * @ClassName: UtilConfiguration
 * @Description: 配置工具类
 * @Version 1.0
 */
@Configuration
public class UtilBeanConfiguration {

    @Bean
    public JwtUtil<User> jwtUtil() {
        return new JwtUtil();
    }

    @Bean
    public RedisUtil redisUtil() {
        return new RedisUtil();
    }

}

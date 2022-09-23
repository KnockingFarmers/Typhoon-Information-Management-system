package com.xxx.typhoon.app.config;

import com.xxx.common.util.JwtUtil;
import com.xxx.common.util.RedisUtil;
import com.xxx.typhoon.app.entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @Author
 * @Date 2022/8/2 17:07
 * @PackageName:org.gl.springcloud.conf
 * @ClassName: ApplicationContextConfig
 * @Description: 全局配置类
 * @Version 1.0
 */
@Configuration
public class ApplicationContextConfiguration {

    @Bean
    public JwtUtil<User> jwtUtil() {
        return new JwtUtil();
    }

    @Bean
    public RedisUtil redisUtil() {
        return new RedisUtil();
    }
}

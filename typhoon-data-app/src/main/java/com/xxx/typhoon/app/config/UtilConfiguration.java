package com.xxx.typhoon.app.config;

import com.xxx.common.util.FileUtil;
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
 * @Description: 把工具类加载到容器中
 * @Version 1.0
 */
@Configuration
public class UtilConfiguration {

    @Bean
    public JwtUtil<User> jwtUtil() {
        return new JwtUtil();
    }

    @Bean
    public RedisUtil redisUtil() {
        return new RedisUtil();
    }

    @Bean
    public FileUtil fileUtil(){
        return new FileUtil();
    }
}

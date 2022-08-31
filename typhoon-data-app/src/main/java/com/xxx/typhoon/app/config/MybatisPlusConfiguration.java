package com.xxx.typhoon.app.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author
 * @Date 2022/8/30 22:22
 * @PackageName:com.xxx.typhoon.app.config
 * @ClassName: MybatisPlusConfiguration
 * @Description: MP配置类
 * @Version 1.0
 */
@Configuration
@MapperScan("com.xxx.typhoon.app.mapper")
public class MybatisPlusConfiguration {

    /**
     * 乐观锁插件
     * @return
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
        return interceptor;
    }

}

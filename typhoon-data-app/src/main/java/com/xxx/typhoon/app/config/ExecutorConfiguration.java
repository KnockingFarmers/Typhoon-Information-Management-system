package com.xxx.typhoon.app.config;

import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author
 * @Date 2022/9/9 22:22
 * @PackageName:com.xxx.typhoon.app.config
 * @ClassName: MybatisPlusConfiguration
 * @Description: 线程池配置
 * @Version 1.0
 */
@Configuration
@Data
public class ExecutorConfiguration {
 
    /**
     * 核心线程
     */
    private int corePoolSize=5;
 
    /**
     * 最大线程
     */
    private int maxPoolSize=20;
 
    /**
     * 队列容量
     */
    private int queueCapacity=10;
 
    /**
     * 保持时间
     */
    private int keepAliveSeconds=200;
 
    /**
     * 名称前缀
     */
    private String preFix="task-";
 
    @Bean
    public Executor myExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setKeepAliveSeconds(keepAliveSeconds);
        executor.setThreadNamePrefix(preFix);
        executor.setRejectedExecutionHandler( new ThreadPoolExecutor.AbortPolicy());
        executor.initialize();
        return executor;
    }
}
package com.xxx.typhoon.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @Author
 * @Date 2022/8/29 11:21
 * @PackageName:com.xxx.typhoon.app
 * @ClassName: VisualizationOfTyphoonDataApplication
 * @Description: 主启动
 * @Version 1.0
 */
@SpringBootApplication
@EnableAsync
public class VisualizationOfTyphoonDataApplication {
    public static void main(String[] args) {
        SpringApplication.run(VisualizationOfTyphoonDataApplication.class,args);
    }
}

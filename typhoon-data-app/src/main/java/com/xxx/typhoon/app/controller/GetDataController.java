package com.xxx.typhoon.app.controller;

import com.xxx.common.result.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.File;

/**
 * @Author 甘龙
 * @Date 2022/9/17 22:39
 * @PackageName:com.xxx.typhoon.app.controller
 * @ClassName: GetDataController
 * @Description: 调用python端接口
 * @Version 1.0
 */
@RestController
@RequestMapping("/getData")
public class GetDataController {

    public static final String TYPHOON_NEWS_URL="爬取台风新闻";
    public static final String TYPHOON_DATA_URL="爬取台风数据";

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/typhoonNewsData")
    public File getNewsData(String typhoonName){
        return restTemplate.postForObject(TYPHOON_NEWS_URL,typhoonName, File.class);
    }

    @PostMapping("/typhoonData")
    public File getTyphoonData(String typhoonName){

        return restTemplate.postForObject(TYPHOON_DATA_URL,typhoonName, File.class);
    }

}

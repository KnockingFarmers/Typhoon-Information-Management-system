package com.xxx.typhoon.app.service;

import com.xxx.common.result.CommonResult;
//import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author 甘龙
 * @Date 2022/9/20 22:48
 * @PackageName:com.xxx.typhoon.app.service
 * @ClassName: GetDataService
 * @Description: TODO
 * @Version 1.0
 */
@Service
//@FeignClient(value = "爬取台风新闻")
public interface GetDataService {

    /**
     * 调用python 获取台风数据
     *
     * @return
     */
    @GetMapping("get")
    CommonResult getTyphoonData();
}

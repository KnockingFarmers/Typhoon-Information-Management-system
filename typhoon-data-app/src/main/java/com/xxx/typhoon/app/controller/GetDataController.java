package com.xxx.typhoon.app.controller;

import com.xxx.common.result.CommonResult;
import com.xxx.typhoon.app.service.GetDataService;
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

//    @Autowired
//    GetDataService getDataService;
//
//    @PostMapping("/typhoonNewsData")
//    public CommonResult getNewsData(String typhoonName){
//        return getDataService.getTyphoonData();
//    }


}

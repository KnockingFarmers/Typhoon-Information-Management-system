package com.xxx.typhoon.app.controller;

import com.xxx.common.result.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @Author 甘龙
 * @Date 2022/9/17 22:39
 * @PackageName:com.xxx.typhoon.app.controller
 * @ClassName: GetDataController
 * @Description: TODO
 * @Version 1.0
 */
@RestController
@RequestMapping("/getData")
public class GetDataController {

    public static final String PYTHON_URL="http://CLOUD-PAYMENT-SERVICE";

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/typhoonNewsData")
    public CommonResult getNewsData(){
        return null;
    }

    @PostMapping("/typhoonData")
    public CommonResult getTyphoonData(){

        return null;
    }

}

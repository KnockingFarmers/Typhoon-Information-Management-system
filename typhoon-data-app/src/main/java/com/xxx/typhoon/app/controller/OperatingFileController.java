package com.xxx.typhoon.app.controller;

import com.xxx.common.result.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author
 * @Date 2022/9/4 14:32
 * @PackageName:com.xxx.typhoon.app.controller
 * @ClassName: OperatingFileController
 * @Description: 文件操作控制层
 * @Version 1.0
 */
@RestController
@RequestMapping("/file")
public class OperatingFileController {

    private static final String TYPHOON_DATA_ENTITY="data";
    private static final String TYPHOON_NEWS_ENTITY="news";

    @PostMapping("/readExcelFileToDB")
    public CommonResult readExcelFileToDB(MultipartFile excelFile,String entity){

        CommonResult result=null;

        if (entity.equals(TYPHOON_DATA_ENTITY)) {

        }else if (entity.equals(TYPHOON_NEWS_ENTITY)){

        }
        return result;
    }

    @PostMapping("/readCSVFileToDB")
    public CommonResult readCSVFileToDB(MultipartFile csvFile,String entity){
        CommonResult result=null;

        if (entity.equals(TYPHOON_DATA_ENTITY)) {

        }else if (entity.equals(TYPHOON_NEWS_ENTITY)){

        }
        return result;
    }

}

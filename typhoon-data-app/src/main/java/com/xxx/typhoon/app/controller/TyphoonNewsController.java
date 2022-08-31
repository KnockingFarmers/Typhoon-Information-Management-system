package com.xxx.typhoon.app.controller;

import com.alibaba.excel.EasyExcel;
import com.xxx.common.result.CommonResult;
import com.xxx.typhoon.app.lisenner.ExcelListener;
import com.xxx.typhoon.app.service.TyphoonNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 
 * @since 2022-08-31
 */
@RestController
@RequestMapping("/typhoonNews")
public class TyphoonNewsController {

    @Autowired
    TyphoonNewsService typhoonNewsService;

    @PostMapping("/readExcel")
    @Async
    public CommonResult readExcel(MultipartFile excelFile){
        typhoonNewsService.redExcel(excelFile);
        return null;
    }

}

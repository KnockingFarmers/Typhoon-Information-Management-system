package com.xxx.typhoon.app.controller;

import com.xxx.common.result.CommonResult;
import com.xxx.typhoon.app.entity.TyphoonData;
import com.xxx.typhoon.app.entity.TyphoonNews;
import com.xxx.typhoon.app.service.TyphoonNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 
 * @since 2022-09-02
 */
@RestController
@RequestMapping("/typhoonNews")
public class TyphoonNewsController {

    @Autowired
    TyphoonNewsService newsService;

    @PostMapping("deleteTyphoonNews")
    public CommonResult deleteTyphoonData(String newsId){
        return newsService.deleteTyphoonNews(Long.valueOf(newsId));
    }

    @PostMapping("updateTyphoonNews")
    public CommonResult updateTyphoonData(TyphoonNews typhoonNews){
        return newsService.updateTyphoonNews(typhoonNews);
    }

    @GetMapping("getTyphoonNewsListByTyphoonName")
    public CommonResult updateTyphoonData(String typhoonName){
        return newsService.getTyphoonNewsListByTyphoonName(typhoonName);
    }
}

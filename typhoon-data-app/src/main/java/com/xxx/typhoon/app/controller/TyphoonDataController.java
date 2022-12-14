package com.xxx.typhoon.app.controller;

import com.xxx.common.result.CommonResult;
import com.xxx.typhoon.app.entity.TyphoonData;
import com.xxx.typhoon.app.service.TyphoonDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 
 * @since 2022-08-31
 */
@RestController
@RequestMapping("/typhoonData")
public class TyphoonDataController {

    @Autowired
    TyphoonDataService dataService;

    @GetMapping("/getTyphoonNames")
    public CommonResult<List<String>> getTyphoonNames(){
        CommonResult<List<String>> result=new CommonResult<>();
        result.setData(dataService.getTyphoonNameDataList());
        result.setCode(200);
        result.setMessage("获取成功");
        return result;
    }

    @GetMapping("/getTyphoonDataByName")
    public CommonResult getTyphoonDataByName(String name){
        CommonResult<List<TyphoonData>> result=new CommonResult<>();
        result.setData(dataService.getTyphoonDataByName(name));
        result.setCode(200);
        result.setMessage("获取成功");
        return result;
    }

    @PostMapping("deleteTyphoonData")
    public CommonResult deleteTyphoonData(String dataId,String typhoonName){
        return dataService.deleteTyphoonData(Long.valueOf(dataId),typhoonName);
    }

    @PostMapping("updateTyphoonData")
    public CommonResult updateTyphoonData(TyphoonData typhoonData){
        return dataService.updateTyphoonData(typhoonData);
    }


}

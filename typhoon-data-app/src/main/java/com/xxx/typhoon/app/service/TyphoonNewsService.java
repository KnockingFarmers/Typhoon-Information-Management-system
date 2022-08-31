package com.xxx.typhoon.app.service;

import com.xxx.common.result.CommonResult;
import com.xxx.typhoon.app.entity.TyphoonNews;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 
 * @since 2022-08-31
 */
public interface TyphoonNewsService extends IService<TyphoonNews> {

    /**
     * 读取CSV文件并存入数据库
     * @param file
     * @return
     */
    CommonResult readCSV(MultipartFile CSVFile);

    /**
     * 读取excel
     * @param excelFile
     * @return
     */
    CommonResult redExcel(MultipartFile excelFile);

}

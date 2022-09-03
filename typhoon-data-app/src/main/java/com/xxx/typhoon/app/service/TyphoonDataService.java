package com.xxx.typhoon.app.service;

import com.xxx.common.result.CommonResult;
import com.xxx.typhoon.app.entity.TyphoonData;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 
 * @since 2022-08-31
 */
public interface TyphoonDataService extends IService<TyphoonData> {

    /**
     * 读取CSV文件并存入数据库
     * @param csvFile
     * @return
     */
    CommonResult readCSV(MultipartFile csvFile);

    /**
     * 读取excel
     * @param excelFile
     * @return
     */
    CommonResult redExcel(MultipartFile excelFile);

    /**
     * 获取数据库中都有哪些台风台风基本信息
     * @return
     */
    List<String> getTyphoonNameDataList();

    /**
     * 根据台风名字获取台风数据
     * @param typhoonName
     * @return
     */
    List<TyphoonData> getTyphoonDataByName(String typhoonName);

}

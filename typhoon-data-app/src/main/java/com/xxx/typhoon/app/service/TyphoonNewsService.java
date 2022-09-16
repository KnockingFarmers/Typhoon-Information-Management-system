package com.xxx.typhoon.app.service;

import com.xxx.common.result.CommonResult;
import com.xxx.typhoon.app.entity.TyphoonNews;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.File;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 
 * @since 2022-09-02
 */
public interface TyphoonNewsService extends IService<TyphoonNews> {

    /**
     * 读取台风微博数据文件CSV并导入数据库
     * @param cVSFile
     * @return
     */
    CommonResult readCVSFileToDB(File cVSFile);


    /**
     * 读取台风微博数据文件Excel并导入数据库
     * @param excelFile
     * @return
     */
    CommonResult readExcelFileToDB(File excelFile);

    /**
     * 删除台风新闻
     * @param newsId
     * @return
     */
    CommonResult deleteTyphoonNews(Long newsId);

    /**
     * 更新台风新闻数据
     * @param typhoonNews
     * @return
     */
    CommonResult updateTyphoonNews(TyphoonNews typhoonNews);

    /**
     * 根据台风名字获取台风新闻数据
     * @param typhoonName
     * @return
     */
    CommonResult getTyphoonNewsListByTyphoonName(String typhoonName);

}

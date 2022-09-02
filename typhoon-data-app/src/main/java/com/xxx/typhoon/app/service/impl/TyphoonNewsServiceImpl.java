package com.xxx.typhoon.app.service.impl;

import com.xxx.common.result.CommonResult;
import com.xxx.typhoon.app.entity.TyphoonNews;
import com.xxx.typhoon.app.mapper.TyphoonNewsMapper;
import com.xxx.typhoon.app.service.TyphoonNewsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 
 * @since 2022-09-02
 */
@Service
public class TyphoonNewsServiceImpl extends ServiceImpl<TyphoonNewsMapper, TyphoonNews> implements TyphoonNewsService {

    @Override
    public CommonResult readCVSFileToDB(File cVSFile) {
        return null;
    }

    @Override
    public CommonResult readExcelFileToDB(File excelFile) {
        return null;
    }
}

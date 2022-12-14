package com.xxx.typhoon.app.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xxx.common.result.CommonResult;
import com.xxx.common.util.FileUtil;
import com.xxx.tphoon.fileOperation.exception.FileCommonException;
import com.xxx.typhoon.app.entity.TyphoonNews;
import com.xxx.typhoon.app.mapper.TyphoonNewsMapper;
import com.xxx.typhoon.app.service.TyphoonNewsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

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

    @Autowired
    TyphoonNewsMapper newsMapper;

    @Override
    public CommonResult readCVSFileToDB(File csvFile) throws FileCommonException {

        FileUtil fileUtil=new FileUtil();
//        Iterator iterator = fileUtil.readCSVFile(csvFile);
        return null;
    }

    @Override
    public CommonResult readExcelFileToDB(File excelFile) {
        FileUtil fileUtil=new FileUtil();
        try {
            fileUtil.readExcelFile(excelFile,TyphoonNews.class);
        } catch (FileCommonException e) {
            //如果发生异常把文件存储到本地中

            e.printStackTrace();
        }
        return null;
    }

    @Override
    public CommonResult deleteTyphoonNews(Long newsId) {
        if (newsId!=null) {
            Lock lock=new ReentrantLock();

            lock.lock();
            try {
                newsMapper.deleteById(newsId);
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }
        return null;
    }

    @Override
    public CommonResult updateTyphoonNews(TyphoonNews typhoonNews) {

        if (typhoonNews!=null) {
            Lock lock=new ReentrantLock();
            lock.lock();
            try {
                newsMapper.updateById(typhoonNews);
            }catch (Exception e){

            }finally {
                lock.unlock();
            }

        }

        return null;
    }

    @Override
    public CommonResult getTyphoonNewsListByTyphoonName(String typhoonName) {
        if (!StringUtils.isEmpty(typhoonName)) {
            IPage<TyphoonNews> newsIPage=new Page<>();
            QueryWrapper wrapper=new QueryWrapper();
            wrapper.eq("typhoon_name",typhoonName);
            newsMapper.selectPage(newsIPage,wrapper);
            List<TyphoonNews> records = newsIPage.getRecords();
        }
        return null;
    }
}

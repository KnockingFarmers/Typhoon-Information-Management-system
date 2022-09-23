package com.xxx.typhoon.app.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxx.common.result.CommonResult;
import com.xxx.common.util.FileUtil;
import com.xxx.common.util.RedisUtil;
import com.xxx.typhoon.app.entity.TyphoonData;
import com.xxx.typhoon.app.forkjoin.TyphoonInsertTaskForkJoin;
import com.xxx.typhoon.app.mapper.TyphoonDataMapper;
import com.xxx.typhoon.app.service.TyphoonDataService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author
 * @since 2022-08-31
 */
@Service
@Slf4j
public class TyphoonDataServiceImpl extends ServiceImpl<TyphoonDataMapper, TyphoonData> implements TyphoonDataService {


    @Autowired
    TyphoonDataMapper typhoonDataMapper;

    @Autowired
    RedisUtil redisUtil;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Transactional(rollbackFor = {Exception.class, IOException.class})
    @Override
    public CommonResult readCSV(File csvFile, String typhoonName) throws Exception {

        FileUtil<TyphoonData> fileUtil = new FileUtil<>();

        List<TyphoonData> insertList = new ArrayList<>();


            List<TyphoonData> dataList = fileUtil.readCSVFile(csvFile, TyphoonData.class);

            //读取完毕后删除文件
//            csvFile.delete();
            dataList.forEach(typhoonData -> typhoonData.setTyphoonName(typhoonName));

            log.info("插入开始------>" + System.currentTimeMillis());

            ForkJoinPool forkJoinPool = new ForkJoinPool();
            forkJoinPool.submit(new TyphoonInsertTaskForkJoin(insertList, this));
            forkJoinPool.awaitTermination(2, TimeUnit.SECONDS);
            forkJoinPool.shutdown();

            log.info("插入结束------>" + System.currentTimeMillis());

        return new CommonResult();
    }

    @Transactional(rollbackFor = {Exception.class, IOException.class})
    @Override
    public CommonResult redExcel(File excelFile, String typhoonName) throws Exception {

        List<TyphoonData> dataList = null;
        FileUtil<TyphoonData> fileUtil = new FileUtil<>();

        dataList = fileUtil.readExcelFile(excelFile, TyphoonData.class);
        //读取完毕后删除文件
//        excelFile.delete();

        dataList.forEach(typhoonData -> typhoonData.setTyphoonName(typhoonName));

        log.info("插入开始------>" + System.currentTimeMillis());

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        forkJoinPool.submit(new TyphoonInsertTaskForkJoin(dataList, this));
        forkJoinPool.awaitTermination(2, TimeUnit.SECONDS);
        forkJoinPool.shutdown();

        log.info("插入结束------>" + System.currentTimeMillis());
        return null;
    }

    @Override
    public List<String> getTyphoonNameDataList() {
        QueryWrapper wrapper = new QueryWrapper();

        wrapper.select("DISTINCT typhoon_name");

        return typhoonDataMapper.selectObjs(wrapper);
    }

    @Override
    public List getTyphoonDataByName(String typhoonName) {
        List<Object> dataList=null;
        if (StringUtils.isEmpty(typhoonName)) {
            dataList= redisUtil.lGet(typhoonName, 0, -1);

            if (dataList == null || dataList.size() <= 0) {
                QueryWrapper wrapper = new QueryWrapper();
                wrapper.eq("typhoon_name", typhoonName);
                dataList = typhoonDataMapper.selectList(wrapper);

                redisUtil.lSet(typhoonName, dataList);

            }

        }
        return dataList;

    }

    @Override
    public CommonResult deleteTyphoonData(Long dataId,String typhoonName) {
        Lock lock = new ReentrantLock();
        lock.lock();
        try {

            if (dataId!=null) {

                redisUtil.del(typhoonName);
                typhoonDataMapper.deleteById(dataId);
                Thread.sleep(100);
                redisUtil.del(typhoonName);
            }
        } catch (Exception e) {

        } finally {
            lock.unlock();
        }
        return null;
    }

    @Override
    public CommonResult updateTyphoonData(TyphoonData typhoonData) {

        Lock lock = new ReentrantLock();
        lock.lock();
        try {
            if (!StringUtils.isEmpty((CharSequence) typhoonData)) {
                typhoonDataMapper.updateById(typhoonData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return null;
    }
}


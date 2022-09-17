package com.xxx.typhoon.app.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxx.common.result.CommonResult;
import com.xxx.common.util.FileUtil;
import com.xxx.typhoon.app.entity.TyphoonData;
import com.xxx.typhoon.app.forkjoin.TyphoonInsertTaskForkJoin;
import com.xxx.typhoon.app.mapper.TyphoonDataMapper;
import com.xxx.typhoon.app.service.TyphoonDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

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


    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Transactional(rollbackFor = {Exception.class, IOException.class})
    @Override
    public CommonResult readCSV(File csvFile, String typhoonName) throws Exception {

        FileUtil<TyphoonData> fileUtil = new FileUtil<>();

        List<TyphoonData> insertList = new ArrayList<>();
        try {

            Iterator<String[]> iterator = fileUtil.readCSVFile(csvFile);

            //读取完毕后删除文件
            csvFile.delete();

            //跳过标题栏
            iterator.next();
            while (iterator.hasNext()) {
                Object[] objects = Arrays.stream(iterator.next()).toArray();
                TyphoonData typhoonData = new TyphoonData();
                Date date = sdf.parse((String) objects[1]);
                typhoonData.setPublishTime(date);
                typhoonData.setUserName((String) objects[2]);
                typhoonData.setUserLink((String) objects[3]);

                //数据过长
                typhoonData.setContent((String) objects[4]);

                typhoonData.setSource((String) objects[5]);
                typhoonData.setLocationUrl((String) objects[6]);
                typhoonData.setLocationName((String) objects[7]);

                //数据过长
                typhoonData.setImageUrls((String) objects[8]);

                typhoonData.setWeiboLink((String) objects[9]);
                typhoonData.setForwardNum(Integer.parseInt((String) objects[10]));
                typhoonData.setCommentNum(Integer.parseInt((String) objects[11]));
                typhoonData.setLikeNum(Integer.parseInt((String) objects[12]));

                insertList.add(typhoonData);
            }


//            typhoonDataService.saveBatch(insertList);
        } catch (Exception e) {
            e.printStackTrace();
        }

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

        dataList.forEach(typhoonData -> {
            typhoonData.setTyphoonName(typhoonName);
        });

        log.info("插入开始------>"+System.currentTimeMillis());

        ForkJoinPool forkJoinPool=new ForkJoinPool();
        forkJoinPool.submit(new TyphoonInsertTaskForkJoin(dataList,TyphoonData.class));
        forkJoinPool.awaitTermination(2, TimeUnit.SECONDS);
        forkJoinPool.shutdown();

        log.info("插入结束------>"+System.currentTimeMillis());
        return null;
    }

    @Override
    public List<String> getTyphoonNameDataList() {
        QueryWrapper wrapper = new QueryWrapper();

        wrapper.select("DISTINCT typhoon_name");

        return typhoonDataMapper.selectObjs(wrapper);
    }

    @Override
    public List<TyphoonData> getTyphoonDataByName(String typhoonName) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("typhoon_name", typhoonName);
        return typhoonDataMapper.selectList(wrapper);
    }

    @Override
    public CommonResult deleteTyphoonData(Long dataId) {
        return null;
    }

    @Override
    public CommonResult updateTyphoonData(TyphoonData typhoonData) {
        return null;
    }
}


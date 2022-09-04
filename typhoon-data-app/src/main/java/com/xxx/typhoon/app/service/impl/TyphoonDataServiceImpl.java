package com.xxx.typhoon.app.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxx.common.result.CommonResult;
import com.xxx.common.util.FileUtil;
import com.xxx.typhoon.app.entity.TyphoonData;
import com.xxx.typhoon.app.mapper.TyphoonDataMapper;
import com.xxx.typhoon.app.service.TyphoonDataService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author
 * @since 2022-08-31
 */
@Service
public class TyphoonDataServiceImpl extends ServiceImpl<TyphoonDataMapper, TyphoonData> implements TyphoonDataService {


    @Autowired
    TyphoonDataMapper typhoonDataMapper;

    @Autowired
    FileUtil fileUtil;

    @Autowired
    TyphoonDataService typhoonDataService;

    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Transactional(rollbackFor = {Exception.class, IOException.class})
    @Override
    public CommonResult readCSV(MultipartFile csvFile,String typhoonName) {

        List<TyphoonData> insertList = new ArrayList<>();
        File file = null;
        try {

            file = fileUtil.multipartFileToFile(csvFile);
            Iterator<String[]> iterator = fileUtil.readCSVFile(file);

            //读取完毕后删除文件
            file.delete();


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

                typhoonDataMapper.insert(typhoonData);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return new CommonResult();
    }

    @Transactional(rollbackFor = {Exception.class, IOException.class})
    @Override
    public CommonResult redExcel(MultipartFile excelFile,String typhoonName) {

        File file = null;
        List<JSONObject> dataList = null;
        List<TyphoonData> insertList=new ArrayList<>();;
        try {
            file = fileUtil.multipartFileToFile(excelFile);
            dataList = fileUtil.readExcelFile(file, TyphoonData.class);

            //读取完毕后删除文件
            file.delete();

            dataList.forEach(jsonObject -> {

                TyphoonData typhoonData = new TyphoonData();
                typhoonData.setPublishTime(new Date());
                typhoonData.setUserName(jsonObject.getString("userName"));
                typhoonData.setUserLink(jsonObject.getString("userLink"));

                //数据过长
                typhoonData.setContent(StringUtils.truncate(jsonObject.getString("content"),255));
                typhoonData.setSource(jsonObject.getString("source"));
                typhoonData.setLocationUrl(jsonObject.getString("locationUrl"));
                typhoonData.setLocationName(jsonObject.getString("locationName"));

                //数据过长
                typhoonData.setImageUrls(StringUtils.truncate(jsonObject.getString("imageUrls"),255));
                typhoonData.setWeiboLink(jsonObject.getString("weiboLink"));
                typhoonData.setForwardNum(jsonObject.getInteger("forwardNum"));
                typhoonData.setCommentNum(jsonObject.getInteger("commentNum"));
                typhoonData.setLikeNum(jsonObject.getInteger("likeNum"));
                typhoonData.setTyphoonName(typhoonName);
                insertList.add(typhoonData);
            });

            typhoonDataService.saveBatch(insertList);
        } catch (Exception e) {
            e.printStackTrace();
        }


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
}


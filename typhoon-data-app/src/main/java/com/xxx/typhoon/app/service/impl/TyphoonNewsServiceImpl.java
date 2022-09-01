package com.xxx.typhoon.app.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxx.common.result.CommonResult;
import com.xxx.common.util.FileUtil;
import com.xxx.tphoon.fileOperation.service.CSVFileService;
import com.xxx.typhoon.app.entity.TyphoonNews;
import com.xxx.typhoon.app.lisenner.ExcelListener;
import com.xxx.typhoon.app.mapper.TyphoonNewsMapper;
import com.xxx.typhoon.app.service.TyphoonNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
public class TyphoonNewsServiceImpl extends ServiceImpl<TyphoonNewsMapper, TyphoonNews> implements TyphoonNewsService {

    /**
     * Excel 2007+
     */
    private static final String EXCEL_2007 = "xlsx";

    /**
     * Excel 2003
     */
    private static final String EXCEL_2003 = "xls";

    /**
     * CSV
     */
    private static final String CSV = "csv";

    @Autowired
    TyphoonNewsMapper typhoonNewsMapper;

    @Autowired
    FileUtil fileUtil;

    @Override
    public CommonResult readCSV(MultipartFile csvFile) {
        String originalFilename = csvFile.getOriginalFilename();
        StringBuilder sb = new StringBuilder();
        String fileSuffix = sb.substring(originalFilename.lastIndexOf("."));

        if (fileSuffix.equals(CSV)) {

            CSVFileService fileService = new CSVFileService();
            List<TyphoonNews> insertList = new ArrayList<>();
            try {
                Iterator<String[]> iterator = fileService.readCSV(fileUtil.multipartFileToFile(csvFile));
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                iterator.next();
                while (iterator.hasNext()) {
                    Object[] objects = Arrays.stream(iterator.next()).toArray();
                    TyphoonNews news = new TyphoonNews();
                    Date date = sdf.parse((String) objects[1]);
                    news.setPublishTime(date);
                    news.setUserName((String) objects[2]);
                    news.setUserLink((String) objects[3]);

                    //数据过长直接放弃存储
                    String content = (String) objects[4];
                    news.setContent(content.length() > 255 ? null : content);

                    news.setSource((String) objects[5]);
                    news.setLocationUrl((String) objects[6]);
                    news.setLocationName((String) objects[7]);

                    //数据过长直接放弃存储
                    String urls = (String) objects[8];
                    news.setImageUrls(urls.length() > 255 ? null : urls);

                    news.setWeiboLink((String) objects[9]);
                    news.setForwardNum(Integer.parseInt((String) objects[10]));
                    news.setCommentNum(Integer.parseInt((String) objects[11]));
                    news.setLikeNum(Integer.parseInt((String) objects[12]));

                    typhoonNewsMapper.insert(news);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return new CommonResult();
    }

    @Override
    public CommonResult redExcel(MultipartFile excelFile) {
        String originalFilename = excelFile.getOriginalFilename();
        StringBuilder sb = new StringBuilder();
        String fileSuffix = sb.substring(originalFilename.lastIndexOf("."));

        if (fileSuffix.equals(EXCEL_2007) || fileSuffix.equals(EXCEL_2003)) {
            ExcelListener excelListener = new ExcelListener();
            try {
                EasyExcel.read(excelFile.getInputStream(), excelListener).sheet().doRead();
            } catch (IOException e) {
                e.printStackTrace();
            }
            List<JSONObject> dataList = excelListener.getDataList();
            for (JSONObject jsonObject : dataList) {
                TyphoonNews news = new TyphoonNews();
                news.setPublishTime(jsonObject.getDate("publish_time"));
                news.setUserName(jsonObject.getString("user_name"));
                news.setUserLink(jsonObject.getString("user_link"));
                news.setContent(jsonObject.getString("content"));
                news.setSource(jsonObject.getString("source"));
                news.setLocationUrl(jsonObject.getString("locationUrl"));
                news.setLocationName(jsonObject.getString("locationName"));
                news.setImageUrls(jsonObject.getString("image_urls"));
                news.setWeiboLink(jsonObject.getString("weibo_link"));
                news.setForwardNum(jsonObject.getInteger("forward_num"));
                news.setCommentNum(jsonObject.getInteger("comment_num"));
                news.setLikeNum(jsonObject.getInteger("like_num"));

                typhoonNewsMapper.insert(news);
            }
        }

        return null;
    }

    @Override
    public List<String> getTyphoonNameDataList() {
        QueryWrapper wrapper=new QueryWrapper();

        wrapper.select("typhoon_name");
        wrapper.select("DISTINCT typhoon_name");

        return typhoonNewsMapper.selectList(wrapper);
    }

    @Override
    public List<TyphoonNews> getTyphoonDataByName(String typhoonName) {
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("typhoon_name",typhoonName);
        return typhoonNewsMapper.selectList(wrapper);
    }
}


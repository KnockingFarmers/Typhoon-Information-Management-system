package com.xxx.typhoon.app.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxx.common.result.CommonResult;
import com.xxx.common.util.FileUtil;
import com.xxx.typhoon.app.entity.TyphoonData;
import com.xxx.typhoon.app.mapper.TyphoonDataMapper;
import com.xxx.typhoon.app.service.TyphoonDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
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

    /**
     * Excel 2007+
     */
    public static final String EXCEL_2007 = "xlsx";

    /**
     * Excel 2003
     */
    private static final String EXCEL_2003 = "xls";

    /**
     * CSV
     */
    private static final String CSV = "csv";

    @Autowired
    TyphoonDataMapper typhoonDataMapper;

    @Autowired
    FileUtil fileUtil;

    @Override
    public CommonResult readCSV(MultipartFile csvFile) {

        List<TyphoonData> insertList = new ArrayList<>();
        File file = null;
        try {

            file = fileUtil.multipartFileToFile(csvFile);
            Iterator<String[]> iterator = fileUtil.readCSVFile(file);

            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            //跳过标题栏
            iterator.next();
            while (iterator.hasNext()) {
                Object[] objects = Arrays.stream(iterator.next()).toArray();
                TyphoonData typhoonData = new TyphoonData();
                Date date = sdf.parse((String) objects[1]);
                typhoonData.setPublishTime(date);
                typhoonData.setUserName((String) objects[2]);
                typhoonData.setUserLink((String) objects[3]);

                //数据过长直接放弃存储
                String content = (String) objects[4];
                typhoonData.setContent(content.length() > 255 ? null : content);

                typhoonData.setSource((String) objects[5]);
                typhoonData.setLocationUrl((String) objects[6]);
                typhoonData.setLocationName((String) objects[7]);

                //数据过长直接放弃存储
                String urls = (String) objects[8];
                typhoonData.setImageUrls(urls.length() > 255 ? null : urls);

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

    @Override
    public CommonResult redExcel(MultipartFile excelFile) {

        File file = null;
        List<TyphoonData> dataList = null;
        try {
            file = fileUtil.multipartFileToFile(excelFile);
            dataList = fileUtil.readExcelFile(file, TyphoonData.class);

            dataList.forEach(typhoonData -> typhoonDataMapper.insert(typhoonData));
        } catch (Exception e) {
            e.printStackTrace();
        }


        return null;
}

    @Override
    public List<String> getTyphoonNameDataList() {
        QueryWrapper wrapper = new QueryWrapper();

        wrapper.select("typhoon_name");
        wrapper.select("DISTINCT typhoon_name");

        return typhoonDataMapper.selectList(wrapper);
    }

    @Override
    public List<TyphoonData> getTyphoonDataByName(String typhoonName) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("typhoon_name", typhoonName);
        return typhoonDataMapper.selectList(wrapper);
    }
}


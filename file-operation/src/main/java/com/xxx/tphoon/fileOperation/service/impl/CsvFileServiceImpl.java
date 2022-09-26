package com.xxx.tphoon.fileOperation.service.impl;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.xxx.tphoon.fileOperation.annotation.CSVField;
import com.xxx.tphoon.fileOperation.service.CsvFileService;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @Author 甘龙
 * @Date 2022/8/31 13:00
 * @PackageName:com.xxx.tphoon.fileOperation.service
 * @ClassName: CsvFileServiceImpl
 * @Description: TODO
 * @Version 1.0
 */
public class CsvFileServiceImpl implements CsvFileService {

    private static final String CHARSET = "utf-8";


    @Override
    public Iterator readCSV(File file) {

        Iterator<String[]> iterator = null;
        try {
            CSVReader csvReader = new CSVReaderBuilder(new BufferedReader(new InputStreamReader(new FileInputStream(file), CHARSET))).build();

            iterator = csvReader.iterator();

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        return iterator;
    }

    @Override
    public List readCsv(File file, Class entity) throws FileNotFoundException, UnsupportedEncodingException, IllegalAccessException, InstantiationException {
        Field[] declaredFields = entity.getDeclaredFields();

        CSVReader csvReader = new CSVReaderBuilder(new BufferedReader(new InputStreamReader(new FileInputStream(file), CHARSET))).build();

        //读取文件后的值
        Iterator<String[]> iterator = csvReader.iterator();

        //标题栏
        String[] titles = iterator.next();

        List csvObjects = new ArrayList();

        while (iterator.hasNext()) {


            //循环读取实体类中哪个属性使用了注解
            for (Field field : declaredFields) {
                CSVField annotation = field.getAnnotation(CSVField.class);
                Object o = entity.newInstance();

                if (annotation != null && !StringUtils.isEmpty(annotation.value())) {
                    //如果该注解的值不是空的则遍历向属性赋值

                    //拿到每一行的数据
                    String[] filedValues = iterator.next();


                    //循环赋值
                    for (int i = 0; i < filedValues.length; i++) {
                        if (annotation.value().equals(titles[i])) {

                            field.setAccessible(true);
                            field.set(o, filedValues[i]);
                        }


                        csvObjects.add(o);
                    }
                }
            }
        }


        return csvObjects;
    }


}

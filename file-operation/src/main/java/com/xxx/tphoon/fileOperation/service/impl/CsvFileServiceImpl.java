package com.xxx.tphoon.fileOperation.service.impl;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.xxx.tphoon.fileOperation.annotation.CSVField;
import com.xxx.tphoon.fileOperation.service.CsvFileService;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.lang.reflect.Field;
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

    private static final String CHARSET="utf-8";


    @Override
    public Iterator readCSV(File file){

        Iterator<String[]> iterator=null;
        try {
            CSVReader csvReader=new CSVReaderBuilder(new BufferedReader(new InputStreamReader(new FileInputStream(file),CHARSET))).build();

            iterator=csvReader.iterator();

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        return iterator;
    }

    @Override
    public List readCsv(File file, Class entity) {
        Field[] declaredFields = entity.getDeclaredFields();
        for (int i = 0; i < declaredFields.length; i++) {
            CSVField annotation = declaredFields[i].getAnnotation(CSVField.class);
            if (StringUtils.isEmpty(annotation.name())) {
                String fieldValue=annotation.name();
            }
        }

        return null;
    }


}

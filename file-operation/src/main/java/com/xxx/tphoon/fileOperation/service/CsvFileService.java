package com.xxx.tphoon.fileOperation.service;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.*;
import java.util.Arrays;
import java.util.Iterator;

/**
 * @Author 甘龙
 * @Date 2022/8/31 13:00
 * @PackageName:com.xxx.tphoon.fileOperation.service
 * @ClassName: CsvFileService
 * @Description: TODO
 * @Version 1.0
 */
public class CsvFileService {

    private static final String CHARSET="utf-8";


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

}

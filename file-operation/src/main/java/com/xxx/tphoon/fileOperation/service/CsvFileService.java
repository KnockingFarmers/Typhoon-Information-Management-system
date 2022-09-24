package com.xxx.tphoon.fileOperation.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;

/**
 * @Author 甘龙
 * @Date 2022/9/21 21:30
 * @PackageName:com.xxx.tphoon.fileOperation.service
 * @ClassName: CsvFileService
 * @Description: TODO
 * @Version 1.0
 */
public interface CsvFileService<T> {

    /**
     * 读取CSV文件
     * @param file
     * @return
     */
     Iterator readCSV(File file);

    /**
     *
     * @param file
     * @param entity
     * @return
     */
     List<T> readCsv(File file,Class<T> entity) throws FileNotFoundException, UnsupportedEncodingException, IllegalAccessException;
}

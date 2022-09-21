package com.xxx.tphoon.fileOperation.service;

import java.io.File;
import java.util.Iterator;

/**
 * @Author 甘龙
 * @Date 2022/9/21 21:30
 * @PackageName:com.xxx.tphoon.fileOperation.service
 * @ClassName: CsvFileService
 * @Description: TODO
 * @Version 1.0
 */
public interface CsvFileService {

    /**
     * 读取CSV文件
     * @param file
     * @return
     */
     Iterator readCSV(File file);
}

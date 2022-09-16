package com.xxx.common.util;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xxx.tphoon.fileOperation.exception.FileCommonException;
import com.xxx.tphoon.fileOperation.lisenner.ExcelListener;
import com.xxx.tphoon.fileOperation.service.CsvFileService;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @Author
 * @Date 2022/8/31 13:16
 * @PackageName:com.xxx.common.util
 * @ClassName: FileUtil
 * @Description: 文件工具类
 * @Version 1.0
 */
public class FileUtil<T> {

    /**
     * Excel 2007+
     */
    public static final String EXCEL_2007 = ".xlsx";

    /**
     * Excel 2003
     */
    private static final String EXCEL_2003 = ".xls";

    /**
     * CSV
     */
    private static final String CSV = ".csv";

    private CsvFileService csvFileService=new CsvFileService();

    private ExcelListener excelListener = new ExcelListener();


    public String getFileType(String fileName){
        StringBuilder sb = new StringBuilder(fileName);
        String fileSuffix = sb.substring(fileName.lastIndexOf("."));
        return fileSuffix;
    }

    public File multipartFileToFile(MultipartFile file) throws Exception {
        File toFile = null;
        if (file.equals("") || file.getSize() <= 0) {
            file = null;
        } else {
            InputStream ins = null;
            ins = file.getInputStream();
            toFile = new File(file.getOriginalFilename());
            inputStreamToFile(ins, toFile);
            ins.close();
        }
        return toFile;

    }

    private static void inputStreamToFile(InputStream ins, File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<File> splitFile(File file,long size){
        return null;
    }


    /**
     * 读取csv文件
     * @param csvFile
     * @return
     * @throws FileCommonException
     */
    public Iterator<String[]> readCSVFile(File csvFile) throws FileCommonException {

        String fileSuffix = getFileType(csvFile.getName());

        if (fileSuffix.equals(CSV)){
            Iterator<String[]> iterator = csvFileService.readCSV(csvFile);
            return iterator;
        }else {
            throw new FileCommonException();
        }

    }



    /**
     * 读取excel文件
     * @param excelFile
     * @param excelEntity
     * @return
     * @throws FileCommonException
     */
    public List<T> readExcelFile(File excelFile,Class<T> excelEntity) throws FileCommonException {

        String fileSuffix = getFileType(excelFile.getName());

        if (fileSuffix.equals(EXCEL_2003)||fileSuffix.equals(EXCEL_2007)){
            EasyExcel.read(excelFile,excelEntity, excelListener).sheet().doRead();
            List<JSONObject> dataList = excelListener.getDataList();

            //拿到文件对象的所有属性
            Field[] fields=excelEntity.getDeclaredFields();

            List<T> excelObjectList=new ArrayList<>();

            //循环遍历拿出数据
                for (int j = 0; j < dataList.size(); j++) {

                    for (int i = 0; i < fields.length; i++) {
                        //打破封装 private
                        fields[i].setAccessible(true);
                    JSONObject jsonObject = dataList.get(i);


                    T t=JSON.parseObject(JSON.toJSONString(jsonObject),excelEntity);
                  excelObjectList.add(t);
                }
            }

            return excelObjectList;
        }else {
            throw new FileCommonException();
        }
    }

}

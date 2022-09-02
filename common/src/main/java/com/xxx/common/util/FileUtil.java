package com.xxx.common.util;

import com.xxx.tphoon.fileOperation.exception.FileTypeException;
import com.xxx.tphoon.fileOperation.service.CSVFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Iterator;

/**
 * @Author
 * @Date 2022/8/31 13:16
 * @PackageName:com.xxx.common.util
 * @ClassName: FileUtil
 * @Description: TODO
 * @Version 1.0
 */
public class FileUtil<T> {

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

    private CSVFileService csvFileService=new CSVFileService();

//    private ExcelListener excelListener = new ExcelListener();

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


    public Iterator<String[]> readCSVFile(File csvFile) throws FileTypeException {
        String originalFilename = csvFile.getName();
        StringBuilder sb = new StringBuilder();
        String fileSuffix = sb.substring(originalFilename.lastIndexOf("."));

        if (fileSuffix.equals(CSV)){
            Iterator<String[]> iterator = csvFileService.readCSV(csvFile);
            return iterator;
        }else {
            throw new FileTypeException();
        }
    }

}

package com.xxx.common.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * @Author
 * @Date 2022/8/31 13:16
 * @PackageName:com.xxx.common.util
 * @ClassName: FileUtil
 * @Description: TODO
 * @Version 1.0
 */
public class FileUtil {


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
}

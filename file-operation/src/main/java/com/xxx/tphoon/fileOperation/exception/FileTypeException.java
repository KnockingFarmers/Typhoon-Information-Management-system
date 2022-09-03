package com.xxx.tphoon.fileOperation.exception;

/**
 * @Author
 * @Date 2022/9/2 21:55
 * @PackageName:com.xxx.tphoon.fileOperation.exception
 * @ClassName: FileTypeException
 * @Description: 文件类型异常
 * @Version 1.0
 */
public class FileTypeException extends Exception{

    @Override
    public void printStackTrace() {
        System.out.println("This file is not of type CSV!!!");
    }
}

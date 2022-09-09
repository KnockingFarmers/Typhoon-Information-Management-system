package com.xxx.tphoon.fileOperation.exception;

/**
 * @Author
 * @Date 2022/9/2 21:55
 * @PackageName:com.xxx.tphoon.fileOperation.exception
 * @ClassName: FileCommonException
 * @Description: 文件类型异常
 * @Version 1.0
 */
public class FileCommonException extends Exception{

    public FileCommonException() {
    }

    public FileCommonException(String message) {
        super(message);
    }

    public FileCommonException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileCommonException(Throwable cause) {
        super(cause);
    }
}

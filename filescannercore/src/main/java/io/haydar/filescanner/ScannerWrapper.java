package io.haydar.filescanner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Haydar
 * @Package io.haydar.filescannercore
 * @DATE 2017-04-14
 */

public class ScannerWrapper {
    public static final String TAG = "ScannerWrapper";

    /**
     * 递归扫描
     *
     * @param absolutePath
     * @return
     */
    public static List<FileInfo> scanDirs(String absolutePath) {
        List<FileInfo> result = new ArrayList<>();
        if (FileScannerJni.isLoadJNISuccess()) {
            result = FileScannerJni.scanDirs(absolutePath);
        } else {
            result = FileScannerJava.scanDirs(absolutePath);
        }
        return result;
    }

    public static List<FileInfo> scanFiles(String filePath, String type) {
        List<FileInfo> result = new ArrayList<>();
        if (FileScannerJni.isLoadJNISuccess()) {
            result = FileScannerJni.scanFiles(filePath, type);
        } else {
            result = FileScannerJava.scanFiles(filePath);
        }
        return result;
    }

    public static long getFileLastModifiedTime(String filePath) {
        if (FileScannerJni.isLoadJNISuccess()) {
            return FileScannerJni.getFileLastModifiedTime(filePath);
        } else {
            return FileScannerJava.getFileLastModifiedTime(filePath);
        }

    }

    /**
     * 扫描目录中的文件夹(不扫描子目录),不执行递归扫描
     *
     * @param filePath
     * @return
     */
    public static List<FileInfo> scanUpdateDirs(String filePath) {
        List<FileInfo> result = new ArrayList<>();
        if (FileScannerJni.isLoadJNISuccess()) {
            result = FileScannerJni.scanUpdateDirs(filePath);
        } else {
            result = FileScannerJava.scanUpdateDirs(filePath);
        }
        return result;
    }
}

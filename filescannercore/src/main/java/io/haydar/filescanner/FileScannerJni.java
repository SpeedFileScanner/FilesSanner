package io.haydar.filescanner;


import android.util.Log;

import java.util.ArrayList;

import io.haydar.filescanner.util.FilterUtil;

/**
 * @author Haydar
 * @Package io.haydar.filescannercore
 * @DATE 2017-04-14
 */

public class FileScannerJni {

    public static final String TAG = "FileScannerJni";
    private static boolean mLoadSuccess;

    static {
        try {
            System.loadLibrary("FileScanner");
            mLoadSuccess = true;
        } catch (Exception e) {
            e.printStackTrace();
            mLoadSuccess = false;
            // Log.e(TAG, "static initializer: loadLibrary error!!!" + e.getMessage());
        }

    }


    public static boolean isLoadJNISuccess() {
        return mLoadSuccess;
    }

    /**
     * call from jni
     *
     * @param fileSize
     * @return
     */
    public static boolean isFileSizeSupport(long fileSize) {
        Log.e("WW", "isFileSizeSupport  fileSize=" + fileSize);
        return FilterUtil.isFileSizeSupport(fileSize);
    }

    /**
     * call from jni
     *
     * @param extension
     * @return
     */
    public static boolean isFileExtensionSupport(String extension) {
        Log.e("WW", "isFileExtensionSupport  extension=" + extension);
        return FilterUtil.isSupportType(extension);
    }

    public static native ArrayList<FileInfo> scanDirs(String paramString);

    public static native ArrayList<FileInfo> scanFiles(String filePath);

    public static native long getFileLastModifiedTime(String filePath);

    public static native ArrayList<FileInfo> scanUpdateDirs(String filePath);
}

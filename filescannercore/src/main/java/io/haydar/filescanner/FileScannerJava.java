package io.haydar.filescanner;

import android.text.TextUtils;
import android.util.Log;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

import io.haydar.filescanner.util.FilterUtil;
import io.haydar.filescanner.util.Util4File;

/**
 * @author wangwei on 2017/7/11.
 *         wangwei@jiandaola.com
 */
public class FileScannerJava {

    private static final String TAG = "FileScannerJava";

    static class OnlyDirFilter implements FileFilter {
        private OnlyDirFilter() {
        }

        public boolean accept(File file) {
            return file.isDirectory();
        }
    }

    static class OnlyFileFilter implements FileFilter {
        private OnlyFileFilter() {
        }

        public boolean accept(File file) {
            return !file.isDirectory();
        }
    }

    public static long getFileLastModifiedTime(String filePath) {
        if (TextUtils.isEmpty(filePath)) {
            return -1;
        }
        File file = new File(filePath);
        if (file.exists()) {
            return file.lastModified();
        }
        return -1;
    }

    /**
     * 递归扫描
     *
     * @param path
     * @return
     */
    public static List<FileInfo> scanDirs(String path) {
        return scanDirs(path, true);
    }

    /**
     * 扫描目录中的文件夹(不扫描子目录),不执行递归扫描
     *
     * @param path
     * @return
     */
    public static List<FileInfo> scanUpdateDirs(String path) {
        return scanDirs(path, false);
    }

    public static List<FileInfo> scanDirs(String path, boolean recursive) {
        int index = 0;
        ArrayList<FileInfo> result = new ArrayList<>();
        if (path != null) {
            File dir = new File(path);
            if (dir.exists() && dir.isDirectory()) {
                Log.d(TAG, path + " : 目录存在");
                if (needScan(dir)) {
                    if (isValidDir(path)) {
                        result.add(generateDirInfo(dir));
                        Log.d(TAG, "scanDirs -> add fileInfo: " + generateDirInfo(dir).toString());
                    }
                    File[] subDirs = dir.listFiles(new OnlyDirFilter());
                    if (subDirs != null) {
                        int length = subDirs.length;
                        while (index < length) {
                            File c1022c2 = subDirs[index];
                            if (isValidDir(Util4File.getFullPath(c1022c2)) && needScan(c1022c2)) {
                                result.add(generateDirInfo(c1022c2));
                                Log.d(TAG, "scanDirs -> add fileInfo: " + generateDirInfo(c1022c2).toString());
                                if (recursive) {
                                    rScanDirs(c1022c2, 2, result);
                                }
                            }
                            index++;
                        }
                    }
                } else {
                    Log.d(TAG, path + " 包含.nomedia文件或者隐藏文件夹");
                }
            }
        }
        return result;
    }

    private static void rScanDirs(File file, int depth, List<FileInfo> result) {
        if (depth < FilterUtil.getMaxDirDepth()) {
            File[] dirs = file.listFiles(new OnlyDirFilter());
            if (dirs != null) {
                for (File dir : dirs) {
                    if (isValidDir(dir.getAbsolutePath()) && needScan(dir)) {
                        result.add(generateDirInfo(dir));
                        rScanDirs(dir, depth + 1, result);
                    }
                }
            }
        }
    }

    public static List<FileInfo> scanFiles(String filePath) {
        List<FileInfo> result = new ArrayList<>();
        if (filePath != null) {
            File file = new File(filePath);
            if (file.exists() && file.isDirectory() && isValidDir(filePath) && needScan(file)) {
                File[] files = file.listFiles(new OnlyFileFilter());
                if (files != null) {
                    for (File tmp : files) {
                        if (FilterUtil.isSupportType(tmp.getAbsolutePath())) {
                            result.add(generateFileInfo(tmp));
                        }
                    }
                }
            }
        }
        return result;
    }

    private static FileInfo generateFileInfo(File file) {
        FileInfo fileInfo = new FileInfo();
        fileInfo.setFilePath(file.getAbsolutePath());
        fileInfo.setFileSize(file.length());
        fileInfo.setLastModifyTime(file.lastModified());
        fileInfo.setCount(0);
        fileInfo.setType(FileInfo.TYPE_DEFAULT);
        return fileInfo;
    }

    private static FileInfo generateDirInfo(File dir) {
        FileInfo fileInfo = new FileInfo();
        fileInfo.setFilePath(dir.getAbsolutePath());
        fileInfo.setFileSize(0);
        fileInfo.setLastModifyTime(dir.lastModified());
        fileInfo.setCount(0);
        if (dir.listFiles().length > 0) {
            fileInfo.setType(FileInfo.TYPE_DIR);
        } else {
            fileInfo.setType(FileInfo.TYPE_DEFAULT);
        }
        return fileInfo;
    }

    public static boolean needScan(File file) {
        return !((FilterUtil.isFilterNoMediaDir() && FilterUtil.containNoMediaFile(file)) || (FilterUtil.isFilterHiddenDir() && file.getName().startsWith(".")));
    }

    private static boolean isValidDir(String dir) {
        if (dir == null || FilterUtil.isInBlackList(dir)) {
            return false;
        }
        if (FilterUtil.isInWhiteList(dir)) {
            return true;
        }
        return true;
    }
}

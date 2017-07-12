package io.haydar.filescanner.util;

import android.text.TextUtils;

import java.io.File;

/**
 * @author wangwei on 2017/7/11.
 *         wangwei@jiandaola.com
 */
public class Util4File {

    public static String getFileParentDir(String filePath) {
        if (!TextUtils.isEmpty(filePath)) {
            int lastIndexOf = filePath.lastIndexOf("/");
            if (lastIndexOf < filePath.length() - 1) {
                return filePath.substring(0, lastIndexOf + 1);
            }
        }
        return "";
    }

    public static boolean isEmpty(String str) {
        return str == null || str.trim().length() <= 0;
    }

    public static String appendSeparator(String str) {
        if (isEmpty(str) || str.endsWith(File.separator)) {
            return str;
        }
        return str + File.separator;
    }

    public static String getFullPath(File file) {
        if (file.isDirectory()) {
            return appendSeparator(file.getAbsolutePath());
        }
        return file.getAbsolutePath();
    }
}

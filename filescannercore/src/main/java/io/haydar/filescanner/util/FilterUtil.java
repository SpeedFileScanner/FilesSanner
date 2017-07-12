package io.haydar.filescanner.util;

import android.text.TextUtils;
import android.util.Log;

import java.io.File;
import java.net.URI;
import java.util.HashMap;

import io.haydar.filescanner.ScannerConfig;


/**
 * @author wangwei on 2017/7/10.
 *         wangwei@jiandaola.com
 */
public class FilterUtil {
    public static final String TAG = "FilterUtil";
    private static int MAX_DIR_DEPTH = 20;
    private static String[] sBlackList = ScannerConfig.BLACK_LIST;
    private static boolean sFilterHiddenDir = true;
    private static boolean sFilterMicroMsg = true;
    private static boolean sFilterNoMediaDir = true;
    private static String[] sWhiteList = ScannerConfig.WHITE_LIST;
    public static final String NOMEDIA = ".nomedia";

    public static long sFileSizeFilter = 100 * 1024;//100K
    public static long sMediaTimeLengthFilter = 60 * 1000;//60s

    private static boolean sFilterMediaLength = false;


    public static void setFileSizeFilter(int fileSize) {
        sFileSizeFilter = fileSize;
    }

    public static long getFileSizeFilter() {
        return sFileSizeFilter;
    }

    public static void setMediaTimeLengthFilter(long length) {
        sMediaTimeLengthFilter = length;
    }

    public static long getMediaTimeLengthFilter() {
        return sMediaTimeLengthFilter;
    }

    public static int getMaxDirDepth() {
        return MAX_DIR_DEPTH;
    }

    public static void setMaxDirDepth(int depth) {
        MAX_DIR_DEPTH = depth;
    }

    public static void setsWhiteList(String[] whiteList) {
        sWhiteList = whiteList;
    }

    public static boolean isFilterNoMediaDir() {
        return sFilterNoMediaDir;
    }

    public static boolean isFilterHiddenDir() {
        return sFilterHiddenDir;
    }

    public static boolean isFilterMicroMsg() {
        return sFilterMicroMsg;
    }

    public static void setFilterNoMediaDir(boolean filterNoMediaDir) {
        sFilterNoMediaDir = filterNoMediaDir;
    }

    public static boolean getDebugStatus() {
        return ScannerConfig.JNI_DEBUG;
    }

    public static String[] getBlackList() {
        return sBlackList;
    }

    public static String[] getWhiteList() {
        return sWhiteList;
    }

    public static boolean isSupportType(String filePath) {
        if (TextUtils.isEmpty(filePath)) {
            return false;
        }
        return MediaFile.getSingleton().isAudioFileTypeByPath(filePath);
    }

    public static boolean isFileSizeSupport(String filePath) {
        if (TextUtils.isEmpty(filePath)) {
            return false;
        }
        File file = new File(filePath);
        return file.length() > sFileSizeFilter;
    }

    public static boolean isFileSizeSupport(long length) {
        Log.d(TAG, "isFileSizeSupport length=" + length + ";sFileSizeFilter=" + sFileSizeFilter);
        return length > sFileSizeFilter;
    }

    public static boolean isMediaFileTimeLengthSupport(String filePath) {
        if (!sFilterMediaLength) {
            return true;
        }
        if (TextUtils.isEmpty(filePath)) {
            return false;
        }
        File file = new File(filePath);
        URI uri = file.toURI();
        String duration = null;
        android.media.MediaMetadataRetriever mmr = new android.media.MediaMetadataRetriever();
        try {
            HashMap<String, String> headers = new HashMap<>();
            headers.put("User-Agent", "Mozilla/5.0 (Linux; U; Android 4.4.2; zh-CN; MW-KW-001 Build/JRO03C) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 UCBrowser/1.0.0.001 U4/0.8.0 Mobile Safari/533.1");
            mmr.setDataSource(uri.toString(), headers);
            duration = mmr.extractMetadata(android.media.MediaMetadataRetriever.METADATA_KEY_DURATION);
        } catch (Exception ex) {
        } finally {
            mmr.release();
        }
        LogUtil.e(TAG, "duration " + duration);
        return true;
    }

    public static boolean isInBlackList(String str) {
        for (CharSequence contains : sBlackList) {
            if (str.contains(contains)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isInWhiteList(String str) {
        for (CharSequence contains : sWhiteList) {
            if (str.contains(contains)) {
                return true;
            }
        }
        return false;
    }

    public static boolean containNoMediaFile(File file) {
        if (file == null || !file.isDirectory()) {
            return false;
        }
        return new File(file.getAbsolutePath() + NOMEDIA).exists();
    }
}

package io.haydar.filescanner;

/**
 * @author wangwei on 2017/7/11.
 *         wangwei@jiandaola.com
 */
public class ScannerConfig {
    public static final String[] BLACK_LIST = new String[]{"DuoKan/", "TencentMapSDK/", "Tencent/QQSecure/", "Tencent/tassistant/log/", "Tencent/wtlogin/", "qqmusic/qrc/", "qqmusic/lyric/", "qqmusic/album/", "qqmusic/log/", "ttpod/lyric/", "kugou/lyrics/", "DCIM/Albummanager/", "clockworkmod/", "RM/res/", "com.eg.android.AlipayGphone", "com.tencent.karaoke", "com.tencent.karaoke-ext", "Tencent/karaoke", "com.taobao.trip", "kibey_echo/offline/", "iReader/tmp/", "qqmusic/fingerPrint/", "com.taobao.taobao"};
    public static boolean DEBUG = BuildConfig.DEBUG;
    public static final String[] DEFAULT_SONG_FILE_DIRS = new String[]{"qqmusic/import/", "qqmusic/song/", "kgmusic/download/", "ttpod/song/", "Baidu_music/download/", "KuwoMusic/music/", "DUOMI/down/", "tencent/QQfile_recv/"};
    public static boolean JNI_DEBUG = false;
    public static final int MAX_DIR_DEPTH = 20;
    public static final String PERFORMANCE_TAG = "performance_test";
    public static final String SCANNER_SPEED_TAG = "scanner_speed_test";
    public static final String[] SCAN_TYPE_BLACK_LIST = new String[]{"M3U8", "SMF", "ISO"};
    public static final String[] SUPPORTED_QQMUSIC_FILE_TYPE = new String[]{"MQCC", "TMP", "OFL", "EFE"};
    public static final String[] SUPPORTED_SCAN_TYPE = new String[]{"APE", "MAC", "FLAC", "WAV"};
    public static final String TAG = "FILE_SCANNER";
    public static final String[] WHITE_DIR = new String[]{"Android/data"};
    public static final String[] WHITE_LIST = new String[0];
}

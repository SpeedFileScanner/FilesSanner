package io.haydar.filescanner;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author Haydar
 * @Package io.haydar.filescannercore
 * @DATE 2017-04-14
 */
public class FileInfo {
    private int count;
    private String filePath;
    private long fileSize;
    private long lastModifyTime;

    @FileType
    private int type;//1文件夹 ;2 file

    public static final int TYPE_DEFAULT = 0;
    public static final int TYPE_DIR = 1;
    public static final int TYPE_MEDIA = 2;

    @IntDef(value = {TYPE_DEFAULT, TYPE_DIR, TYPE_MEDIA})
    @Retention(RetentionPolicy.SOURCE)
    public @interface FileType {
    }

    public FileInfo() {
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public Long getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(long lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    public int getType() {
        return type;
    }

    public void setType(@FileType int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "FileInfo{" +
                "count=" + count +
                ", filePath='" + filePath + '\'' +
                ", fileSize=" + fileSize +
                ", lastModifyTime=" + lastModifyTime +
                ", type=" + type +
                '}';
    }
}

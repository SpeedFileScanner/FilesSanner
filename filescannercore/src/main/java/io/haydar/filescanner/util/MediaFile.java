package io.haydar.filescanner.util;

import android.webkit.MimeTypeMap;

/**
 * @author wangwei on 2017/7/6.
 *         wangwei@jiandaola.com
 */
public class MediaFile {

    private final MimeTypeMap mMimeTypeMapInstance;

    private MediaFile() {
        mMimeTypeMapInstance = MimeTypeMap.getSingleton();
    }

    private static Singleton<MediaFile> gInstance = new Singleton<MediaFile>() {
        @Override
        protected MediaFile create() {
            return new MediaFile();
        }
    };

    public static MediaFile getSingleton() {
        return gInstance.get();
    }

    public boolean hasMimeType(String mimeType) {
        boolean has = mMimeTypeMapInstance.hasMimeType(mimeType);
        if (!has) {
            return CopySysMediaFile.isMimeTypeMedia(mimeType);
        }
        return has;
    }

    public String getFileExtensionFromUrl(String url) {
        return mMimeTypeMapInstance.getFileExtensionFromUrl(url);
    }

    public String getMimeTypeFromExtension(String extension) {
        return mMimeTypeMapInstance.getMimeTypeFromExtension(extension);
    }

    public String getMimeTypeForPath(String path) {
        return CopySysMediaFile.getMimeTypeForFile(path);
    }

    /**
     * Return true if the given extension has a registered MIME type.
     *
     * @param extension A file extension without the leading '.'
     * @return True iff there is an extension entry in the map.
     */
    public boolean hasExtension(String extension) {
        return mMimeTypeMapInstance.hasExtension(extension);
    }

    public String getExtensionFromMimeType(String mimeType) {
        return mMimeTypeMapInstance.getExtensionFromMimeType(mimeType);
    }


    public boolean isAudioFileTypeByPath(String path) {
        CopySysMediaFile.MediaFileType fileType = CopySysMediaFile.getFileType(path);
        if (fileType == null) {
            return false;
        }
        return CopySysMediaFile.isAudioFileType(fileType.fileType);
    }

    public boolean isAudioFileTypeByMimeType(String mimeType) {
        int fileType = CopySysMediaFile.getFileTypeForMimeType(mimeType);
        return CopySysMediaFile.isAudioFileType(fileType);
    }

    public boolean isVideoFileTypeByPath(String path) {
        CopySysMediaFile.MediaFileType fileType = CopySysMediaFile.getFileType(path);
        return CopySysMediaFile.isVideoFileType(fileType.fileType);
    }

    public boolean isVideoFileTypeByMimeType(String mimeType) {
        int fileType = CopySysMediaFile.getFileTypeForMimeType(mimeType);
        return CopySysMediaFile.isVideoFileType(fileType);
    }

    public boolean isImageFileTypeByPath(String path) {
        CopySysMediaFile.MediaFileType fileType = CopySysMediaFile.getFileType(path);
        return CopySysMediaFile.isImageFileType(fileType.fileType);
    }

    public boolean isImageFileTypeByMimeType(String mimeType) {
        int fileType = CopySysMediaFile.getFileTypeForMimeType(mimeType);
        return CopySysMediaFile.isImageFileType(fileType);
    }

    public boolean isMimeTypeMedia(String mimeType) {
        return CopySysMediaFile.isMimeTypeMedia(mimeType);
    }
}

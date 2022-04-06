package com.atguigu.boot.utils.file;

import com.atguigu.boot.utils.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 全局配置类
 *
 * @author lizhi
 */
@Component
@ConfigurationProperties(prefix = "file")
public class FileConfig {

    /**
     * 顺斜杠
     */
    private static final String SLASH = "/";

    /**
     * 通用下载路径
     */
    private static String downloadUrl = "/common/file/download?fileName=";

    /**
     * 文件存放基础路径
     */
    private static String fileBasePath;


    public static String getFileBasePath() {
        return fileBasePath;
    }

    public void setDownloadUrl(String downloadUrl) {
        if (StringUtils.isNotEmpty(downloadUrl)) {
            FileConfig.downloadUrl = downloadUrl;
        }
    }

    public void setFileBasePath(String fileBasePath) {
        if (fileBasePath.endsWith(SLASH)) {
            FileConfig.fileBasePath = StringUtils.substring(fileBasePath, 0, fileBasePath.length() - 1);
        } else {
            FileConfig.fileBasePath = fileBasePath;
        }
    }

    /**
     * 获取上传路径
     */
    public static String getUploadPath() {
        return getFileBasePath() + "/upload";
    }

    /**
     * 获取下载路径
     */
    public static String getDownloadPath() {
        return getFileBasePath() + "/download/";
    }

    /**
     * 获取导入上传路径
     */
    public static String getImportPath() {
        return getFileBasePath() + "/import";
    }

    /**
     * 获取通用下载url路径
     */
    public static String getDownloadUrl() {
        return downloadUrl;
    }
}

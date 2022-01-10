package com.atguigu.boot.utils;


import com.atguigu.boot.exception.BizException;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.UUID;

/**
 * UploadFileUtil
 *
 * @author yaodd
 * @date 2020/6/18
 */
public class UploadFileUtil {

    /**
     * 文件上传
     *
     * @author yaodd
     * @date 2020/6/19
     * @param path 文件上传的路径
     * @param file 文件
     * @return java.lang.String
     */
    public static String fileUpload(String path, MultipartFile file) {
        Path fileStorageLocation = Paths.get(path).toAbsolutePath().normalize();
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));

        try {
           // fileName = UUID.randomUUID().toString().replace("-", "") + "_" + fileName;
            fileName = UUID.randomUUID().toString().replace("-", "") + fileName.substring(file.getOriginalFilename().lastIndexOf("."));

            Files.createDirectories(fileStorageLocation);

            Path targetLocation = fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            throw new BizException("Could not store file " + fileName + ". Please try again later!");
        }
        return fileName;
    }

    public static String fileUploadOriginalFilename(String path, MultipartFile file) {
        Path fileStorageLocation = Paths.get(path).toAbsolutePath().normalize();
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));

        try {
            //fileName = UUID.randomUUID().toString().replace("-", "") + "_" + fileName;

            Files.createDirectories(fileStorageLocation);

            Path targetLocation = fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            throw new BizException("Could not store file " + fileName + ". Please try again later!");
        }
        return fileName;
    }

}

package com.yjl.controller;

import com.yjl.pojo.Result;
import com.yjl.utils.AliOssUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @Author: DLMU 袁佳林
 * @Date: 2023/11/18 14:46
 * @Description:
 **/
@RestController
public class FileUploadController {
    @PostMapping("/upload")
    public Result<String> upload(MultipartFile file) throws Exception {
        String originalFilename = file.getOriginalFilename();
        String fileName= UUID.randomUUID().toString()+originalFilename.substring(originalFilename.lastIndexOf('.'));
        String url = AliOssUtil.uploadFile(fileName, file.getInputStream());
        return Result.success(url);
    }
}

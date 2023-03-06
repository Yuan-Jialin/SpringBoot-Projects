package com.lanqiao.jd.controller;

import com.auth0.jwt.JWT;
import com.lanqiao.jd.annotations.UserLoginToken;
import com.lanqiao.jd.util.FileUtil;
import com.lanqiao.jd.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;


@RestController
public class TestController {
    @Autowired
    FileUtil fileUtil;

    @PostMapping("/test")
    public void test() throws FileNotFoundException {
        File path = new File(ResourceUtils.getURL("classpath:").getPath());
        if(!path.exists()) path = new File("");
        System.out.println("path:"+path.getAbsolutePath());
    }

    @PostMapping("/testUpload")
    public Result testUpload(@RequestParam(name = "file") MultipartFile file){
        String relaPath = fileUtil.fileUpload(file, 2);


        String absoPath = relaPath+fileUtil.rootPath;
//
//        ProductImgList productImgList = new ProductImgList();
//        productImgList.setImgUrl(relaPath);
        return  Result.createSuccessResult(absoPath);
    }

    @UserLoginToken
    @PostMapping("testGetUserId")
    public Result testGetUserId(HttpServletRequest httpServletRequest){
        String token = httpServletRequest.getHeader("token");// 从 http 请求头中取出 token
        String userId = JWT.decode(token).getAudience().get(0);
        return Result.createSuccessResult(userId);
    }
}

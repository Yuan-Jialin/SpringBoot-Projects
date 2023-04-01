package com.example.reggie.controller;

import com.example.reggie.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.UUID;

/**
 * @Author 袁佳林
 * @Description
 * @Date 2023/4/1 15:39
 */
@Slf4j
@RequestMapping("/common")
@RestController
public class CommonController {
    @Value("${reggie.img.path}")
    private String BasePath;
    //文件上传
    //变量命必须和前端保持一致
    @PostMapping("/upload")
    public R<String> upload(MultipartFile file) throws IOException {
    //log.info(file.toString());
        String originalFilename = file.getOriginalFilename();
        String substring = originalFilename.substring(originalFilename.lastIndexOf("."));
        String s = UUID.randomUUID().toString() + substring;
        File dir=new File(BasePath);
        if(!dir.exists()){
            dir.mkdirs();
        }

        try {
            file.transferTo(new File(BasePath+s));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return R.success(s);
    }
    @GetMapping("/download")
    public void download(String name, HttpServletResponse response)  {

        try {
            FileInputStream fileInputStream=new FileInputStream(BasePath+name);
            ServletOutputStream outputStream = response.getOutputStream();
            int len=0;
            response.setContentType("image/jpeg");
            byte bytes[]=new byte[2048];
            while ((len=fileInputStream.read(bytes))!=-1)
            {
                outputStream.write(bytes,0,len);
                outputStream.flush();
            }
            outputStream.close();
            fileInputStream.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}

package com.lanqiao.jd.util;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 刘佳昇
 * @Date 2019/8/8 15:29
 */

@Component
public class FileUtil {
//    图片路径
    @Value("${img.path}")
    public String imgPath;

    @Value("${pdf.path}")
    public String pdfPath;

    @Value("${mp3.path}")
    public String mp3Path;

    @Value("${root.path}")
    public String rootPath;
    /**
     * 文件上传
     *
     * @param file
     * @return
     * flag 1-pdf 2-img 3-mp3
     *
     */
    public String fileUpload(MultipartFile file, Integer flag) {
        //判断文件是否为空
        if (file.isEmpty()) {
            return "-1";
        }

        String fileName = file.getOriginalFilename();

        String prePath ="";


        switch(flag){
            case 1:
//                pdf
                prePath=this.rootPath+this.pdfPath;
                break;
            case 2:
//                img
                prePath=this.rootPath+this.imgPath;
                break;
            case 3:
//                mp3
                prePath=this.rootPath+this.mp3Path;
                break;
        }
        //加个时间戳，尽量避免文件名称重复
        String path = prePath + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "_" + fileName;
        File dest = new File(path);

        String[] relativelyPath = path.split("static");
        //判断文件是否已经存在
        if (dest.exists()) {
            return "-2";
        }

        //判断文件父目录是否存在
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdir();
        }

        try {
            file.transferTo(dest); //保存文件
        } catch (IOException e) {
            return "-3";
        }

        return relativelyPath[1];
    }

    /**
     * 文件下载
     *
     * @param response
     * @param filePathName
     * @return
     */
    public String downloadFile(HttpServletResponse response, String filePathName) {
        File file = new File(filePathName);
        if (!file.exists()) {
            return "-1";
        }

        response.reset();
        response.setHeader("Content-Disposition", "attachment;fileName=" + filePathName);

//        jdk7新写法，由于实现了autucloseable接口，定义在try中的流会自动关闭
        try(OutputStream os = response.getOutputStream()) {
            InputStream inStream = new FileInputStream(filePathName);
//            OutputStream os = response.getOutputStream();

            byte[] buff = new byte[1024];
            int len = -1;
            while ((len = inStream.read(buff)) > 0) {
                os.write(buff, 0, len);
            }

//            os.flush();
//            os.close();
            inStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            return "-2";
        }

        return "0";
    }

    /**
     * 获取当前时间
     * @return
     */
    public String getCurrTime(){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }


    /**
     * 删除文件
     * @param fileName
     * @return
     */
    public String deleteFile(String fileName){
        File file = new File(fileName);
        if (!file.exists()){
            return "-2";
        }
        if (file.delete()){
            return "0";
        }else {
            return "-1";
        }

    }

}

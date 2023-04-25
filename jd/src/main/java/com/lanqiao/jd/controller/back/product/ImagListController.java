package com.lanqiao.jd.controller.back.product;

import com.lanqiao.jd.entity.ProductImgList;
import com.lanqiao.jd.service.ProductImgListService;
import com.lanqiao.jd.util.FileUtil;
import com.lanqiao.jd.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
/**
 * @Author 袁佳林
 * @Description
 * @Date 2023/3/1 15:39
 */
@RestController
@RequestMapping("/imgList")
public class ImagListController {
    @Autowired
    ProductImgListService productImgListService;
    @Autowired
    FileUtil fileUtil;

    @PostMapping("/insertImgList")
    public Result insertImgList(@RequestParam(name = "file") MultipartFile file,@RequestParam(name = "itemId") int itemId){
        String imgUrl = fileUtil.fileUpload(file, 2);
        ProductImgList productImgList = new ProductImgList();
        productImgList.setImgUrl(imgUrl);
        productImgList.setProductItemId(itemId);
        return productImgListService.insertImgList(productImgList);
    }

    @PostMapping("/deleteImgList")
    public Result deleteImgList(@RequestParam(name = "imgId") int imgId){
        return productImgListService.deleteImgListById(imgId);
    }


    @PostMapping("/changeImgList")
    public Result changeImgList(@RequestParam(name = "file") MultipartFile file,@RequestParam(name = "imgId") int imgId){
        String imgUrl = fileUtil.fileUpload(file,2);
        return productImgListService.changeImgList(imgUrl,imgId);
    }

    @PostMapping("/selectImgListByItemId")
    public Result selectImgList(@RequestParam(name = "itemId") int itemId){
        return productImgListService.selectAllImgList(itemId);
    }


}

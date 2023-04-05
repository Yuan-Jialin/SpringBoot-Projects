package com.lanqiao.jd.controller.back.product;


import com.lanqiao.jd.entity.Product;
import com.lanqiao.jd.service.ProductService;
import com.lanqiao.jd.util.FileUtil;
import com.lanqiao.jd.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    FileUtil fileUtil;

    //插入product，传入图片和product类
    @PostMapping("/insertProduct")
    public Result insertProduct( Product sourceProduct,MultipartFile file){
       // System.out.println("dasdasdadasd");
        System.out.println(sourceProduct.getProductName());
        System.out.println("接受到文件了"+file.getName());
        System.out.println(sourceProduct.getProductName());
       // String imgUrl = fileUtil.fileUpload(file, 2);
        Product product = new Product();
        //product.setProductImgUrl(imgUrl);
        product.setBusinessId(sourceProduct.getBusinessId());
        product.setProductName(sourceProduct.getProductName());
        product.setProductPrice(sourceProduct.getProductPrice());
        product.setStock(sourceProduct.getStock());
        return productService.insertProduct(product);
    }

    //根据主键删除，传值productId
    @PostMapping("/delectProduct")
    public Result delectProduct(@RequestParam(name = "productId") int productId){
        return productService.deleteProduct(productId);
    }


    //查看商品信息,传入主键
    @PostMapping("/selectProduct")
    public Result selectProduct(@RequestParam(name = "businessId") int businessId){
        return productService.selectProduct(businessId);
    }

    //后台商家管理模块->模糊查询
    //need:businessId productName
    @PostMapping("/selectByProductNameBack")
    public Result selectByProductName(Product product){
        return productService.selectByProductNameBack(product);
    }
}

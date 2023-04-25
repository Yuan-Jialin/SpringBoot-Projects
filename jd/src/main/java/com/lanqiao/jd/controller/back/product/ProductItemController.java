package com.lanqiao.jd.controller.back.product;


import com.lanqiao.jd.entity.ProductItem;
import com.lanqiao.jd.service.ProductItemService;
import com.lanqiao.jd.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author 袁佳林
 * @Description
 * @Date 2023/2/1 22:39
 */
@RestController
@RequestMapping("/productItem")
public class ProductItemController {
    @Autowired
    ProductItemService productItemService;

    //添加信息
    @PostMapping("/insertProductItem")
    public Result insertProductItem(ProductItem productItem){
        return productItemService.insertProductItem(productItem);
    }

    //删除信息
    @PostMapping("/deleteProductItem")
    public Result deleteProductItem(@RequestParam(name = "productItemId") int productItemId){
        return productItemService.deleteProductItem(productItemId);
    }

    //修改信息
    @PostMapping("/changeProductItem")
    public Result changeProductItem(ProductItem productItem){
        return productItemService.changeProductItem(productItem);
    }

    //查询信息，传入productId
    @PostMapping("/selectProductItem")
    public Result selectProductItem(@RequestParam(name = "productId") int productId){
        return productItemService.selectProductItemByProductId(productId);
    }
}

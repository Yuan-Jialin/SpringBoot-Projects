package com.lanqiao.jd.controller.back.user;

import com.lanqiao.jd.entity.UserAddress;
import com.lanqiao.jd.service.UserAddressService;
import com.lanqiao.jd.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
/**
 * @Author 袁佳林
 * @Description
 * @Date 2023/4/1 15:25
 */
@RestController
@RequestMapping("/userBack")
public class UserAddressController {
    @Autowired
    UserAddressService userAddressService;

    @PostMapping("/insertAddress")
    public Result insertAddress(UserAddress userAddress){
        return userAddressService.insertUserAddress(userAddress);
    }

    @PostMapping("deleteAddress")
    public Result deleteAddress(@RequestParam(name = "userAddressId") int userAddressId){
        return userAddressService.deleteUserAddress(userAddressId);
    }
}

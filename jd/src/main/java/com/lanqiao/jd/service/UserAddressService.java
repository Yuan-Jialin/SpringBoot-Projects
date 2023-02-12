package com.lanqiao.jd.service;

import com.lanqiao.jd.entity.UserAddress;
import com.lanqiao.jd.util.Result;

public interface UserAddressService {
    public Result insertUserAddress(UserAddress userAddress);

    public Result deleteUserAddress(int userAddressId);

    public Result getAddress(int userId);
}

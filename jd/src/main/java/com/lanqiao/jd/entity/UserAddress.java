package com.lanqiao.jd.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAddress {
    private Integer userAddressId;

    private String address;

    private String receiveTel;

    private String receiveName;

    private Integer userId;

    public UserAddress(String address, String receiveTel, String receiveName, Integer userId) {
        this.address = address;
        this.receiveTel = receiveTel;
        this.receiveName = receiveName;
        this.userId = userId;
    }
}
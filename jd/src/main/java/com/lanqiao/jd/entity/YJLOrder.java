package com.lanqiao.jd.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author 袁佳林
 * @Description
 * @Date 2023/5/7 21:27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class YJLOrder {

    private String status;
    private String username;
        private String address;
        private double price;
}

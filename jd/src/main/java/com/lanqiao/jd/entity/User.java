package com.lanqiao.jd.entity;

import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer userId;

    private String userName;

    private String password;

    private BigDecimal balance;

    private String phoneNumber;

    public User(String userName,String password,BigDecimal balance,String phoneNumber){

        this.userName=userName;
        this.password=password;
        this.balance=balance;
        this.phoneNumber=phoneNumber;
    }

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
}
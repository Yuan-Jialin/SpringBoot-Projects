package com.lanqiao.jd.util;

import org.springframework.stereotype.Component;

@Component
public class CodeUtil {
    private static int newcode;
    public static int getNewcode() {
        return newcode;
    }
    public static void setNewcode(){
        newcode = (int)(Math.random()*9999)+100;  //每次调用生成一次四位数的随机数
    }
}

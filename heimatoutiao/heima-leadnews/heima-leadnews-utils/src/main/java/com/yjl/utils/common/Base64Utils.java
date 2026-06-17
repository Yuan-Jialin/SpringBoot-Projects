package com.yjl.utils.common;


import java.util.*;

public class Base64Utils {

    /**
     * 解码
     *
     * @param base64
     * @return
     */
    public static byte[] decode(String base64) {
        Base64.Decoder decoder = Base64.getDecoder();
        try {
            // Base64解码
            byte[] b = decoder.decode(base64);

            /*for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {// 调整异常数据
                    b[i] += 256;
                }
            }*/
            return b;
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * 编码
     *
     * @param data
     * @return
     * @throws Exception
     */
    public static String encode(byte[] data) {
        Base64.Encoder encoder = Base64.getEncoder();
        //encoder.encodeToString(data);
        return encoder.encodeToString(data);
    }
}
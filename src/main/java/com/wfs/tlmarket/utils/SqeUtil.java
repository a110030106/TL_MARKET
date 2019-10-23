package com.wfs.tlmarket.utils;

/**
 * 创建人：王福顺  创建时间：2019/10/23
 */
public class SqeUtil {

    /**
     * 生成 用户编号
     * @return
     */
    public static String createUserNo() {
        // 3位随机数
        int threeRandom = (int)(Math.random() * 1000);
        // 拼接时间戳，并转换16进制
        String hexString = Long.toHexString(Long.valueOf(threeRandom + "" + System.currentTimeMillis()));
        // 截取8位
        String userNo = hexString.substring(0,8);
        return userNo;
    }

    public static void main(String[] args) {

    }
}

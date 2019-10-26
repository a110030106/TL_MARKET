package com.wfs.tlmarket.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;

/**
 * 创建人：王福顺  创建时间：2019/10/23
 */
public class MD5Util {


    public static String md5(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("md5");

            byte[] bytes = md.digest(password.getBytes());

            String str = Base64.getEncoder().encodeToString(bytes);

            return str;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
//        System.out.println(md5(""));
        System.out.println(new Date());
    }
}

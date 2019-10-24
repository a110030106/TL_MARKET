package com.wfs.tlmarket.utils;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * 创建人：王福顺  创建时间：2019/10/24
 */
public class AESUtil {

    private static final String sKey = "AW4D5F1S63F1D5SS";

    private static SecretKeySpec skeySpec;
    private static Cipher cipher;

    static {
        try {
            skeySpec = new SecretKeySpec(sKey.getBytes("utf-8"), "AES");
            cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");//"算法/模式/补码方式"
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    // 加密
    public static String Encrypt(String sSrc){
        if (sKey == null) {
            System.out.print("Key为空null");
            return null;
        }
        // 判断Key是否为16位
        if (sKey.length() != 16) {
            System.out.print("Key长度不是16位");
            return null;
        }
        byte[] encrypted = null;
        try {
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
            encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        // 此处使用BASE64做转码功能，同时能起到2次加密的作用。
        return new Base64().encodeToString(encrypted);
    }

    // 解密
    public static String Decrypt(String sSrc) {

        // 判断Key是否正确
        if (sKey == null) {
            System.out.print("Key为空null");
            return null;
        }
        // 判断Key是否为16位
        if (sKey.length() != 16) {
            System.out.print("Key长度不是16位");
            return null;
        }
        // 先用base64解密
        byte[] encrypted1 = new Base64().decode(sSrc);
        try {
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            byte[] original = cipher.doFinal(encrypted1);
            String originalString = new String(original);
            return originalString;
        } catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }
    }

    public static void main(String[] args) {
        /*
         * 此处使用AES-128-ECB加密模式，key需要为16位。
         */
        // 需要加密的字串
        String cSrc = "1234";
        System.out.println(cSrc);
        // 加密
        String enString = AESUtil.Encrypt(cSrc);
        System.out.println("加密后的字串是：" + enString);

        // 解密
        String DeString = AESUtil.Decrypt(enString);
        System.out.println("解密后的字串是：" + DeString);
    }

}

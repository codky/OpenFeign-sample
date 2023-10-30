package com.example.feignstudy2.encrypt;

import org.junit.jupiter.api.Test;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.*;

class CasCryptoMD5Test {

    @Test
    void md5() {
        String md5 = CasCryptoMD5.getMD5("1234");
        System.out.println("md5 = " + md5); // md5 = 81dc9bdb52d04dc20036dbd8313ed055
    }

    @Test
    void md5_test() {
        String pwd = "1234";
        String MD5 = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(pwd.getBytes());
            byte byteData[] = md.digest();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }
            MD5 = sb.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            MD5 = null;
        }
        System.out.println("md5_test() = " + MD5); // md5_test() = 81dc9bdb52d04dc20036dbd8313ed055
//        return MD5;
    }

}

package com.demo.carrental.common;

import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class MD5PwdUtil {

    public static String md5(String src) {
        return DigestUtils.md5DigestAsHex(src.getBytes(StandardCharsets.UTF_8));
    }

    private static final String salt = "c3ViIjoic3Z";

    private static String inputPassToFormPass(String inputPass) {
        String str = ""+salt.charAt(0)+salt.charAt(2) + inputPass +salt.charAt(5) + salt.charAt(4);
        return md5(str);
    }

    private static String formPassToDBPass(String formPass, String salt) {
        String str = ""+salt.charAt(0)+salt.charAt(2) + formPass +salt.charAt(5) + salt.charAt(4);
        return md5(str);
    }

    public static String getRandomStr(){
        String org = UUID.randomUUID().toString().replaceAll("-","");
        return org.substring(0,6);
    }

    public static String inputPassToDbPass(String inputPass, String salt) {
        String formPass = inputPassToFormPass(inputPass);
        return formPassToDBPass(formPass, salt);
    }

}

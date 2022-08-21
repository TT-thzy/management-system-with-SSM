package com.wt.utils;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @program: hm_ssm
 * @description:
 * @author: Mr.Wang
 * @create: 2021-07-30 09:40
 **/
public class BCryptPasswordEncoderUtils {

    private static BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();

    public static String encoding(String password){
        return bCryptPasswordEncoder.encode(password);
    }

    @Test
    public void test(){
        System.out.println(encoding("123"));
    }
}

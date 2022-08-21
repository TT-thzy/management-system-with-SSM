package com.wt.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @program: hm_ssm
 * @description:
 * @author: Mr.Wang
 * @create: 2021-07-15 18:44
 **/
public class DateUtils {

    //日期转换成字符串
    public static String dateToString(Date date, String patt) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(patt);
        String format = simpleDateFormat.format(date);
        return format;
    }

    //字符串转日期
    public static Date stringToDate(String date,String patt){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(patt);
        Date parse = null;
        try {
            parse = simpleDateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parse;
    }

}

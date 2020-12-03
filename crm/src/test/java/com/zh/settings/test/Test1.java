package com.zh.settings.test;

import com.zh.crm.utils.DateTimeUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Test1 {
    public static void main(String[] args) {
        //盐城失效时间
        String expireTime = "2020-12-03 10:10:10";
        /*
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = sdf.format(date);*/
        //当前系统时间
        String currenTime = DateTimeUtil.getSysTime();
        int count = expireTime.compareTo(currenTime);

        System.out.println(count);
    }
}

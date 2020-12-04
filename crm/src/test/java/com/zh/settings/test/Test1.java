package com.zh.settings.test;

import com.zh.crm.exception.LoginException;
import com.zh.crm.settings.domain.User;
import com.zh.crm.settings.service.UserService;
import com.zh.crm.settings.service.impl.UserServiceImpl;
import com.zh.crm.utils.DateTimeUtil;
import com.zh.crm.utils.MD5Util;
import com.zh.crm.utils.PrintJson;
import com.zh.crm.utils.ServiceFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Test1 {
    public static void main(String[] args) {

        String loginAct = "zs";
        String loginPwd = "123";
        loginPwd = MD5Util.getMD5(loginPwd);
        //接收ip地址
        String ip = "127.0.0.1";
        //业务层开发统一使用代理形态
        UserService us = (UserService) ServiceFactory.getService(new UserServiceImpl());
        User user = null;
        try {
            user = us.login(loginAct,loginPwd,ip);
        } catch (LoginException e) {
            e.printStackTrace();
        }
        System.out.println(user);
       /* try {




        }catch (Exception e){
            e.printStackTrace();
            String msg = e.getMessage();
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("success" ,false);
            map.put("msg",msg);
            PrintJson.printJsonObj(response,map);
        }*/
    }
}

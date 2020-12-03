package com.zh.crm.settings.web.controller;

import com.zh.crm.settings.domain.User;
import com.zh.crm.settings.service.UserService;
import com.zh.crm.settings.service.impl.UserServiceImpl;
import com.zh.crm.utils.MD5Util;
import com.zh.crm.utils.PrintJson;
import com.zh.crm.utils.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UserController  extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //super.service(request, response);
        System.out.println("进入到用户控制器");
        String path =request.getServletPath();

        if ("/settings/user/login.do".equals(path)){

            login(request,response);
        }else {

        }
    }

    private void login(HttpServletRequest request, HttpServletResponse response) {

        System.out.println("进入到验证");
        String loginAct = request.getParameter("loginAct");
        String loginPwd = request.getParameter("loginPwd");

        loginPwd = MD5Util.getMD5(loginPwd);
        //接收ip地址
        String ip = request.getRemoteAddr();
        System.out.println("-------------" + ip);
        //业务层开发统一使用代理形态
        UserService us = (UserService) ServiceFactory.getService(new UserServiceImpl());

        try {

            User user = us.login(loginAct,loginPwd,ip);
            request.getSession().setAttribute("user",user);
            PrintJson.printJsonFlag(response,true);

        }catch (Exception e){
            e.printStackTrace();
            String msg = e.getMessage();
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("success" ,false);
            map.put("msg",msg);
            PrintJson.printJsonObj(response,map);
        }
    }
}

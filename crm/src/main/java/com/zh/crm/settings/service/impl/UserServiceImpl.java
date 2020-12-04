package com.zh.crm.settings.service.impl;

import com.zh.crm.exception.LoginException;
import com.zh.crm.settings.dao.UserDao;
import com.zh.crm.settings.domain.User;
import com.zh.crm.settings.service.UserService;
import com.zh.crm.utils.DateTimeUtil;
import com.zh.crm.utils.SqlSessionUtil;

import java.util.HashMap;
import java.util.Map;

public class UserServiceImpl implements UserService {

    private UserDao userDao = SqlSessionUtil.getSqlSession().getMapper(UserDao.class);

    public User login(String loginAct, String loginPwd, String ip) throws LoginException {

        Map<String ,String> map = new HashMap<String, String>();
        map.put("loginAct" ,loginAct);
        map.put("loginPwd" ,loginPwd);
        map.put("ip" ,ip);
        User user = userDao.login(map);
        if (user == null){

            throw new LoginException("账号密码错误");
        }
        if (user.getExpireTime().compareTo(DateTimeUtil.getSysTime())<0  ){
            throw new LoginException("账号已失效");
        }
        if ("0".equals(user.getLockState()) ){
            throw new LoginException("账号已锁定");
        }
        if (user.getAllowIps() != null && user.getAllowIps()!=""){
            if (!user.getAllowIps().contains(ip) ){
                throw new LoginException("ip地址受限");
            }
        }

        return user;
    }
}

package com.zh.crm.settings.service.impl;

import com.zh.crm.settings.dao.UserDao;
import com.zh.crm.settings.service.UserService;
import com.zh.crm.utils.SqlSessionUtil;

public class UserServiceImpl implements UserService {

    private UserDao userDao = SqlSessionUtil.getSqlSession().getMapper(UserDao.class);

}

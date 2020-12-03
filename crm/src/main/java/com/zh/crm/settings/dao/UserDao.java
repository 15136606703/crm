package com.zh.crm.settings.dao;

import com.zh.crm.settings.domain.User;

import java.util.Map;

public interface UserDao {

    User login(Map<String, String> map);
}

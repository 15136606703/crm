package com.zh.crm.settings.service;

import com.zh.crm.exception.LoginException;
import com.zh.crm.settings.domain.User;

public interface UserService {
    User login(String loginAct, String loginPwd, String ip) throws LoginException;
}

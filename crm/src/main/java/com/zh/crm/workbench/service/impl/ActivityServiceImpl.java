package com.zh.crm.workbench.service.impl;

import com.zh.crm.utils.SqlSessionUtil;
import com.zh.crm.workbench.dao.ActivityDao;
import com.zh.crm.workbench.service.ActivityService;

public class ActivityServiceImpl implements ActivityService {

    //引dao层
    private ActivityDao activityDao = SqlSessionUtil.getSqlSession().getMapper(ActivityDao.class);

}

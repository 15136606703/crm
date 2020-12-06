package com.zh.crm.workbench.service.impl;

import com.zh.crm.utils.SqlSessionUtil;
import com.zh.crm.vo.PaginationVo;
import com.zh.crm.workbench.dao.ActivityDao;
import com.zh.crm.workbench.domain.Activity;
import com.zh.crm.workbench.service.ActivityService;

import java.lang.annotation.Target;
import java.util.List;
import java.util.Map;

public class ActivityServiceImpl implements ActivityService {

    //引dao层
    private ActivityDao activityDao = SqlSessionUtil.getSqlSession().getMapper(ActivityDao.class);

    public boolean save(Activity a) {
        boolean flag = true;
        int count = activityDao.save(a);
        if (count != 1) {
            flag = false;
        }
        return flag;
    }

    public PaginationVo<Activity> pageList(Map<String, Object> map) {

        //取得total
        int total = activityDao.getTotalByCondition(map);
        //取得dataList
        List<Activity> aList = activityDao.getActivityListByCondition(map);

        PaginationVo<Activity> vo = new PaginationVo();
        vo.setTotal(total);
        vo.setDataList(aList);
        return vo;
    }
}

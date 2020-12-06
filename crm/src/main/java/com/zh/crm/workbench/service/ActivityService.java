package com.zh.crm.workbench.service;

import com.zh.crm.vo.PaginationVo;
import com.zh.crm.workbench.domain.Activity;

import java.util.Map;

public interface ActivityService {
    boolean save(Activity a);

    PaginationVo<Activity> pageList(Map<String, Object> map);
}

package com.zh.crm.settings.service;

import com.zh.crm.settings.domain.DicValue;

import java.util.List;
import java.util.Map;

/**
 * @Classname DicService
 * @Description 张浩
 * @Date 2020-12-9 09:43
 * @Created by Administrator
 */
public interface DicService {
    Map<String, List<DicValue>> getAll();
}

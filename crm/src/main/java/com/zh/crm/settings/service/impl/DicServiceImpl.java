package com.zh.crm.settings.service.impl;

import com.zh.crm.settings.dao.DicTypeDao;
import com.zh.crm.settings.dao.DicValueDao;
import com.zh.crm.settings.domain.DicType;
import com.zh.crm.settings.domain.DicValue;
import com.zh.crm.settings.service.DicService;
import com.zh.crm.utils.SqlSessionUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Classname DicServiceImpl
 * @Description 张浩
 * @Date 2020-12-9 09:43
 * @Created by Administrator
 */
public class DicServiceImpl implements DicService {

    private DicTypeDao dicTypeDao = SqlSessionUtil.getSqlSession().getMapper(DicTypeDao.class);
    private DicValueDao dicValueDao = SqlSessionUtil.getSqlSession().getMapper(DicValueDao.class);

    public Map<String, List<DicValue>> getAll() {
        Map<String,List<DicValue>> map = new HashMap<String, List<DicValue>>();
        //将字典类型去除
       List<DicType> typeList = dicTypeDao.getTypeList();
       //将字典类型遍历
        for (DicType dt:typeList){

            String code = dt.getCode();
            List<DicValue> dvList = dicValueDao.getListByCode(code);
            map.put(code+"list",dvList);
        }
        return map;
    }
}

package com.zh.crm.settings.dao;

import com.zh.crm.settings.domain.DicValue;

import java.util.List;

/**
 * Author 北京动力节点
 */
public interface DicValueDao {
    List<DicValue> getListByCode(String code);
}

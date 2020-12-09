package com.zh.crm.web.listener;

import com.zh.crm.settings.domain.DicValue;
import com.zh.crm.settings.service.DicService;
import com.zh.crm.settings.service.impl.DicServiceImpl;
import com.zh.crm.utils.ServiceFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Classname SysInitListener
 * @Description 张浩
 * @Date 2020-12-9 13:39
 * @Created by Administrator
 */
public class SysInitListener implements ServletContextListener {

    /*
    * sce : 该参数能够取得监听的对象
    *           箭筒的是什么对象，就可以通过参数获取得什么对象
    *
    * */
    public void contextInitialized(ServletContextEvent sce) {

        ServletContext application = sce.getServletContext();
        //取得数据字典
        DicService ds = (DicService) ServiceFactory.getService(new DicServiceImpl());

        Map<String , List<DicValue>> map = ds.getAll();
        Set<String> set = map.keySet();
        for (String key:set){

            application.setAttribute(key,map.get(set));
        }

    }
}

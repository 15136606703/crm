package com.zh.crm.workbench.web.controller;

import com.zh.crm.settings.domain.User;
import com.zh.crm.settings.service.UserService;
import com.zh.crm.settings.service.impl.UserServiceImpl;
import com.zh.crm.utils.DateTimeUtil;
import com.zh.crm.utils.PrintJson;
import com.zh.crm.utils.ServiceFactory;
import com.zh.crm.utils.UUIDUtil;
import com.zh.crm.vo.PaginationVo;
import com.zh.crm.workbench.domain.Activity;
import com.zh.crm.workbench.service.ActivityService;
import com.zh.crm.workbench.service.impl.ActivityServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActivityController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //super.service(request, response);
        System.out.println("进入到市场活动控制器");
        String path =request.getServletPath();

        if ("/workbench/activity/getUserList.do".equals(path)){
            getUserList(request,response);
            
        }else if("/workbench/activity/save.do".equals(path)){
            save(request,response);
        }else if("/workbench/activity/pageList.do".equals(path)){
            pageList(request,response);
        }else if("/workbench/activity/delete.do".equals(path)){
            delete(request,response);
        }
    }

    /**
     * 删除
     *@Description 张浩
     *@param
     * @param request
     * @param response
     *@return void
     *@date 2020-12-7 15:12
     *@auther Administrator
     */
    private void delete(HttpServletRequest request, HttpServletResponse response) {
        String ids[] = request.getParameterValues("id");
        ActivityService as = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());
        boolean flag =  as.delete(ids);
        PrintJson.printJsonFlag(response,flag);
    }

    /**
     * 分页查询
     *@Description 张浩
     * @param request
     * @param response
     *@return void
     *@date 2020-12-7 09:25
     *@auther Administrator
     */
    private void pageList(HttpServletRequest request, HttpServletResponse response) {

        int  pageNum = Integer.valueOf(request.getParameter("pageNum"));
        int pageSize = Integer.valueOf(request.getParameter("pageSize"));
        String owner = request.getParameter("owner");
        String name = request.getParameter("name");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("startDate");
        int skipCount = (pageNum-1)*pageSize;


        Map<String,Object> map = new HashMap<String, Object>();
        map.put("skipCount",skipCount);
        map.put("pageSize",pageSize);
        map.put("owner",owner);
        map.put("name",name);
        map.put("startDate",startDate);
        map.put("endDate",endDate);


        ActivityService as = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());
        PaginationVo<Activity> vo =  as.pageList(map);

        PrintJson.printJsonObj(response,vo);


    }

    /*
    * 添加市场活动
    * */
    private void save(HttpServletRequest request, HttpServletResponse response) {
        String id = UUIDUtil.getUUID();
        String owner = request.getParameter("owner");
        String name = request.getParameter("name");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String cost = request.getParameter("cost");
        String description = request.getParameter("description");
        String createTime = DateTimeUtil.getSysTime();
        //创建人
        String createBy = ((User)request.getSession().getAttribute("user")).getName();

        Activity a = new Activity();
        a.setId(id);
        a.setOwner(owner);
        a.setName(name);
        a.setStartDate(startDate);
        a.setEndDate(endDate);
        a.setCost(cost);
        a.setDescription(description);
        a.setCreateTime(createTime);
        a.setCreateBy(createBy);

        ActivityService as = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());

        boolean flag = as.save(a);

        PrintJson.printJsonFlag(response,flag);
    }

    private void getUserList(HttpServletRequest request, HttpServletResponse response) {

        System.out.println("取得用户信息表");
        //业务层开发统一使用代理形态
       // ActivityService activityService = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());

        UserService us = (UserService) ServiceFactory.getService(new UserServiceImpl());
        List<User> uList = us.getUserList();
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("success" ,true);
        map.put("uList",uList);
        PrintJson.printJsonObj(response,map);

    }

}

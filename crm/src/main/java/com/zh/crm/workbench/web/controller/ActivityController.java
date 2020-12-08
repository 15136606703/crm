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
import com.zh.crm.workbench.domain.ActivityRemark;
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
        }else if("/workbench/activity/getUserListAndActivity.do".equals(path)){
            getUserListAndActivity(request,response);
        }else if("/workbench/activity/update.do".equals(path)){
            update(request,response);
        }else if("/workbench/activity/detail.do".equals(path)){
            detail(request,response);
        }
        else if("/workbench/activity/getRemarkListByAid.do".equals(path)){
            getRemarkListByAid(request,response);
        }
        else if("/workbench/activity/deleteRemark.do".equals(path)){
            deleteRemark(request,response);
        }
        else if("/workbench/activity/saveRemark.do".equals(path)){
            saveRemark(request,response);
        }else if("/workbench/activity/updateRemark.do".equals(path)){
            updateRemark(request,response);
        }

    }

    private void updateRemark(HttpServletRequest request, HttpServletResponse response) {
        String noteContent = request.getParameter("noteContent");
        String id = request.getParameter("id");
        String editTime = DateTimeUtil.getSysTime();
        String editBy = ((User)request.getSession().getAttribute("user")).getName();
        String editFlag = "1";

        ActivityRemark ar = new ActivityRemark();
        ar.setId(id);
        ar.setNoteContent(noteContent);
        ar.setCreateBy(editTime);
        ar.setCreateTime(editBy);
        ar.setEditFlag(editFlag);

        ActivityService as = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());

        boolean flag =  as.updateRemark(ar);
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("success",flag);
        map.put("ar",ar);
        PrintJson.printJsonObj(response,map);

    }

    private void saveRemark(HttpServletRequest request, HttpServletResponse response) {
        String noteContent = request.getParameter("noteContent");
        String activityId = request.getParameter("activityId");
        String id = UUIDUtil.getUUID();

        String createTime = DateTimeUtil.getSysTime();
        //创建人
        String createBy = ((User)request.getSession().getAttribute("user")).getName();
        String editFlag = "0";
        ActivityRemark ar = new ActivityRemark();
        ar.setId(id);
        ar.setActivityId(activityId);
        ar.setNoteContent(noteContent);
        ar.setCreateBy(createBy);
        ar.setCreateTime(createTime);
        ar.setEditFlag(editFlag);

        ActivityService as = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());

        boolean flag =  as.saveRemark(ar);
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("success",flag);
        map.put("ar",ar);
        PrintJson.printJsonObj(response,map);
    }

    private void deleteRemark(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        ActivityService as = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());
        boolean flag = as.deleteRemark(id);
        PrintJson.printJsonFlag(response,flag);
    }

    private void getRemarkListByAid(HttpServletRequest request, HttpServletResponse response) {
        String activityId = request.getParameter("activityId");
        ActivityService as = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());
        List<ActivityRemark> rList =  as.getRemarkListByAid(activityId);
        PrintJson.printJsonObj(response,rList);
    }

    private void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id = request.getParameter("id");
        ActivityService as = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());
        Activity a =  as.detail(id);
        request.setAttribute("a",a);
        //转发
        request.getRequestDispatcher("/workbench/activity/detail.jsp").forward(request,response);
    }

    private void update(HttpServletRequest request, HttpServletResponse response) {
        ActivityService as = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());
        String id = request.getParameter("id");
        String owner = request.getParameter("owner");
        String name = request.getParameter("name");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String cost = request.getParameter("cost");
        String description = request.getParameter("description");

        String editTime = DateTimeUtil.getSysTime();
        //创建人
        String editBy = ((User)request.getSession().getAttribute("user")).getName();

        Activity a = new Activity();
        a.setId(id);
        a.setOwner(owner);
        a.setName(name);
        a.setStartDate(startDate);
        a.setEndDate(endDate);
        a.setCost(cost);
        a.setDescription(description);
        a.setEditTime(editTime);
        a.setEditBy(editBy);


        boolean flag = as.update(a);

        PrintJson.printJsonFlag(response,flag);
    }

    private void getUserListAndActivity(HttpServletRequest request, HttpServletResponse response) {

        ActivityService as = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());

        String id = request.getParameter("id");
        Map<String,Object> map =  as.getUserListAndActivity(id);
        PrintJson.printJsonObj(response,map);
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
        ActivityService as = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());
        String ids[] = request.getParameterValues("id");
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
        ActivityService as = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());

        int  pageNum = Integer.valueOf(request.getParameter("pageNum"));
        int pageSize = Integer.valueOf(request.getParameter("pageSize"));
        String owner = request.getParameter("owner");
        String name = request.getParameter("name");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        int skipCount = (pageNum-1)*pageSize;


        Map<String,Object> map = new HashMap<String, Object>();
        map.put("skipCount",skipCount);
        map.put("pageSize",pageSize);
        map.put("owner",owner);
        map.put("name",name);
        map.put("startDate",startDate);
        map.put("endDate",endDate);


        PaginationVo<Activity> vo =  as.pageList(map);

        PrintJson.printJsonObj(response,vo);


    }

    /*
    * 添加市场活动
    * */
    private void save(HttpServletRequest request, HttpServletResponse response) {
        ActivityService as = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());
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


        boolean flag = as.save(a);

        PrintJson.printJsonFlag(response,flag);
    }

    private void getUserList(HttpServletRequest request, HttpServletResponse response) {

        UserService us = (UserService) ServiceFactory.getService(new UserServiceImpl());
        System.out.println("取得用户信息表");
        //业务层开发统一使用代理形态
       // ActivityService activityService = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());


        List<User> uList = us.getUserList();
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("success" ,true);
        map.put("uList",uList);
        PrintJson.printJsonObj(response,map);

    }

}

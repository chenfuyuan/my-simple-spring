package com.learn.project.springframework.service;

import com.learn.project.springframework.beans.factory.DisposableBean;
import com.learn.project.springframework.beans.factory.InitializingBean;
import com.learn.project.springframework.dao.UserDao;

/**
 * UserService
 *
 * @author chenfuyuan
 * @date 2023/1/12 16:59
 */
public class UserService implements InitializingBean, DisposableBean {

    private String uId;

    private UserDao userDao;

    private String company;

    private String location;


    public void queryUserInfo() {
        System.out.println("查询用户信息!name=" + userDao.queryUserName(uId) + "company:" + company + " location:" + location);
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("执行: userService.destroy");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("执行：userSerivce.afterPropertiesSet");
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}

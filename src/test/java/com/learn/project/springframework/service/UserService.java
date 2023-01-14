package com.learn.project.springframework.service;

import com.learn.project.springframework.dao.UserDao;

/**
 * UserService
 *
 * @author chenfuyuan
 * @date 2023/1/12 16:59
 */
public class UserService {

    private String uId;

    private UserDao userDao;



    public void queryUserInfo() {
        System.out.println("查询用户信息!name=" + userDao.queryUserName(uId));
    }


}

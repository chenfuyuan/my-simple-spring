package com.learn.project.springframework.service;

/**
 * UserService
 *
 * @author chenfuyuan
 * @date 2023/1/12 16:59
 */
public class UserService {

    private String userName;

    public UserService(String userName) {
        this.userName = userName;
    }

    public UserService() {
        this.userName = "default";
    }


    public void queryUserInfo() {
        System.out.println("查询用户信息!name="+userName);
    }


}

package com.learn.project.springframework.service;

/**
 * UserService
 *
 * @author chenfuyuan
 * @date 2023/1/12 16:59
 */
public class UserService {

    private String userName;

    private static final String DEFAULT_NAME = "default";

    private int age;

    public UserService(String userName) {
        this.userName = userName;
        this.age = 0;
    }

    public UserService(int age) {
        this.userName = DEFAULT_NAME;
        this.age=age;
    }

    public UserService() {
        this.userName = DEFAULT_NAME;
    }


    public void queryUserInfo() {
        System.out.println("查询用户信息!name="+userName+"  age:" + age);
    }


}

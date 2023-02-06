package com.learn.project.springframework.dao;

import java.util.HashMap;
import java.util.Map;

/**
 * UserDao
 *
 * @author chenfuyuan
 * @date 2023/1/14 14:15
 */
public class UserDao {

    private static Map<String, String> userMap = new HashMap<>();

    public void initDataMethod() {
        System.out.println("执行:userDao.init-method");
        userMap.put("10001", "小陈");
        userMap.put("10002", "小福");
        userMap.put("10003", "小源");
    }

    public String queryUserName(String uId) {
        return userMap.get(uId);
    }

    public void destroyDataMethod() {
        System.out.println("执行: destroy-method");
        userMap.clear();
    }
}

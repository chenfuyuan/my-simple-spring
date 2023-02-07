package com.learn.project.springframework.dao;

/**
 * IUserDaoImpl
 *
 * @author chenfuyuan
 * @date 2023/2/7 14:13
 */
public class IUserDaoImpl implements IUserDao {

    private String uId;

    private String company;

    private String location;

    private IUserDao userDao;


    @Override
    public String queryUserName(String uId) {
        return userDao.queryUserName(uId) + ", " + company + ", " + location;
    }
}

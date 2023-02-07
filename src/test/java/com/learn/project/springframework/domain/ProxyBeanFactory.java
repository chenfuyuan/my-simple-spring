package com.learn.project.springframework.domain;

import com.learn.project.springframework.beans.factory.FactoryBean;
import com.learn.project.springframework.dao.IUserDao;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * ProxyBeanFactory
 *
 * @author chenfuyuan
 * @date 2023/2/7 15:21
 */
public class ProxyBeanFactory implements FactoryBean<IUserDao> {

    @Override
    public IUserDao getObject() {
        InvocationHandler handler = (proxy,method,args) -> {
            Map<String, String> dataMap = new HashMap<>();
            dataMap.put("10001", "小陈");
            dataMap.put("10002", "小福");
            dataMap.put("10003", "小源");
            return "你被代理了 " + method.getName() + ":" + dataMap.get(args[0].toString());
        };
        return (IUserDao) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{IUserDao.class}, handler);
    }

    @Override
    public Class<IUserDao> getObjectType() {
        return IUserDao.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}

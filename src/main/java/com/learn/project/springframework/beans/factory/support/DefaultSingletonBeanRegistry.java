package com.learn.project.springframework.beans.factory.support;

import com.learn.project.springframework.beans.factory.config.SingletonBeanRegistry;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * DefaultSingletonBeanRegistry
 *
 * @author chenfuyuan
 * @date 2023/1/7 16:39
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    /**
     * 存放单例对象容器
     */
    private final Map<String, Object> singletonObjects = new ConcurrentHashMap<>(256);

    @Override
    public Object getSingleton(String beanName) {
        return singletonObjects.get(beanName);
    }


    protected void addSingleton(String beanName, Object singletonObject) {
        singletonObjects.put(beanName, singletonObject);
    }
}

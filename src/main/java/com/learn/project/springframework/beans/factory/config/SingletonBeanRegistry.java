package com.learn.project.springframework.beans.factory.config;

import com.learn.project.springframework.beans.BeansException;

/**
 * SingletonBeanRegistry
 *
 * @author chenfuyuan
 * @date 2023/1/7 16:37
 */
public interface SingletonBeanRegistry {

    /**
     * 获取单例bean
     * @param beanName
     * @return
     */
    Object getSingleton(String beanName);

    /**
     * 注册单例
     * @param beanName
     * @param singletonObject
     */
    void registerSingleton(String beanName, Object singletonObject);
}

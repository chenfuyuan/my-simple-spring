package com.learn.project.springframework.beans.factory.config;

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
}

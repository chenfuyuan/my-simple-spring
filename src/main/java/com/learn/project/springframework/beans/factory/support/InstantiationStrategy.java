package com.learn.project.springframework.beans.factory.support;

import com.learn.project.springframework.beans.BeansException;
import com.learn.project.springframework.beans.factory.BeanFactory;
import com.learn.project.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * InstantiationStrategy
 *
 * @author chenfuyuan
 * @date 2023/1/13 13:14
 */
public interface InstantiationStrategy {

    /**
     * 实例化
     * @param beanDefinition
     * @param beanName
     * @param constructor
     * @param args
     * @return
     * @throws BeansException
     */
    Object instantiate(RootBeanDefinition beanDefinition, String beanName, Constructor constructor, Object[] args) throws BeansException;
}

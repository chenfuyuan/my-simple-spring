package com.learn.project.springframework.beans.factory.config;

import com.learn.project.springframework.beans.BeansException;

/**
 * BeanPostProcessor
 * 提供修改Bean对象的扩展点
 * @author chenfuyuan
 * @date 2023/1/29 22:22
 */
public interface BeanPostProcessor {

    /**
     * 在Bean对象执行初始化方法之前，执行此方法
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException;

    /**
     * 在Bean对象执行初始化方法之后，执行此方法
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException;
}

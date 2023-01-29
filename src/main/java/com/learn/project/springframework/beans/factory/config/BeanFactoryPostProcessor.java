package com.learn.project.springframework.beans.factory.config;

import com.learn.project.springframework.beans.BeansException;

/**
 * BeanFactoryPostProcessor
 *
 * @author chenfuyuan
 * @date 2023/1/29 22:21
 */
public interface BeanFactoryPostProcessor {

    /**
     * 在所有的BeanDefinition加载完成后，实例化Bean对象之前，提供修改BeanDefinition属性的机制
     * @param beanFactory
     * @throws BeansException
     */
    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException;
}

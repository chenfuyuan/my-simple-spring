package com.learn.project.springframework.beans.factory.support;

import com.learn.project.springframework.beans.factory.config.BeanDefinition;

/**
 * BeanDefinitionRegistry
 *
 * @author chenfuyuan
 * @date 2023/1/7 17:13
 */
public interface BeanDefinitionRegistry {

    /**
     * 根据beanName获取对应的BeanDefinition
     * @param beanName
     * @return
     */
    BeanDefinition getBeanDefinition(String beanName);

    /**
     * 注册BeanDefinition
     * @param beanName
     * @param beanDefinition
     */
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);
}

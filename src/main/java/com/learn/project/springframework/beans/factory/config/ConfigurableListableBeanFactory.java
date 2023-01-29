package com.learn.project.springframework.beans.factory.config;

import com.learn.project.springframework.beans.BeansException;
import com.learn.project.springframework.beans.factory.AutowireCapableBeanFactory;
import com.learn.project.springframework.beans.factory.ConfigurableBeanFactory;
import com.learn.project.springframework.beans.factory.ListableBeanFactory;

/**
 * ConfigurableListableBeanFactory
 *
 * @author chenfuyuan
 * @date 2023/1/29 22:52
 */
public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {

    /**
     * 提前实例化单例Bean对象
     * @throws BeansException
     */
    void preInstantiateSingletons() throws BeansException;


    BeanDefinition getBeanDefinition(String beanName) throws BeansException;
}

package com.learn.project.springframework.beans.factory.config;

import com.learn.project.springframework.beans.MutablePropertyValues;

/**
 * BeanDefinition
 * 对Bean的定义(即对容器中Bean的抽象)
 * @author chenfuyuan
 * @date 2022/12/31 16:01
 */
public interface BeanDefinition {

    String SCOPE_SINGLETON = ConfigurableBeanFactory.SCOPE_SINGLETON;
    String SCOPE_PROTOTYPE = ConfigurableBeanFactory.SCOPE_PROTOTYPE;


    /**
     * 获取BeanClass名称
     * @return
     */
    String getBeanClassName();

    MutablePropertyValues getPropertyValues();

    default boolean hasPropertyValues() {
        return !getPropertyValues().isEmpty();
    }

    void setScope(String beanScope);
}

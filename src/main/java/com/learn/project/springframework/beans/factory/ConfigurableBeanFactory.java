package com.learn.project.springframework.beans.factory;

import com.learn.project.springframework.beans.factory.config.BeanPostProcessor;
import com.learn.project.springframework.beans.factory.config.SingletonBeanRegistry;

/**
 * ConfigurableBeanFactory
 * 可获取BeanPostProcessor和BeanClassLoader等的一个配置化接口
 * @author chenfuyuan
 * @date 2023/1/19 10:05
 */
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry {

    String SCOPE_SINGLETON = "singleton";

    String SCOPE_PROTOTYPE = "prototype";

    /**
     * 添加BeanPostProcessor对象
     * @param beanPostProcessor
     */
    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

    /**
     * 销毁单例对象
     */
    void destroySingletons();
}

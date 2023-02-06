package com.learn.project.springframework.context.support;

import com.learn.project.springframework.beans.BeansException;
import com.learn.project.springframework.beans.factory.config.BeanPostProcessor;
import com.learn.project.springframework.context.ApplicationContext;
import com.learn.project.springframework.context.ApplicationContextAware;

/**
 * ApplicationContextAwareProcessor
 * 包装ApplicationContext
 * @author chenfuyuan
 * @date 2023/2/6 13:48
 */
public class ApplicationContextAwareProcessor implements BeanPostProcessor {

    private ApplicationContext applicationContext;

    public ApplicationContextAwareProcessor(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        // 因为在创建Bean时，无法直接获取到ApplicationContext，将ApplicationContext写入包装类中，进行获取
        if (bean instanceof ApplicationContextAware) {
            ((ApplicationContextAware) bean).setApplicationContext(applicationContext);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}

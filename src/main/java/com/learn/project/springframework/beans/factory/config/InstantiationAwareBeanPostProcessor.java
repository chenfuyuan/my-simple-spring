package com.learn.project.springframework.beans.factory.config;

import com.learn.project.springframework.beans.BeansException;
import com.learn.project.springframework.beans.PropertyValues;
import com.learn.project.springframework.beans.factory.Aware;

/**
 * InstantiationAwareBeanPostProcessor
 *
 * @author chenfuyuan
 * @date 2023/2/16 14:56
 */
public interface InstantiationAwareBeanPostProcessor extends BeanPostProcessor {

    Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException;

    PropertyValues postProcessPropertyValues(PropertyValues pvs, Object bean, String beanName) throws BeansException;
}

package com.learn.project.springframework.beans.factory.support;

import com.learn.project.springframework.beans.MutablePropertyValues;
import com.learn.project.springframework.beans.factory.config.BeanDefinition;

/**
 * RootBeanDefinition
 *
 * @author chenfuyuan
 * @date 2022/12/31 16:07
 */
public class RootBeanDefinition extends AbstractBeanDefinition {


    public RootBeanDefinition(Class<?> beanClass) {
        setBeanClass(beanClass);
    }

    public RootBeanDefinition(Class<?> beanClass, MutablePropertyValues propertyValues) {
        super(propertyValues);
        setBeanClass(beanClass);
    }

    public RootBeanDefinition(BeanDefinition original) {
        super(original);
    }
}

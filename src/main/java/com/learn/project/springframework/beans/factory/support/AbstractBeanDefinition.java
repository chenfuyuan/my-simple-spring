package com.learn.project.springframework.beans.factory.support;

import com.learn.project.springframework.beans.BeansException;
import com.learn.project.springframework.beans.factory.config.BeanDefinition;

/**
 * AbstractBeanDefinition
 *
 * @author chenfuyuan
 * @date 2022/12/31 16:08
 */
public abstract class AbstractBeanDefinition implements BeanDefinition {

    /**
     * 对应的beanClass
     */
    private Class beanClass;

    protected AbstractBeanDefinition() {

    }

    protected AbstractBeanDefinition(BeanDefinition original) {
        if (original instanceof AbstractBeanDefinition originalAbd) {
            setBeanClass(originalAbd.getBeanClass());
        }
    }

    public void setBeanClass(Class<?> beanClass) {
        this.beanClass = beanClass;
    }

    public Class<?> getBeanClass() throws IllegalStateException {
        if (beanClass == null) {
            throw new IllegalStateException("No bean class specified on bean definition");
        }
        return this.beanClass;
    }

    @Override
    public String getBeanClassName() {
        return this.beanClass.getName();
    }
}

package com.learn.project.springframework.beans.factory.config;

/**
 * RuntimeBeanReference
 *
 * @author chenfuyuan
 * @date 2023/1/14 14:01
 */
public class RuntimeBeanReference implements BeanReference {

    private final String beanName;

    public RuntimeBeanReference(String beanName) {
        this.beanName = beanName;
    }

    @Override
    public String getBeanName() {
        return this.beanName;
    }
}

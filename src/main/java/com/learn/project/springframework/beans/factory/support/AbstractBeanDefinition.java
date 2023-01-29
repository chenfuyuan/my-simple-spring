package com.learn.project.springframework.beans.factory.support;

import com.learn.project.springframework.beans.MutablePropertyValues;
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
    private Class<?> beanClass;

    /**
     * 属性值
     */
    private MutablePropertyValues propertyValues;


    protected AbstractBeanDefinition() {

    }

    protected AbstractBeanDefinition(MutablePropertyValues propertyValues) {
        this.propertyValues = propertyValues;
    }

    protected AbstractBeanDefinition(BeanDefinition original) {
        if (original instanceof AbstractBeanDefinition originalAbd) {
            setBeanClass(originalAbd.getBeanClass());
            if (originalAbd.hasPropertyValues()) {
                setPropertyValues(new MutablePropertyValues(originalAbd.getPropertyValues()));
            }
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


    @Override
    public MutablePropertyValues getPropertyValues() {
        return propertyValues;
    }

    public void setPropertyValues(MutablePropertyValues propertyValues) {
        this.propertyValues = propertyValues;
    }

    @Override
    public boolean hasPropertyValues() {
        return this.propertyValues != null && !this.propertyValues.isEmpty();
    }
}

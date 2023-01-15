package com.learn.project.springframework.beans.factory.support;

import com.learn.project.springframework.beans.PropertyValue;
import com.learn.project.springframework.beans.factory.config.BeanDefinition;
import com.learn.project.springframework.beans.factory.config.RuntimeBeanReference;

/**
 * BeanDefinitionValueResolver
 *
 * @author chenfuyuan
 * @date 2023/1/14 16:05
 */
public class BeanDefinitionValueResolver {

    private AbstractAutowireCapableBeanFactory beanFactory;

    private String beanName;

    private BeanDefinition beanDefinition;

    public BeanDefinitionValueResolver(AbstractAutowireCapableBeanFactory beanFactory, String beanName, BeanDefinition beanDefinition) {
        this.beanFactory = beanFactory;
        this.beanName = beanName;
        this.beanDefinition =beanDefinition;
    }

    /**
     * 解析PropertyValue
     * @param argName
     * @param value
     * @return
     */
    public Object resolveValueIfNecessary(Object argName, Object value) {
        if (value instanceof RuntimeBeanReference ref) {
            return resolveReference(argName, ref);
        }
        return value;
    }

    private Object resolveReference(Object argName, RuntimeBeanReference ref) {
        //解析BeanReference，在beanFactory中查找对应的Bean
        return this.beanFactory.getBean(ref.getBeanName());
    }
}

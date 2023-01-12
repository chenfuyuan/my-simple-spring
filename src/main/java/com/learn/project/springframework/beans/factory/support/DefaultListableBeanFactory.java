package com.learn.project.springframework.beans.factory.support;

import com.learn.project.springframework.beans.BeansException;
import com.learn.project.springframework.beans.factory.config.BeanDefinition;

import java.util.HashMap;
import java.util.Map;

/**
 * DefaultListableBeanFactory
 *
 * @author chenfuyuan
 * @date 2023/1/7 17:15
 */
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry {

    private Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();
    @Override
    public BeanDefinition getBeanDefinition(String beanName) {
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        if (beanDefinition == null) {
            throw new BeansException("No bean named '" + beanName + "' is defined");
        }
        return beanDefinition;
    }

    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanName, beanDefinition);
    }

}

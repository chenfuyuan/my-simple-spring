package com.learn.project.springframework.beans.factory.support;

import com.learn.project.springframework.beans.BeansException;
import com.learn.project.springframework.beans.factory.BeanFactory;
import com.learn.project.springframework.beans.factory.config.BeanDefinition;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * AbstractBeanFactory
 *
 * @author chenfuyuan
 * @date 2023/1/7 16:41
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {

    private Map<String, RootBeanDefinition> mergedBeanDefinitions = new ConcurrentHashMap<>();

    /**
     * 使用模板方法 统一收口通用核心方法的调用逻辑和标准定义
     * @param beanName
     * @return
     */
    @Override
    public Object getBean(String beanName) {
        return doGetBean(beanName);
    }

    protected Object doGetBean(String beanName) {
        Object bean = getSingleton(beanName);
        if (bean != null) {
            return bean;
        }
        bean = createBean(beanName, getMergedBeanDefinition(beanName));
        return bean;
    }

    protected RootBeanDefinition getMergedBeanDefinition(String beanName) {
        RootBeanDefinition mbd = this.mergedBeanDefinitions.get(beanName);
        if (mbd != null) {
            return mbd;
        }
        return getMergedBeanDefinition(beanName, getBeanDefinition(beanName));
    }

    private RootBeanDefinition getMergedBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        RootBeanDefinition result = new RootBeanDefinition(beanDefinition);
        this.mergedBeanDefinitions.put(beanName, result);
        return result;
    }

    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    /**
     * 创建Bean
     * @param beanName
     * @param mbd
     * @return
     */
    protected abstract Object createBean(String beanName, RootBeanDefinition mbd);

}

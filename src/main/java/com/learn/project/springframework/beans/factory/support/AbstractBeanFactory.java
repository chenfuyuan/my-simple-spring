package com.learn.project.springframework.beans.factory.support;

import com.learn.project.springframework.beans.BeansException;
import com.learn.project.springframework.beans.factory.BeanFactory;
import com.learn.project.springframework.beans.factory.ConfigurableBeanFactory;
import com.learn.project.springframework.beans.factory.config.BeanDefinition;
import com.learn.project.springframework.beans.factory.config.BeanPostProcessor;
import com.learn.project.springframework.util.ClassUtils;
import com.sun.jdi.connect.Connector;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * AbstractBeanFactory
 *
 * @author chenfuyuan
 * @date 2023/1/7 16:41
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements ConfigurableBeanFactory {

    private Map<String, RootBeanDefinition> mergedBeanDefinitions = new ConcurrentHashMap<>();

    private final List<BeanPostProcessor> beanPostProcessors = new ArrayList<BeanPostProcessor>();

    private final ClassLoader classLoader = ClassUtils.getDefaultClassLoader();

    public ClassLoader getClassLoader() {
        return classLoader;
    }

    /**
     * 使用模板方法 统一收口通用核心方法的调用逻辑和标准定义
     * @param beanName
     * @return
     */
    @Override
    public Object getBean(String beanName) {
        return doGetBean(beanName, null);
    }

    @Override
    public <T> T getBean(String beanName, Class<T> beanType) {
        return (T) getBean(beanName);
    }

    @Override
    public Object getBean(String beanName, Object... args) {
        return doGetBean(beanName,args);
    }

    protected Object doGetBean(String beanName,Object... args) {
        Object bean = getSingleton(beanName);
        if (bean != null) {
            return bean;
        }
        bean = createBean(beanName, getMergedBeanDefinition(beanName),args);
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
    protected abstract Object createBean(String beanName, RootBeanDefinition mbd,Object[] args);

    @Override
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
        this.beanPostProcessors.remove(beanPostProcessor);
        this.beanPostProcessors.add(beanPostProcessor);
    }

    public List<BeanPostProcessor> getBeanPostProcessors() {
        return this.beanPostProcessors;
    }
}

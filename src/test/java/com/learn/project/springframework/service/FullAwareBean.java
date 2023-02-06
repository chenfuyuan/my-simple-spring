package com.learn.project.springframework.service;

import com.learn.project.springframework.beans.BeansException;
import com.learn.project.springframework.beans.factory.BeanClassLoaderAware;
import com.learn.project.springframework.beans.factory.BeanFactory;
import com.learn.project.springframework.beans.factory.BeanFactoryAware;
import com.learn.project.springframework.beans.factory.BeanNameAware;
import com.learn.project.springframework.context.ApplicationContext;
import com.learn.project.springframework.context.ApplicationContextAware;

import java.util.StringJoiner;

/**
 * FullAwareBean
 *
 * @author chenfuyuan
 * @date 2023/2/6 14:10
 */
public class FullAwareBean implements BeanNameAware, BeanFactoryAware, BeanClassLoaderAware, ApplicationContextAware {

    private ClassLoader beanClassLoader;

    private BeanFactory beanFactory;

    private String beanName;

    private ApplicationContext applicationContext;


    public ClassLoader getBeanClassLoader() {
        return beanClassLoader;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }

    public String getBeanName() {
        return beanName;
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    @Override
    public void setBeanClassLoader(ClassLoader beanClassLoader) {
        this.beanClassLoader = beanClassLoader;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    @Override
    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", FullAwareBean.class.getSimpleName() + "[", "]")
                .add("beanClassLoader=" + beanClassLoader)
                .add("beanFactory=" + beanFactory)
                .add("beanName='" + beanName + "'")
                .add("applicationContext=" + applicationContext)
                .toString();
    }
}

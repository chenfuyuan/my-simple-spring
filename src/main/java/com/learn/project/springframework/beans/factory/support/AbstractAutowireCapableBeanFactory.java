package com.learn.project.springframework.beans.factory.support;

import com.learn.project.springframework.beans.BeansException;

import java.lang.reflect.Constructor;

/**
 * AbstractAutowireCapableBeanFactory
 *
 * @author chenfuyuan
 * @date 2023/1/7 16:41
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {

    private InstantiationStrategy instantiationStrategy = new CglibInstantiationStrategy();

    @Override
    protected Object createBean(String beanName, RootBeanDefinition mbd,Object[] args) {
        return doCreateBean(beanName, mbd, args);
    }

    protected Object doCreateBean(String beanName, RootBeanDefinition mbd, Object[] args) {
        Object bean = null;
        try {
            bean = createBeanInstance(mbd, beanName, args);
        } catch (Exception e) {
            throw new BeansException("Instantiation of bean failed", e);
        }
        //缓存实例化的单例bean
        addSingleton(beanName,bean);
        return bean;
    }

    /**
     * 创建Bean对象
     * @param beanDefinition
     * @param beanName
     * @param args
     * @return
     */
    protected Object createBeanInstance(RootBeanDefinition beanDefinition, String beanName, Object[] args) {
        ConstructorResolver constructorResolver = new ConstructorResolver(this);
        //解析出对应的构造器
        Constructor constructorToUse = constructorResolver.autowireConstructor(beanName, beanDefinition, null, args);
        return instantiationStrategy.instantiate(beanDefinition, beanName, constructorToUse, args);
    }
}

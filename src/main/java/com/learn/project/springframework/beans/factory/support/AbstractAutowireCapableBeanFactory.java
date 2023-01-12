package com.learn.project.springframework.beans.factory.support;

import java.lang.reflect.InvocationTargetException;

/**
 * AbstractAutowireCapableBeanFactory
 *
 * @author chenfuyuan
 * @date 2023/1/7 16:41
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {

    @Override
    protected Object createBean(String beanName, RootBeanDefinition mbd) {
        return doCreateBean(beanName, mbd, null);
    }

    protected Object doCreateBean(String beanName, RootBeanDefinition mdb, Object[] args) {
        Object bean = null;
        try {
            Class<?> beanClass = mdb.getBeanClass();
            bean = beanClass.getDeclaredConstructor(null).newInstance(args);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        //缓存实例化的单例bean
        addSingleton(beanName,bean);
        return bean;
    }
}

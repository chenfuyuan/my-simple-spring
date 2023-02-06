package com.learn.project.springframework.beans.factory.support;

import com.learn.project.springframework.beans.BeansException;
import com.learn.project.springframework.beans.factory.DisposableBean;
import com.learn.project.springframework.beans.factory.config.SingletonBeanRegistry;

import java.beans.Beans;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * DefaultSingletonBeanRegistry
 *
 * @author chenfuyuan
 * @date 2023/1/7 16:39
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    /**
     * 存放单例对象容器
     */
    private final Map<String, Object> singletonObjects = new ConcurrentHashMap<>(256);

    private final Map<String, DisposableBean> disposableBeans = new HashMap<>();


    @Override
    public Object getSingleton(String beanName) {
        return singletonObjects.get(beanName);
    }


    protected void addSingleton(String beanName, Object singletonObject) {
        singletonObjects.put(beanName, singletonObject);
    }

    public void registerDisposableBean(String beanName, DisposableBean bean) {
        disposableBeans.put(beanName, bean);
    }

    public void destroySingletons() {
        Set<String> keySet = this.disposableBeans.keySet();
        Object[] disposableBeanNames = keySet.toArray();

        for (int i = disposableBeanNames.length - 1; i >= 0; i--) {
            Object beanName = disposableBeanNames[i];
            DisposableBean disposableBean = disposableBeans.remove(beanName);
            try {
                disposableBean.destroy();
            } catch (Exception e) {
                throw new BeansException("Destroy method on bean with name '" + beanName + "' threw an exception", e);
            }
        }
    }


}

package com.learn.project.springframework.beans.factory.support;

import com.learn.project.springframework.beans.BeansException;
import com.learn.project.springframework.beans.factory.FactoryBean;
import org.w3c.dom.NodeList;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * FactoryBeanRegistrySupport
 *
 * @author chenfuyuan
 * @date 2023/2/7 09:43
 */
public class FactoryBeanRegistrySupport extends DefaultSingletonBeanRegistry {

    private Map<String, Object> factoryBeanObjectCache = new ConcurrentHashMap<>();

    protected Object getCachedObjectForFactoryBean(String beanName) {
        return this.factoryBeanObjectCache.get(beanName);
    }

    protected Object getObjectFromFactoryBean(FactoryBean factoryBean, String beanName) {
        if (factoryBean.isSingleton()) {
            Object object = this.factoryBeanObjectCache.get(beanName);
            if (object == null) {
                object = doGetObjectFromFactoryBean(factoryBean, beanName);
                this.factoryBeanObjectCache.put(beanName, object);
            }
            return object;
        } else {
            return doGetObjectFromFactoryBean(factoryBean, beanName);
        }
    }

    private Object doGetObjectFromFactoryBean(final FactoryBean factoryBean, final String beanName) {
        try {
            return factoryBean.getObject();
        } catch (Exception e) {
            throw new BeansException("FactoryBean throw exception on object[" + beanName + "] creation", e);
        }
    }
}

package com.learn.project.springframework.beans;

import cn.hutool.cache.Cache;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * CachedIntrospectionResults
 *
 * @author chenfuyuan
 * @date 2023/1/14 16:55
 */
public final class CachedIntrospectionResults {

    private final BeanInfo beanInfo;

    private final Map<String , PropertyDescriptor> propertyDescriptorMap;
    private CachedIntrospectionResults(Class<?> beanClass) {
        try {
            this.beanInfo = getBeanInfo(beanClass);
            this.propertyDescriptorMap = new LinkedHashMap<>();
            PropertyDescriptor[] pds = this.beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor pd : pds) {
                pd = buildGenericTypeAwarePropertyDescriptor(beanClass, pd);
                this.propertyDescriptorMap.put(pd.getName(), pd);
            }
        } catch (IntrospectionException e) {
            throw new BeansException("Failed to obtain BeanInfo for class [" + beanClass.getName() + "]",e);
        }
    }

    private PropertyDescriptor buildGenericTypeAwarePropertyDescriptor(Class<?> beanClass, PropertyDescriptor pd) {
        try {
            return new GenericTypeAwarePropertyDescriptor(beanClass, pd.getName(), pd.getReadMethod(), pd.getWriteMethod());
        } catch (IntrospectionException e) {
            throw new BeansException("Failed to re-introspect class [" + beanClass.getName() + "]", e);
        }
    }

    private BeanInfo getBeanInfo(Class<?> beanClass) throws IntrospectionException {
        return Introspector.getBeanInfo(beanClass);
    }
    public static CachedIntrospectionResults forClass(Class<?> beanClass) {
        CachedIntrospectionResults result = new CachedIntrospectionResults(beanClass);
        return result;
    }

    public PropertyDescriptor getPropertyDescriptor(String name) {
        PropertyDescriptor pd = this.propertyDescriptorMap.get(name);
        return pd;
    }
}

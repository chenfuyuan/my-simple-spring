package com.learn.project.springframework.beans;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

/**
 * GenericTypeAwarePropertyDescriptor
 *
 * @author chenfuyuan
 * @date 2023/1/14 17:35
 */
public final class GenericTypeAwarePropertyDescriptor extends PropertyDescriptor {

    private final Class<?> beanClass;

    private final Method readMethod;

    private final Method writeMethod;

    public GenericTypeAwarePropertyDescriptor(Class<?> beanClass, String propertyName,
                                              Method readMethod, Method writeMethod) throws IntrospectionException {
        super(propertyName, null, null);
        this.beanClass = beanClass;
        this.readMethod =readMethod;
        this.writeMethod =writeMethod;
    }

    public Class<?> getBeanClass() {
        return beanClass;
    }

    @Override
    public Method getReadMethod() {
        return readMethod;
    }

    @Override
    public Method getWriteMethod() {
        return writeMethod;
    }
}

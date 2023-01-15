package com.learn.project.springframework.beans;

import com.learn.project.springframework.util.Assert;

/**
 * AbstractNestablePropertyAccessor
 *
 * @author chenfuyuan
 * @date 2023/1/14 15:25
 */
public abstract class AbstractNestablePropertyAccessor extends AbstractPropertyAccessor {

    Object wrappedObject;

    public final Object getWrappedInstance() {
        Assert.notNull(wrappedObject, "No wrapped object");
        return this.wrappedObject;
    }

    public final Class<?> getWrappedClass() {
        return getWrappedInstance().getClass();
    }

    @Override
    public void setPropertyValue(String propertyName, Object value) throws BeansException {
        //使用内省的方式，填充属性
        //获取内省结果，内省结果中包含JavaBean的write方法
        PropertyHandler propertyHandler = getLocalPropertyHandler(propertyName);
        try {
            //填充属性
            propertyHandler.setValue(value);
        } catch (Exception e) {
            throw new BeansException("setPropertyValue Failed.",e);
        }
    }

    protected abstract PropertyHandler getLocalPropertyHandler(String propertyName);

    protected abstract class PropertyHandler {
        private final Class<?> propertyType;

        private final boolean readable;

        private final boolean writable;

        public PropertyHandler(Class<?> propertyType, boolean readable, boolean writable) {
            this.propertyType = propertyType;
            this.readable = readable;
            this.writable = writable;
        }

        public Class<?> getPropertyType() {
            return propertyType;
        }

        public boolean isReadable() {
            return readable;
        }

        public boolean isWritable() {
            return writable;
        }


        public abstract void setValue(Object value) throws Exception;
    }
}

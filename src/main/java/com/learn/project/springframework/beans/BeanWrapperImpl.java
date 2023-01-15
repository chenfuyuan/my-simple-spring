package com.learn.project.springframework.beans;

import com.learn.project.springframework.util.ReflectionUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

/**
 * BeanWrapperImpl
 *
 * @author chenfuyuan
 * @date 2023/1/14 13:02
 */
public class BeanWrapperImpl extends AbstractNestablePropertyAccessor implements BeanWrapper {


    /**
     * 缓存内省结果
     */
    private CachedIntrospectionResults cachedIntrospectionResults;

    public void setBeanInstance(Object object) {
        this.wrappedObject = object;
    }

    protected BeanPropertyHandler getLocalPropertyHandler(String propertyName) {
        CachedIntrospectionResults cachedIntrospectionResults = getCachedIntrospectionResults();
        PropertyDescriptor propertyDescriptor = cachedIntrospectionResults.getPropertyDescriptor(propertyName);
        return (propertyDescriptor != null ? new BeanPropertyHandler(propertyDescriptor) : null);
    }

    private CachedIntrospectionResults getCachedIntrospectionResults() {
        if (this.cachedIntrospectionResults == null) {
            cachedIntrospectionResults = CachedIntrospectionResults.forClass(getWrappedClass());
        }
        return this.cachedIntrospectionResults;
    }

    private class BeanPropertyHandler extends PropertyHandler {
        private final PropertyDescriptor pd;

        public BeanPropertyHandler(PropertyDescriptor pd) {
            super(pd.getPropertyType(), pd.getReadMethod() != null, pd.getWriteMethod() != null);
            this.pd = pd;
        }

        @Override
        public void setValue(Object value) throws Exception {
            Method writeMethod = this.pd instanceof GenericTypeAwarePropertyDescriptor typeAwarePd ?
                    typeAwarePd.getWriteMethod() : this.pd.getWriteMethod();
            ReflectionUtils.makeAccessible(writeMethod);
            //本实例调用写入方法
            writeMethod.invoke(getWrappedInstance(), value);
        }
    }

}

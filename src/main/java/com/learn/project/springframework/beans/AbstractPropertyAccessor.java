package com.learn.project.springframework.beans;

import java.util.Arrays;
import java.util.List;

/**
 * AbstractPropertyAccessor
 *
 * @author chenfuyuan
 * @date 2023/1/14 15:24
 */
public abstract class AbstractPropertyAccessor implements ConfigurablePropertyAccessor {


    @Override
    public void setPropertyValues(PropertyValues pvs) throws BeansException {
        setPropertyValues(pvs,false,false);
    }

    public void setPropertyValues(PropertyValues pvs, boolean ignoreUnknown, boolean ignoreInvalid) {
        List<PropertyValue> propertyValues = pvs instanceof MutablePropertyValues mpvs ?
                mpvs.getPropertyValueList() : Arrays.asList(pvs.getPropertyValues());
        for (PropertyValue propertyValue : propertyValues) {
            setPropertyValue(propertyValue);
        }
    }

    @Override
    public abstract void setPropertyValue(String propertyName, Object value) throws BeansException;

    @Override
    public void setPropertyValue(PropertyValue pv) throws BeansException {
        setPropertyValue(pv.getName(), pv.getValue());
    }
}

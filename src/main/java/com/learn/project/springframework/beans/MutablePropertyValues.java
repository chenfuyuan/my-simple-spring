package com.learn.project.springframework.beans;

import com.learn.project.springframework.util.Lists;

import java.util.Iterator;
import java.util.List;

/**
 * MutablePropertyValues
 *
 * @author chenfuyuan
 * @date 2023/1/14 13:10
 */
public class MutablePropertyValues implements PropertyValues {

    private final List<PropertyValue> propertyValueList;

    public MutablePropertyValues() {
        this.propertyValueList = Lists.newArrayList(0);
    }

    public MutablePropertyValues(MutablePropertyValues original) {
        if (original != null) {
            //copy original propertyValues
            PropertyValue[] propertyValues = original.getPropertyValues();
            this.propertyValueList = Lists.newArrayList(propertyValues.length);
            for (PropertyValue propertyValue : propertyValues) {
                this.propertyValueList.add(propertyValue);
            }
        } else {
            this.propertyValueList = Lists.newArrayList(0);
        }
    }

    public MutablePropertyValues(List<PropertyValue> propertyValueList) {
        this.propertyValueList = propertyValueList != null ? propertyValueList : Lists.newArrayList(0);
    }

    @Override
    public PropertyValue[] getPropertyValues() {
        return propertyValueList.toArray(new PropertyValue[0]);
    }

    @Override
    public PropertyValue getPropertyValue(String propertyName) {
        for (PropertyValue propertyValue : propertyValueList) {
            if (propertyValue.getName().equals(propertyName)) {
                return propertyValue;
            }
        }
        return null;
    }

    @Override
    public boolean isEmpty() {
        return this.propertyValueList.isEmpty();
    }

    public List<PropertyValue> getPropertyValueList() {
        return propertyValueList;
    }

    public MutablePropertyValues addPropertyValue(PropertyValue addPv) {
        for (int i = 0; i < this.propertyValueList.size(); i++) {
            PropertyValue currentPv = this.propertyValueList.get(i);
            //已存在相同的属性值，进行覆盖
            if (currentPv.getName().equals(addPv.getValue())) {
                this.propertyValueList.set(i, addPv);
                return this;
            }
        }
        this.propertyValueList.add(addPv);
        return this;
    }
}

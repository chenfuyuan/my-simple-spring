package com.learn.project.springframework.beans;

/**
 * PropertyAccessor
 *
 * @author chenfuyuan
 * @date 2023/1/14 15:42
 */
public interface PropertyAccessor {

    /**
     * 设置属性值集合
     * @param pvs
     * @throws BeansException
     */
    void setPropertyValues(PropertyValues pvs) throws BeansException;

    /**
     * 设置属性值
     * @param pv
     * @throws BeansException
     */
    void setPropertyValue(PropertyValue pv) throws BeansException;

    /**
     * 设置属性，根据属性名称和值
     * @param propertyName
     * @param value
     * @throws BeansException
     */
    void setPropertyValue(String propertyName, Object value) throws BeansException;
}


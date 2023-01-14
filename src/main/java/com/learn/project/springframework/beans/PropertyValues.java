package com.learn.project.springframework.beans;

/**
 * PropertyValues
 *
 * @author chenfuyuan
 * @date 2023/1/14 13:09
 */
public interface PropertyValues {

    /**
     * 获取属性值数组
     * @return
     */
    PropertyValue[] getPropertyValues();

    /**
     * 获取对应属性值
     * @param propertyName
     * @return
     */
    PropertyValue getPropertyValue(String propertyName);

    /**
     * 是否为空
     * @return
     */
    boolean isEmpty();

}

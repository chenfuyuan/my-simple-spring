package com.learn.project.springframework.beans;

import com.learn.project.springframework.util.Assert;

/**
 * PropertyValue
 *
 * @author chenfuyuan
 * @date 2023/1/14 13:09
 */
public class PropertyValue {

    private final String name;

    private final Object value;

    public PropertyValue(String name, Object value) {
        Assert.notNull(name, "Name must not be null");
        this.name = name;
        this.value =value;
    }


    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }
}

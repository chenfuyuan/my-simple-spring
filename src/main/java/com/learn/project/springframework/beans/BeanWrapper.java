package com.learn.project.springframework.beans;

/**
 * BeanWrapper
 *
 * @author chenfuyuan
 * @date 2023/1/14 13:01
 */
public interface BeanWrapper extends ConfigurablePropertyAccessor {

    /**
     * 获取实例对象
     * @return
     */
    Object getWrappedInstance();

    /**
     * 获取实例对象类型
     * @return
     */
    Class<?> getWrappedClass();
}

package com.learn.project.springframework.beans.factory;

/**
 * FactoryBean
 *
 * @author chenfuyuan
 * @date 2023/2/6 15:22
 */
public interface FactoryBean<T> {

    /**
     * 获取Object
     * @return
     */
    T getObject();

    /**
     * 获取Object对应的类型
     * @return
     */
    Class<T> getObjectType();

    /**
     * 判断是否是单例
     * @return
     */
    boolean isSingleton();
}

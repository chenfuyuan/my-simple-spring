package com.learn.project.springframework.beans.factory;

/**
 * BeanClassLoaderAware
 *
 * @author chenfuyuan
 * @date 2023/2/6 11:21
 */
public interface BeanClassLoaderAware extends Aware {

    void setBeanClassLoader(ClassLoader classLoader);
}

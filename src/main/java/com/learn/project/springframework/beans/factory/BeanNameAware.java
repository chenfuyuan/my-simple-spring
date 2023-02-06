package com.learn.project.springframework.beans.factory;

/**
 * BeanNameAware
 *
 * @author chenfuyuan
 * @date 2023/2/6 11:21
 */
public interface BeanNameAware extends Aware {

    void setBeanName(String beanName);

}

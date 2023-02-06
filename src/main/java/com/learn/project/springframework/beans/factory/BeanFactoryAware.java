package com.learn.project.springframework.beans.factory;

import com.learn.project.springframework.beans.BeansException;

/**
 * BeanFactoryAware
 *
 * @author chenfuyuan
 * @date 2023/2/6 11:21
 */
public interface BeanFactoryAware extends Aware {

    void setBeanFactory(BeanFactory beanFactory) throws BeansException;

}

package com.learn.project.springframework.beans.factory;

/**
 * BeanFactory
 *
 * @author chenfuyuan
 * @date 2023/1/6 18:57
 */
public interface BeanFactory {

    /**
     * 获取Bean
     * @param beanName
     * @return
     */
    Object getBean(String beanName);
}

package com.learn.project.springframework.beans.factory.config;

/**
 * BeanDefinition
 * 对Bean的定义(即对容器中Bean的抽象)
 * @author chenfuyuan
 * @date 2022/12/31 16:01
 */
public interface BeanDefinition {

    /**
     * 获取BeanClass名称
     * @return
     */
    String getBeanClassName();

}

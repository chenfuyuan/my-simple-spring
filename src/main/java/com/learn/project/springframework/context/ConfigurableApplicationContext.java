package com.learn.project.springframework.context;

import com.learn.project.springframework.beans.BeansException;

/**
 * ConfigurableApplicationContext
 *
 * @author chenfuyuan
 * @date 2023/1/29 23:00
 */
public interface ConfigurableApplicationContext extends ApplicationContext {

    /**
     * 刷新容器
     * @throws BeansException
     */
    void refresh() throws BeansException;


    /**
     * 注册关闭钩子
     */
    void registerShutdownHook();

    /**
     * 关闭
     */
    void close();

}

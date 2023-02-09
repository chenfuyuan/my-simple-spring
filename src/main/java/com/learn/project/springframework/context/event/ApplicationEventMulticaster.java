package com.learn.project.springframework.context.event;

import com.learn.project.springframework.context.ApplicationEvent;
import com.learn.project.springframework.context.ApplicationListener;

/**
 * ApplicationEventMulticaster
 *
 * @author chenfuyuan
 * @date 2023/2/8 16:51
 */
public interface ApplicationEventMulticaster {

    /**
     * 添加监听器
     * @param listener
     */
    void addApplicationListener(ApplicationListener<ApplicationEvent> listener);

    /**
     * 移除监听器
     * @param listener
     */
    void removeApplicationListener(ApplicationListener<ApplicationEvent> listener);

    /**
     * 广播事件
     * @param event
     */
    void multicastEvent(ApplicationEvent event);
}

package com.learn.project.springframework.context.event;

import com.learn.project.springframework.context.ApplicationEvent;

/**
 * ApplicationEventPublisher
 *
 * @author chenfuyuan
 * @date 2023/2/9 14:18
 */
public interface ApplicationEventPublisher {

    /**
     * 发布事件
     * @param event
     */
    void publishEvent(ApplicationEvent event);
}

package com.learn.project.springframework.context.event;

import com.learn.project.springframework.context.ApplicationContext;
import com.learn.project.springframework.context.ApplicationEvent;

/**
 * ContextRefreshedEvent
 *
 * @author chenfuyuan
 * @date 2023/2/8 16:51
 */
public class ContextRefreshedEvent extends ApplicationContextEvent {

    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public ContextRefreshedEvent(Object source) {
        super(source);
    }
}

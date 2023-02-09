package com.learn.project.springframework.context.event;

import com.learn.project.springframework.context.ApplicationEvent;

/**
 * ContextClosedEvent
 *
 * @author chenfuyuan
 * @date 2023/2/8 16:37
 */
public class ContextClosedEvent extends ApplicationContextEvent {

    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public ContextClosedEvent(Object source) {
        super(source);
    }
}

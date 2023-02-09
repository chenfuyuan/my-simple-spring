package com.learn.project.springframework.context;

import java.util.EventObject;

/**
 * ApplicationEvent
 *
 * @author chenfuyuan
 * @date 2023/2/7 16:42
 */
public abstract class ApplicationEvent extends EventObject {
    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public ApplicationEvent(Object source) {
        super(source);
    }

}

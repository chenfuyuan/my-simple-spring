package com.learn.project.springframework.context.event;

import com.learn.project.springframework.context.ApplicationContext;
import com.learn.project.springframework.context.ApplicationEvent;

/**
 * ApplicationContextEvent
 *
 * @author chenfuyuan
 * @date 2023/2/8 15:19
 */
public class ApplicationContextEvent extends ApplicationEvent {

    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public ApplicationContextEvent(Object source) {
        super(source);
    }


    /**
     * Get the {@code ApplicationContext} that the event was raised for.
     */
    public final ApplicationContext getApplicationContext() {
        return (ApplicationContext) getSource();
    }

}

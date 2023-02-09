package com.learn.project.springframework.event;

import com.learn.project.springframework.context.event.ApplicationContextEvent;

/**
 * CustomEvent
 *
 * @author chenfuyuan
 * @date 2023/2/9 17:02
 */
public class CustomEvent extends ApplicationContextEvent {

    private Long id;

    private String message;

    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public CustomEvent(Object source,Long id,String message) {
        super(source);
        this.id = id;
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

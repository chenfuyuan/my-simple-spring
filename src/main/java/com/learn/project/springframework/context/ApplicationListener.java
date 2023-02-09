package com.learn.project.springframework.context;

import java.util.EventListener;

/**
 * ApplicationListener
 *
 * @author chenfuyuan
 * @date 2023/2/8 16:55
 */
public interface ApplicationListener<E extends ApplicationEvent> extends EventListener {

    /**
     * Handle an application event.
     * @param event the event to respond to
     */
    void onApplicationEvent(E event);

}

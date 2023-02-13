package com.learn.project.springframework.event;

import com.learn.project.springframework.context.ApplicationListener;
import com.learn.project.springframework.context.event.ContextClosedEvent;

/**
 * ContextClosedEvent
 *
 * @author chenfuyuan
 * @date 2023/2/13 11:30
 */
public class ContextClosedEventListener implements ApplicationListener<ContextClosedEvent> {

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        System.out.println("context closed event listener 执行!");
    }
}

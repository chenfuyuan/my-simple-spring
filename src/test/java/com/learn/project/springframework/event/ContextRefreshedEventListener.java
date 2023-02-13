package com.learn.project.springframework.event;

import com.learn.project.springframework.context.ApplicationListener;
import com.learn.project.springframework.context.event.ContextRefreshedEvent;

/**
 * ContextRefreshedEvent
 *
 * @author chenfuyuan
 * @date 2023/2/13 11:30
 */
public class ContextRefreshedEventListener implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("context refreshed event listener 执行!");
    }
}

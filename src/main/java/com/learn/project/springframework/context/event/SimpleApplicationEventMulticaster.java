package com.learn.project.springframework.context.event;

import com.learn.project.springframework.beans.factory.BeanFactory;
import com.learn.project.springframework.context.ApplicationEvent;
import com.learn.project.springframework.context.ApplicationListener;
import com.learn.project.springframework.core.io.DefaultResourceLoader;

/**
 * SimpleApplicationEventMulticaster
 *
 * @author chenfuyuan
 * @date 2023/2/9 14:58
 */
public class SimpleApplicationEventMulticaster extends AbstractApplicationEventMulticaster {

    public SimpleApplicationEventMulticaster(BeanFactory beanFactory) {
        setBeanFactory(beanFactory);
    }

    @Override
    public void multicastEvent(ApplicationEvent event) {
        for (final ApplicationListener listener : getApplicationListeners(event)) {
            listener.onApplicationEvent(event);
        }
    }

}

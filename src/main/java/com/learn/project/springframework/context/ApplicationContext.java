package com.learn.project.springframework.context;

import com.learn.project.springframework.beans.factory.HierarchicalBeanFactory;
import com.learn.project.springframework.beans.factory.ListableBeanFactory;
import com.learn.project.springframework.context.event.ApplicationEventPublisher;
import com.learn.project.springframework.core.io.ResourceLoader;

/**
 * ApplicationContext
 *
 * @author chenfuyuan
 * @date 2023/1/29 22:58
 */
public interface ApplicationContext extends ListableBeanFactory, HierarchicalBeanFactory, ResourceLoader, ApplicationEventPublisher {
}

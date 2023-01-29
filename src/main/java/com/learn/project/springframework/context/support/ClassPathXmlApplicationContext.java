package com.learn.project.springframework.context.support;

import com.learn.project.springframework.beans.BeansException;

/**
 * ClassPathXmlAppliationContext
 *
 * @author chenfuyuan
 * @date 2023/1/29 23:21
 */
public class ClassPathXmlApplicationContext extends AbstractXmlApplicationContext {

    private String [] configLocations;

    public ClassPathXmlApplicationContext() {

    }

    public ClassPathXmlApplicationContext(String configLocations) throws BeansException {
        this(new String[]{configLocations});
    }

    public ClassPathXmlApplicationContext(String[] configLocations) throws BeansException {
        this.configLocations = configLocations;
        refresh();
    }

    @Override
    public String[] getConfigLocations() {
        return configLocations;
    }
}

package com.learn.project.springframework.context;

import com.learn.project.springframework.beans.BeansException;
import com.learn.project.springframework.beans.factory.Aware;

/**
 * ApplicationContextAware
 *
 * @author chenfuyuan
 * @date 2023/2/6 11:30
 */
public interface ApplicationContextAware extends Aware {

    void setApplicationContext(ApplicationContext applicationContext) throws BeansException;

}

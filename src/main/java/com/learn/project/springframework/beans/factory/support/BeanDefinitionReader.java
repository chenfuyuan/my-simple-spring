package com.learn.project.springframework.beans.factory.support;

import com.learn.project.springframework.core.io.Resource;
import com.learn.project.springframework.core.io.ResourceLoader;

/**
 * BeanDefinitionReader
 *
 * @author chenfuyuan
 * @date 2023/1/19 09:56
 */
public interface BeanDefinitionReader {

    BeanDefinitionRegistry getRegister();

    ResourceLoader getResourceLoader();

    void loadBeanDefinitions(Resource resource);

    void loadBeanDefinitions(Resource... resources);

    void loadBeanDefinitions(String location);
}

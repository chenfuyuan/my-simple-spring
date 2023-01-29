package com.learn.project.springframework.context.support;

import com.learn.project.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.learn.project.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * AbstractXmlApplicationContext
 *
 * @author chenfuyuan
 * @date 2023/1/29 23:16
 */
public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext {

    @Override
    protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) {
        //处理关于XML文件配置信息的操作
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory, this);
        String[] configLocations = getConfigLocations();
        if (null != configLocations) {
            beanDefinitionReader.loadBeanDefinitions(configLocations);
        }
    }

    /**
     * 获取配置信息的地址描述
     * @return
     */
    protected abstract String[] getConfigLocations();

}

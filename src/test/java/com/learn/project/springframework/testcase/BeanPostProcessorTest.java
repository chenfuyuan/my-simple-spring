package com.learn.project.springframework.testcase;

import com.learn.project.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.learn.project.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import com.learn.project.springframework.common.MyBeanFactoryPostProcessor;
import com.learn.project.springframework.common.MyBeanPostProcessor;
import com.learn.project.springframework.context.support.ClassPathXmlApplicationContext;
import com.learn.project.springframework.service.UserService;
import org.junit.jupiter.api.Test;

/**
 * BeanPostProcessorTest
 *
 * @author chenfuyuan
 * @date 2023/1/29 23:49
 */
public class BeanPostProcessorTest {

    @Test
    public void test_BeanFactoryPostProcessorAndBeanPostProcessor() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("classpath:spring.xml");

        //显式调用
        MyBeanFactoryPostProcessor beanFactoryPostProcessor = new MyBeanFactoryPostProcessor();
        beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);

        MyBeanPostProcessor beanPostProcessor = new MyBeanPostProcessor();
        beanFactory.addBeanPostProcessor(beanPostProcessor);

        UserService userService = beanFactory.getBean("userService",UserService.class);
        userService.queryUserInfo();
    }

    @Test
    public void test_xml() {
        // 1.初始化 BeanFactory
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:springPostProcessor.xml");

        // 2. 获取Bean对象调用方法
        UserService userService = applicationContext.getBean("userService", UserService.class);
        userService.queryUserInfo();
    }
}

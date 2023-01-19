package com.learn.project.springframework.testcase;

import com.learn.project.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.learn.project.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import com.learn.project.springframework.service.UserService;
import org.junit.jupiter.api.Test;

/**
 * ResolveXmlCreateBeanTest
 *
 * @author chenfuyuan
 * @date 2023/1/19 11:33
 */
public class LoadBeanDefinitionTest {

    @Test
    public void test_loadBeanDefinitionForXml() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("classpath:spring.xml");

        UserService userService = beanFactory.getBean("userService",UserService.class);
        userService.queryUserInfo();
    }

}

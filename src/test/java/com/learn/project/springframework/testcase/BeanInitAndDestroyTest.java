package com.learn.project.springframework.testcase;

import com.learn.project.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.learn.project.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import com.learn.project.springframework.context.ConfigurableApplicationContext;
import com.learn.project.springframework.context.support.ClassPathXmlApplicationContext;
import com.learn.project.springframework.service.UserService;
import org.junit.jupiter.api.Test;

/**
 * BeanInitAndDestroyTest
 *
 * @author chenfuyuan
 * @date 2023/2/6 10:08
 */
public class BeanInitAndDestroyTest {

    @Test
    public void testInit() {
        // 1.初始化 BeanFactory
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");

        // 2. 获取Bean对象调用方法
        UserService userService = applicationContext.getBean("userService", UserService.class);
        userService.queryUserInfo();
    }

    @Test
    public void testAddShutDownHook() {
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring.xml");
        context.registerShutdownHook();
        UserService userService = context.getBean("userService", UserService.class);
        userService.queryUserInfo();
    }
}

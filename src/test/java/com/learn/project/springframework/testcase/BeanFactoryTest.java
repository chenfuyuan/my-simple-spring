package com.learn.project.springframework.testcase;

import com.learn.project.springframework.beans.factory.config.BeanDefinition;
import com.learn.project.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.learn.project.springframework.beans.factory.support.RootBeanDefinition;
import com.learn.project.springframework.service.UserService;
import org.junit.jupiter.api.Test;

/**
 * BeanFactoryTest
 *
 * @author chenfuyuan
 * @date 2023/1/12 17:00
 */
public class BeanFactoryTest {

    private static final String USER_SERVICE_BEAN_NAME = "userService";

    @Test
    public void test_BeanFactory() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        //注册bean
        BeanDefinition userServiceDefinition = new RootBeanDefinition(UserService.class);
        beanFactory.registerBeanDefinition(USER_SERVICE_BEAN_NAME, userServiceDefinition);
        //获取bean
        UserService userService = (UserService) beanFactory.getBean(USER_SERVICE_BEAN_NAME);
        userService.queryUserInfo();

        UserService otherUserService = (UserService) beanFactory.getBean(USER_SERVICE_BEAN_NAME);
        otherUserService.queryUserInfo();

        System.out.println("两次获取的bean是否是同一个bean:" + (userService == otherUserService));

    }
}

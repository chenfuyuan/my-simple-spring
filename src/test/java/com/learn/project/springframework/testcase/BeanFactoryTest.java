package com.learn.project.springframework.testcase;

import com.learn.project.springframework.beans.MutablePropertyValues;
import com.learn.project.springframework.beans.PropertyValue;
import com.learn.project.springframework.beans.PropertyValues;
import com.learn.project.springframework.beans.factory.config.BeanDefinition;
import com.learn.project.springframework.beans.factory.config.RuntimeBeanReference;
import com.learn.project.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.learn.project.springframework.beans.factory.support.RootBeanDefinition;
import com.learn.project.springframework.dao.UserDao;
import com.learn.project.springframework.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * BeanFactoryTest
 *
 * @author chenfuyuan
 * @date 2023/1/12 17:00
 */
public class BeanFactoryTest {

    private static final String USER_SERVICE_BEAN_NAME = "userService";

    private static final String USER_DAO_BEAN_NAME = "userDao";

    private DefaultListableBeanFactory defaultBeanFactory;

    @BeforeEach
    public void initBefore() {
        defaultBeanFactory = new DefaultListableBeanFactory();

        BeanDefinition userDaoDefinition = new RootBeanDefinition(UserDao.class);
        defaultBeanFactory.registerBeanDefinition(USER_DAO_BEAN_NAME, userDaoDefinition);
        //userService
        MutablePropertyValues propertyValues = new MutablePropertyValues();
        propertyValues.addPropertyValue(new PropertyValue(USER_DAO_BEAN_NAME, new RuntimeBeanReference(USER_DAO_BEAN_NAME)));
        propertyValues.addPropertyValue(new PropertyValue("uId", "10001"));
        BeanDefinition userServiceDefinition = new RootBeanDefinition(UserService.class,propertyValues);
        defaultBeanFactory.registerBeanDefinition(USER_SERVICE_BEAN_NAME, userServiceDefinition);
    }

    @Test
    public void test_BeanFactory() {
        //获取bean
        UserService userService = (UserService) defaultBeanFactory.getBean(USER_SERVICE_BEAN_NAME);
        userService.queryUserInfo();
        UserService otherUserService = (UserService) defaultBeanFactory.getBean(USER_SERVICE_BEAN_NAME);
        otherUserService.queryUserInfo();
        System.out.println("两次获取的bean是否是同一个bean:" + (userService == otherUserService));
    }

    @Test
    public void testBeanFactoryHaveArgs() {
        UserService userService = (UserService) defaultBeanFactory.getBean(USER_SERVICE_BEAN_NAME);
        userService.queryUserInfo();
    }
}

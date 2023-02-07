package com.learn.project.springframework.testcase;

import com.learn.project.springframework.context.support.ClassPathXmlApplicationContext;
import com.learn.project.springframework.service.UserService;
import org.junit.jupiter.api.Test;

/**
 * ScopeAndFactoryBeanTest
 *
 * @author chenfuyuan
 * @date 2023/2/7 15:30
 */
public class ScopeAndFactoryBeanTest {


    @Test
    public void test_prototype() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        applicationContext.registerShutdownHook();

        UserService userService_01 = applicationContext.getBean("userService", UserService.class);
        UserService userService_02 = applicationContext.getBean("userService", UserService.class);

        System.out.println(userService_01);
        System.out.println(userService_02);
        System.out.println(userService_01 + "十六进制哈希值: " + Integer.toHexString(userService_01.hashCode()));
    }

    @Test
    public void test_factory_bean() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        applicationContext.registerShutdownHook();

        UserService userService = applicationContext.getBean("userService", UserService.class);
        userService.queryUserInfo();
    }
}

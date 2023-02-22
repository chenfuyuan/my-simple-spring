package com.learn.project.springframework.testcase;

import com.learn.project.springframework.context.support.ClassPathXmlApplicationContext;
import com.learn.project.springframework.service.ComponentService;
import org.junit.jupiter.api.Test;

import java.sql.SQLOutput;

/**
 * ComponentScanTest
 *
 * @author chenfuyuan
 * @date 2023/2/22 13:57
 */
public class ComponentScanTest {

    @Test
    public void test_componentScan() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        ComponentService componentService = applicationContext.getBean("componentService", ComponentService.class);
        System.out.println("componentService.class:" + componentService.getClass());
        componentService.invoke();
    }
}

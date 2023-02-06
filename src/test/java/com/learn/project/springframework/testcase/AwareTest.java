package com.learn.project.springframework.testcase;

import com.learn.project.springframework.context.ConfigurableApplicationContext;
import com.learn.project.springframework.context.support.ClassPathXmlApplicationContext;
import com.learn.project.springframework.service.FullAwareBean;
import org.junit.jupiter.api.Test;

/**
 * AwareTest
 *
 * @author chenfuyuan
 * @date 2023/2/6 14:13
 */
public class AwareTest {


    @Test
    public void test_aware() {
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring.xml");
        FullAwareBean fullAwareBean = context.getBean("fullAwareBean", FullAwareBean.class);
        System.out.println(fullAwareBean.toString());
    }
}

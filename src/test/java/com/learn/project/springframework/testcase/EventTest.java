package com.learn.project.springframework.testcase;

import com.learn.project.springframework.context.ApplicationContext;
import com.learn.project.springframework.context.support.ClassPathXmlApplicationContext;
import com.learn.project.springframework.event.CustomEvent;
import org.junit.jupiter.api.Test;

/**
 * EventTest
 *
 * @author chenfuyuan
 * @date 2023/2/9 17:13
 */
public class EventTest {

    @Test
    public void test_event() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        applicationContext.publishEvent(new CustomEvent(applicationContext,1L,"成功了!" ));
        applicationContext.registerShutdownHook();
    }
}

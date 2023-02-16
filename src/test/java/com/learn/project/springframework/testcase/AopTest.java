package com.learn.project.springframework.testcase;

import com.learn.project.springframework.aop.AdvisedSupport;
import com.learn.project.springframework.aop.TargetSource;
import com.learn.project.springframework.aop.aspectj.AspectJExpressionPointcut;
import com.learn.project.springframework.aop.framework.Cglib2AopProxy;
import com.learn.project.springframework.aop.framework.JdkDynamicAopProxy;
import com.learn.project.springframework.context.support.ClassPathXmlApplicationContext;
import com.learn.project.springframework.dao.IUserDao;
import com.learn.project.springframework.dao.IUserDaoImpl;
import com.learn.project.springframework.interceptor.UserServiceInterceptor;
import com.learn.project.springframework.service.IMemberService;
import com.learn.project.springframework.service.IUserService;
import com.learn.project.springframework.service.MemberService;
import com.learn.project.springframework.service.UserService;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * AopTest
 *
 * @author chenfuyuan
 * @date 2023/2/13 14:42
 */
public class AopTest {

    @Test
    public void test_aop_matches() throws NoSuchMethodException {
        //传入匹配表达式
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut("execution(* com.learn.project.springframework.service.UserService.*(..))");
        Class<UserService> userServiceClass = UserService.class;
        Method method = userServiceClass.getDeclaredMethod("queryUserInfo");
        // 切点匹配对应的方法
        System.out.println(pointcut.matches(userServiceClass));
        System.out.println(pointcut.matches(method,userServiceClass));
    }

    @Test
    public void test_dynamic() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        IUserService userService = applicationContext.getBean("userService",IUserService.class);
        AdvisedSupport advisedSupport = new AdvisedSupport();
        advisedSupport.setTargetSource(new TargetSource(userService));
        advisedSupport.setMethodInterceptor(new UserServiceInterceptor());
        advisedSupport.setMethodMatcher(new AspectJExpressionPointcut("execution(* com.learn.project.springframework.service.IUserService.*(..))"));

        IUserService proxy_jdk = (IUserService) new JdkDynamicAopProxy(advisedSupport).getProxy();
        proxy_jdk.queryUserInfo();

        IUserService proxy_cglib = (IUserService) new Cglib2AopProxy(advisedSupport).getProxy();
        proxy_cglib.queryUserInfo();
    }


    @Test
    public void test_aop() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring_advice.xml");
        IMemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        System.out.println(memberService.queryUserInfo());
    }
}

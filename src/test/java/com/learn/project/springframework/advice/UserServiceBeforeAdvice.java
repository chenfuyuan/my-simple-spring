package com.learn.project.springframework.advice;

import com.learn.project.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * UserServiceBeforeAdvice
 *
 * @author chenfuyuan
 * @date 2023/2/16 16:08
 */
public class UserServiceBeforeAdvice implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("拦截方法:" + method.getName());
    }
}

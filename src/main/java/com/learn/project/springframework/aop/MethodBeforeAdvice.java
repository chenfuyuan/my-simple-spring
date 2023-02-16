package com.learn.project.springframework.aop;

import java.lang.reflect.Method;

/**
 * MethodBeforeAdvice
 *
 * @author chenfuyuan
 * @date 2023/2/16 14:20
 */
public interface MethodBeforeAdvice extends BeforeAdvice {

    /**
     * Callback before a given method is invoked.
     * @param method
     * @param args
     * @param target
     * @throws Throwable
     */
    void before(Method method, Object[] args, Object target) throws Throwable;
}

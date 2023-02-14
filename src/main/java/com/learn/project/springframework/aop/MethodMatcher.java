package com.learn.project.springframework.aop;

import java.lang.reflect.Method;

/**
 * MethodMatcher
 *
 * @author chenfuyuan
 * @date 2023/2/13 15:17
 */
public interface MethodMatcher {

    boolean matches(Method method, Class<?> targetClass);
}

package com.learn.project.springframework.aop;

/**
 * Pointcut
 * 切点
 * @author chenfuyuan
 * @date 2023/2/13 15:16
 */
public interface Pointcut {

    /**
     * Return the ClassFilter for this pointer.
     * @return the ClassFilter (never <code>null</code>)
     */
    ClassFilter getClassFilter();

    /**
     * Return the MethodMatcher for this pointer.
     * @return the MethodMatcher (never <code>null</code>)
     */
    MethodMatcher getMethodMatcher();
}

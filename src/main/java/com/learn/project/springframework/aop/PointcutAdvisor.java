package com.learn.project.springframework.aop;

/**
 * PointcutAdvisor
 *
 * @author chenfuyuan
 * @date 2023/2/16 14:23
 */
public interface PointcutAdvisor extends Advisor {

    Pointcut getPointcut();

}

package com.learn.project.springframework.aop;

import org.aopalliance.aop.Advice;

/**
 * Advisor
 *
 * @author chenfuyuan
 * @date 2023/2/16 14:22
 */
public interface Advisor {

    Advice getAdvice();
}

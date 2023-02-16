package com.learn.project.springframework.aop.aspectj;

import com.learn.project.springframework.aop.Pointcut;
import com.learn.project.springframework.aop.PointcutAdvisor;
import org.aopalliance.aop.Advice;

/**
 * AspectJExpressionPointcutAdvisor
 *
 * @author chenfuyuan
 * @date 2023/2/16 14:30
 */
public class AspectJExpressionPointcutAdvisor implements PointcutAdvisor {

    /**
     * 切点
     */
    private AspectJExpressionPointcut pointcut;

    /**
     * 执行方法
     */
    private Advice advice;

    /**
     * 表达式
     */
    private String expression;

    public void setExpression(String expression) {
        this.expression = expression;
    }


    public void setAdvice(Advice advice) {
        this.advice = advice;
    }

    @Override
    public Advice getAdvice() {
        return this.advice;
    }

    @Override
    public Pointcut getPointcut() {
        if (null == pointcut) {
            pointcut = new AspectJExpressionPointcut(expression);
        }
        return this.pointcut;
    }
}

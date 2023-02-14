package com.learn.project.springframework.aop.framework;

import com.learn.project.springframework.aop.TargetSource;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;

/**
 * ReflectMethodInvocation
 *
 * @author chenfuyuan
 * @date 2023/2/14 15:19
 */
public class ReflectiveMethodInvocation implements MethodInvocation {


    protected final Object target;

    protected final Method method;

    protected Object[] args;

    public ReflectiveMethodInvocation(Object target, Method method, Object[] args) {
        this.target = target;
        this.method = method;
        this.args = args;
    }


    @Override
    public Object proceed() throws Throwable {
        return method.invoke(target, args);
    }

    @Override
    public Object getThis() {
        return target;
    }

    @Override
    public AccessibleObject getStaticPart() {
        return method;
    }

    @Override
    public Object[] getArguments() {
        return this.args;
    }

    @Override
    public Method getMethod() {
        return this.method;
    }
}

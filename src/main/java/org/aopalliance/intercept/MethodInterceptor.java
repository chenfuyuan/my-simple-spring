package org.aopalliance.intercept;

/**
 * MethodInterceptor
 *
 * @author chenfuyuan
 * @date 2023/2/14 14:26
 */
@FunctionalInterface
public interface MethodInterceptor extends Interceptor {

    Object invoke(MethodInvocation invocation) throws Throwable;
}

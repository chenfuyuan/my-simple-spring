package org.aopalliance.intercept;

import java.lang.reflect.Method;

/**
 * MethodInvocation
 *
 * @author chenfuyuan
 * @date 2023/2/14 14:38
 */
public interface MethodInvocation extends Invocation {

    Method getMethod();
}

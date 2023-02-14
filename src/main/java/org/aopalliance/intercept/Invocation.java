package org.aopalliance.intercept;

/**
 * Invocation
 *
 * @author chenfuyuan
 * @date 2023/2/14 14:38
 */
public interface Invocation extends Joinpoint {


    Object[] getArguments();

}

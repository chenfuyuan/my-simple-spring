package com.learn.project.springframework.beans;

import com.learn.project.springframework.core.NestedRuntimeException;

/**
 * BeansException
 *
 * @author chenfuyuan
 * @date 2023/1/12 16:15
 */
public class BeansException extends NestedRuntimeException {
    public BeansException(String message) {
        super(message);
    }

    public BeansException(String message, Throwable cause) {
        super(message, cause);
    }
}

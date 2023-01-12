package com.learn.project.springframework.core;

/**
 * NestedRuntimeException
 *
 * @author chenfuyuan
 * @date 2023/1/12 16:16
 */
public class NestedRuntimeException extends RuntimeException {

    public NestedRuntimeException(String message) {
        super(message);
    }

    public NestedRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }
}

package com.learn.project.springframework.util;

/**
 * Assert
 * copy by spring.core Assert
 * @author chenfuyuan
 * @date 2023/1/12 13:51
 */
public abstract class Assert {

    public static void isTrue(boolean expression, String messsage) {
        if (!expression) {
            throw new IllegalStateException(messsage);
        }
    }

    public static void isTrue(boolean expression) {
        isTrue(expression, "[Assertion failed] - this expression must be true");
    }

    public static void notNull(Object object, String message) {
        if (object == null) {
            throw new IllegalArgumentException(message);
        }
    }
}

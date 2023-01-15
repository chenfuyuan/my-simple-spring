package com.learn.project.springframework.util;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * ReflectionUtils
 *
 * @author chenfuyuan
 * @date 2023/1/15 14:41
 */
public abstract class ReflectionUtils {

    public static void makeAccessible(Method method) {
        if ((!Modifier.isPublic(method.getModifiers()) || !Modifier.isPublic(method.getDeclaringClass().getModifiers())) && !method.canAccess(method)) {
            method.setAccessible(true);
        }

    }
}

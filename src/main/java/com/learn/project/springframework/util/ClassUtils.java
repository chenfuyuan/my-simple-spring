package com.learn.project.springframework.util;

import java.util.IdentityHashMap;
import java.util.Map;

/**
 * ClassUtils
 *
 * @author chenfuyuan
 * @date 2023/1/13 14:55
 */
public class ClassUtils {

    private static final Map<Class<?>, Class<?>> primitiveWrapperTypeMap = new IdentityHashMap<>(9);

    private static final Map<Class<?>, Class<?>> primitiveTypeToWrapperMap = new IdentityHashMap<>(9);

    static {
        primitiveWrapperTypeMap.put(Boolean.class, boolean.class);
        primitiveWrapperTypeMap.put(Byte.class, byte.class);
        primitiveWrapperTypeMap.put(Character.class, char.class);
        primitiveWrapperTypeMap.put(Double.class, double.class);
        primitiveWrapperTypeMap.put(Float.class, float.class);
        primitiveWrapperTypeMap.put(Integer.class, int.class);
        primitiveWrapperTypeMap.put(Long.class, long.class);
        primitiveWrapperTypeMap.put(Short.class, short.class);
        primitiveWrapperTypeMap.put(Void.class, void.class);

        for (Map.Entry<Class<?>, Class<?>> entry : primitiveWrapperTypeMap.entrySet()) {
            primitiveTypeToWrapperMap.put(entry.getValue(), entry.getKey());
        }
    }
    public static boolean isAssignableValue(Class<?> type, Object value) {
        Assert.notNull(type, "Type must not be null");
        return value != null ? isAssignable(type, value.getClass()) : !type.isPrimitive();
    }

    private static boolean isAssignable(Class<?> lhsType, Class<?> rhsType) {
        Assert.notNull(lhsType, "Left-hand side type must not be null");
        Assert.notNull(rhsType, "Right-hand side type must not be null");
        if (lhsType.isAssignableFrom(rhsType)) {
            //左边是否是右边的父类
            return true;
        }

        //基础类型和基础类型包装类特殊处理
        if (lhsType.isPrimitive()) {
            //左边参数类型为基本数据类型，判断右边是否是对应的基础数据类型
            //右边可能是包装类，获取对应的基础数据类型,并进行比较
            Class<?> resolvedPrimitive = primitiveWrapperTypeMap.get(rhsType);
            //比较类型是否相等
            return lhsType == resolvedPrimitive;
        } else {
            //如果左边的参数类型不为基础数据类型,可能为包装类
            //右边可能为基础数据类型，获取对应的包装类，进行比较
            Class<?> resolvedWrapper = primitiveTypeToWrapperMap.get(rhsType);
            //比较类型是否匹配
            return resolvedWrapper != null && lhsType.isAssignableFrom(resolvedWrapper);
        }
    }

    public static ClassLoader getDefaultClassLoader() {
        ClassLoader classLoader = null;
        try {
            classLoader = Thread.currentThread().getContextClassLoader();
        } catch (Throwable ex) {
            // 获取当前线程的classLoader失败
        }
        if (classLoader == null) {
            // 获取ClassUtils的classLoader
            classLoader = ClassUtils.class.getClassLoader();
            if (classLoader == null) {
                try {
                    //获取系统的ClassLoader
                    classLoader = ClassLoader.getSystemClassLoader();
                } catch (Throwable ex) {
                    // 获取系统的ClassLoader失败,或许调用者接受空的classLoader
                    // Cannot access system ClassLoader - oh well, maybe the caller can live with null...
                }
            }
        }
        return classLoader;
    }
}

package com.learn.project.springframework.aop;

/**
 * ClassFilter
 * 类匹配类，用于切点找到给定的接口和目标类
 * @author chenfuyuan
 * @date 2023/2/13 15:17
 */
public interface ClassFilter {

    /**
     *
     * @param clazz
     * @return
     */
    boolean matches(Class<?> clazz);
}

package com.learn.project.springframework.util;

/**
 * StringUtils
 *
 * @author chenfuyuan
 * @date 2023/1/30 18:10
 */
public class StringUtils {

    public static boolean isNotEmpty(String checkStr) {
        return !isEmpty(checkStr);
    }

    public static boolean isEmpty(String checkStr) {
        return checkStr == null || "".equals(checkStr);
    }
}

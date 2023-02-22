package com.learn.project.springframework.util;

import cn.hutool.core.util.StrUtil;

/**
 * StringUtils
 *
 * @author chenfuyuan
 * @date 2023/1/30 18:10
 */
public class StringUtils {

    public static final String EMPTY = "";

    public static boolean isNotEmpty(String checkStr) {
        return !isEmpty(checkStr);
    }

    public static boolean isEmpty(String checkStr) {
        return checkStr == null || "".equals(checkStr);
    }

    public static String lowerFirst(String str) {
        if (str == null) {
            return null;
        }
        if (str.length() > 0) {
            char firstChat = str.charAt(0);
            if (Character.isUpperCase(firstChat)) {
                return Character.toLowerCase(firstChat) + str.substring(1);
            }
        }
        return str;
    }

    public static String[] splitToArray(String str, String splitFlag) {
        if (str == null) {
            return new String[0];
        }
        return str.split(splitFlag);
    }
}

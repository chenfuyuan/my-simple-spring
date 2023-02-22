package com.learn.project.springframework.util;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * CharsetUtils
 *
 * @author chenfuyuan
 * @date 2023/2/22 14:55
 */
public class CharsetUtils {
    public static final Charset CHARSET_UTF_8;

    static {
        CHARSET_UTF_8 = StandardCharsets.UTF_8;
    }
}

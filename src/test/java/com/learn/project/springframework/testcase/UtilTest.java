package com.learn.project.springframework.testcase;

import com.learn.project.springframework.util.StringUtils;
import org.junit.jupiter.api.Test;

/**
 * UtilTest
 *
 * @author chenfuyuan
 * @date 2023/2/22 10:59
 */
public class UtilTest {

    @Test
    public void test_subString() {
        String str = "ABCDEFG";
        String s = StringUtils.lowerFirst(str);
        System.out.println(s);
    }
}

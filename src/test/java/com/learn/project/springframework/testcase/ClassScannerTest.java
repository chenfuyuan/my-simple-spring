package com.learn.project.springframework.testcase;

import com.learn.project.springframework.core.lang.ClassScanner;
import com.learn.project.springframework.stereotype.Component;
import org.junit.jupiter.api.Test;

import java.util.Set;

/**
 * ClassScannerTest
 *
 * @author chenfuyuan
 * @date 2023/2/22 14:30
 */
public class ClassScannerTest {

    @Test
    public void ClassScannerTest() {
        Set<Class<?>> classes = ClassScanner.scanPackageByAnnotation("com.learn.project.springframework", Component.class);
        System.out.println(classes);

    }
}

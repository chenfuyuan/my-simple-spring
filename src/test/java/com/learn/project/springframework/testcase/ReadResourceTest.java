package com.learn.project.springframework.testcase;

import cn.hutool.core.io.IoUtil;
import com.learn.project.springframework.core.io.DefaultResourceLoader;
import com.learn.project.springframework.core.io.InputStreamSource;
import com.learn.project.springframework.core.io.Resource;
import com.learn.project.springframework.core.io.ResourceLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * ReadResourceTest
 *
 * @author chenfuyuan
 * @date 2023/1/15 15:38
 */
public class ReadResourceTest {

    private ResourceLoader resourceLoader;

    @BeforeEach
    public void init() {
        resourceLoader = new DefaultResourceLoader();
    }

    @Test
    public void test_readResourceForClassPath() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:important.properties");
        printResourceContent(resource);
    }

    private void printResourceContent(Resource resource) throws IOException {
        InputStream inputStream = resource.getInputStream();
        String content = IoUtil.readUtf8(inputStream);
        System.out.println(content);
    }

    @Test
    public void test_file() throws IOException {
        Resource resource = resourceLoader.getResource("src/test/resources/important.properties");
        printResourceContent(resource);
    }

    @Test
    public void testUrl() throws IOException {
        Resource resource = resourceLoader.getResource("https://github.com/fuzhengwei/small-spring/blob/main/important.properties");
        printResourceContent(resource);
    }

}

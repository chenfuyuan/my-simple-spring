package com.learn.project.springframework.testcase;

import com.learn.project.springframework.beans.CachedIntrospectionResults;
import com.learn.project.springframework.service.UserService;
import org.junit.jupiter.api.Test;

/**
 * IntrospectionTest
 *
 * @author chenfuyuan
 * @date 2023/1/14 17:44
 */
public class IntrospectionTest {

    @Test
    public void test_createCachedIntrospectionResults() {
        CachedIntrospectionResults cachedIntrospectionResults = CachedIntrospectionResults.forClass(UserService.class);
        System.out.println(cachedIntrospectionResults);
        System.out.println(cachedIntrospectionResults.getPropertyDescriptor("userDao"));
    }

}

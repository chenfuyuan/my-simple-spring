package com.learn.project.springframework.core.io;

/**
 * ResourceLoader
 *
 * @author chenfuyuan
 * @date 2023/1/19 09:57
 */
public interface ResourceLoader {

    /**
     * Classpath url 前缀
     */
    String CLASSPATH_URL_PREFIX = "classpath:";

    /**
     * 获取资源
     * @param location
     * @return
     */
    Resource getResource(String location);
}

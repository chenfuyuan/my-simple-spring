package com.learn.project.springframework.util;

import cn.hutool.core.io.IORuntimeException;
import com.learn.project.springframework.core.collection.EnumerationIter;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

/**
 * ResourceUtils
 *
 * @author chenfuyuan
 * @date 2023/2/22 14:36
 */
public class ResourceUtils {

    public static EnumerationIter<URL> getResourceIter(String resource) {
        Enumeration resources;
        try {
            resources = ClassUtils.getDefaultClassLoader().getResources(resource);
        } catch (IOException e) {
            throw new IORuntimeException(e);
        }
        return new EnumerationIter(resources);
    }
}

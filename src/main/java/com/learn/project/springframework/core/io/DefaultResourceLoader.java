package com.learn.project.springframework.core.io;

import com.learn.project.springframework.util.Assert;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * DefaultResourceLoader
 *
 * @author chenfuyuan
 * @date 2023/1/19 09:58
 */
public class DefaultResourceLoader implements ResourceLoader {

    @Override
    public Resource getResource(String location) {
        Assert.notNull(location,"Location must not be null");
        if (location.startsWith(CLASSPATH_URL_PREFIX)) {
            //前缀为 classpath: 时，使用ClassPathResource获取资源
            return new ClassPathResource(location.substring(CLASSPATH_URL_PREFIX.length()));
        }
        try {
            //先使用URL形式获取，如果抛出异常，再使用FileSystem形式获取
            URL url = new URL(location);
            return new UrlResource(url);
        } catch (MalformedURLException e) {
            return new FileSystemResource(location);
        }
    }


}

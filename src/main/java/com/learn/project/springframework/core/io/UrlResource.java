package com.learn.project.springframework.core.io;

import com.learn.project.springframework.util.Assert;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * UrlResource
 *
 * @author chenfuyuan
 * @date 2023/1/19 09:55
 */
public class UrlResource extends AbstractResource{

    private final URL url;

    public UrlResource(URL url) {
        Assert.notNull(url, "URL must not be null");
        this.url = url;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        URLConnection connection = this.url.openConnection();
        try {
            return connection.getInputStream();
        } catch (IOException ex) {
            if (connection instanceof HttpURLConnection) {
                // 断开连接
                ((HttpURLConnection) connection).disconnect();
            }
            throw ex;
        }

    }
}

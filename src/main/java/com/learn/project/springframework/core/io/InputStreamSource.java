package com.learn.project.springframework.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * InputStreamSource
 *
 * @author chenfuyuan
 * @date 2023/1/17 14:43
 */
public interface InputStreamSource {

    /**
     * 获取输入流
     * @return
     * @throws IOException
     */
    InputStream getInputStream() throws IOException;
}

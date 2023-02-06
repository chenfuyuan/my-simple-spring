package com.learn.project.springframework.beans.factory;

/**
 * DisposableBean
 *
 * @author chenfuyuan
 * @date 2023/1/30 17:47
 */
public interface DisposableBean {

    /**
     * 对象销毁时触发
     * @throws Exception
     */
    void destroy() throws Exception;
}

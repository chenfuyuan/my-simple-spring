package com.learn.project.springframework.beans.factory;

/**
 * InitializingBean
 *
 * @author chenfuyuan
 * @date 2023/1/30 17:47
 */
public interface InitializingBean {


    /**
     * Bean处理了属性填充后调用
     * @throws Exception
     */
    void afterPropertiesSet() throws Exception;
}

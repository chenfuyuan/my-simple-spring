package com.learn.project.springframework.beans.factory;

import java.util.List;
import java.util.Map;

/**
 * ListableBeanFactory
 * 扩展BeanFactory接口
 * @author chenfuyuan
 * @date 2023/1/19 10:04
 */
public interface ListableBeanFactory extends BeanFactory{

    /**
     * 根据类型获取所有该类型的bean对象
     * @param type
     * @return
     * @param <T>
     */
    <T>Map<String,T> getBeansOfType(Class<T> type);


}

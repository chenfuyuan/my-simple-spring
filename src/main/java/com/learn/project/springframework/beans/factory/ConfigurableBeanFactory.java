package com.learn.project.springframework.beans.factory;

/**
 * ConfigurableBeanFactory
 * 可获取BeanPostProcessor和BeanClassLoader等的一个配置化接口
 * @author chenfuyuan
 * @date 2023/1/19 10:05
 */
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory {

    String SCOPE_SINGLETON = "singleton";

    String SCOPE_PROTOTYPE = "prototype";


}

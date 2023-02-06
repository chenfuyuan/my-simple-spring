package com.learn.project.springframework.beans.factory.support;

import com.learn.project.springframework.beans.BeansException;
import com.learn.project.springframework.beans.factory.DisposableBean;
import com.learn.project.springframework.util.StringUtils;

import java.lang.reflect.Method;

/**
 * DisposableBeanAdapter
 *
 * @author chenfuyuan
 * @date 2023/1/30 18:14
 */
public class DisposableBeanAdapter implements DisposableBean {

    private final Object bean;

    private final String beanName;

    private String destroyMethodName;

    public DisposableBeanAdapter(Object bean, String beanName,RootBeanDefinition beanDefinition) {
        this.bean = bean;
        this.beanName = beanName;
        this.destroyMethodName = beanDefinition.getDestroyMethodName();
    }

    @Override
    public void destroy() throws Exception {
        //实现销毁接口
        if (bean instanceof DisposableBean) {
            ((DisposableBean) bean).destroy();
        }

        //配置信息 destroy-method
        if (StringUtils.isNotEmpty(destroyMethodName) && !(bean instanceof DisposableBean) && "destroy".equals(this.destroyMethodName)) {
            Method destroyMethod = bean.getClass().getMethod(destroyMethodName);
            if (null == destroyMethod) {
                throw new BeansException("Couldn't find a destroy method named '" + destroyMethodName + "' on bean with name '" + beanName + "'");
            }
            destroyMethod.invoke(bean);
        }

    }
}

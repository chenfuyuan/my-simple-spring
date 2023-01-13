package com.learn.project.springframework.beans.factory.support;

import com.learn.project.springframework.beans.BeansException;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;

import java.lang.reflect.Constructor;

/**
 * CglibInstantiationStrategy
 *
 * @author chenfuyuan
 * @date 2023/1/13 13:27
 */
public class CglibInstantiationStrategy extends SimpleInstantiationStrategy {

    @Override
    public Object instantiate(RootBeanDefinition beanDefinition, String beanName, Constructor constructor, Object[] args) throws BeansException {
        //使用Cglib创建实例对象
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(beanDefinition.getBeanClass());
        enhancer.setCallback(new NoOp() {
            @Override
            public int hashCode() {
                return super.hashCode();
            }
        });
        if (null == constructor) {
            return enhancer.create();
        } else {
            return enhancer.create(constructor.getParameterTypes(),args);
        }
    }
}

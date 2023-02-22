package com.learn.project.springframework.context.annotation;

import com.learn.project.springframework.beans.BeansException;
import com.learn.project.springframework.beans.factory.config.BeanDefinition;
import com.learn.project.springframework.beans.factory.support.AbstractBeanDefinition;
import com.learn.project.springframework.beans.factory.support.BeanDefinitionRegistry;
import com.learn.project.springframework.stereotype.Component;
import com.learn.project.springframework.util.Assert;
import com.learn.project.springframework.util.StringUtils;

import java.util.Set;

/**
 * ClassPathBeanDefinitionScanner
 *
 * @author chenfuyuan
 * @date 2023/2/21 14:41
 */
public class ClassPathBeanDefinitionScanner extends ClassPathScanningCandidateComponentProvider {

    private BeanDefinitionRegistry registry;


    public ClassPathBeanDefinitionScanner(BeanDefinitionRegistry registry) {
        this.registry = registry;
    }

    public int scan(String... basePackages) {
        doScan(basePackages);
        return 0;
    }

    private void doScan(String...basePackages) {
        Assert.notEmpty(basePackages, "At least one base package must be specified");
        for (String basePackage : basePackages) {
            Set<BeanDefinition> candidates = findCandidateComponents(basePackage);
            for (BeanDefinition beanDefinition : candidates) {
                String beanScope = resolveBeanScope(beanDefinition);
                if (StringUtils.isNotEmpty(beanScope)) {
                    beanDefinition.setScope(beanScope);
                }
                registry.registerBeanDefinition(determineBeanName(beanDefinition), beanDefinition);
            }
        }
    }

    /**
     * 获取beanName
     * @param beanDefinition
     * @return
     */
    private String determineBeanName(BeanDefinition beanDefinition) {
        if (!(beanDefinition instanceof AbstractBeanDefinition)) {
            throw new BeansException("no available bean definition class");
        }
        Class<?> beanClass = ((AbstractBeanDefinition) beanDefinition).getBeanClass();
        Component component = beanClass.getAnnotation(Component.class);
        String value = component.value();
        //如果未设置名称，则使用第一个字母小写的简易类名作为beanName
        if (StringUtils.isEmpty(value)) {
            //获取类的简易名称，并将首字母设置为小写
            value = StringUtils.lowerFirst(beanClass.getSimpleName());
        }
        return value;
    }

    private String resolveBeanScope(BeanDefinition beanDefinition) {
        if (!(beanDefinition instanceof AbstractBeanDefinition)) {
            throw new BeansException("no available bean definition class");
        }
        AbstractBeanDefinition abstractBeanDefinition = (AbstractBeanDefinition) beanDefinition;
        Class<?> beanClass = abstractBeanDefinition.getBeanClass();
        Scope scope = beanClass.getAnnotation(Scope.class);
        if (null != scope) {
            return scope.value();
        }
        return StringUtils.EMPTY;
    }
}

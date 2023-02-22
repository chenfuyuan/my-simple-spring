package com.learn.project.springframework.context.annotation;

import cn.hutool.core.util.ClassUtil;
import com.learn.project.springframework.beans.factory.config.BeanDefinition;
import com.learn.project.springframework.beans.factory.support.RootBeanDefinition;
import com.learn.project.springframework.stereotype.Component;
import com.learn.project.springframework.util.ClassUtils;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * ClassPathScanningCandidateComponentProvider
 *
 * @author chenfuyuan
 * @date 2023/2/21 14:42
 */
public class ClassPathScanningCandidateComponentProvider {

    public Set<BeanDefinition> findCandidateComponents(String basePackage) {
        Set<BeanDefinition> candidates = new LinkedHashSet<>();
        Set<Class<?>> scanClasses = ClassUtils.scanPackageByAnnotation(basePackage, Component.class);
        for (Class<?> clazz : scanClasses) {
            candidates.add(new RootBeanDefinition(clazz));
        }
        return candidates;
    }
}

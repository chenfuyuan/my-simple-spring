package com.learn.project.springframework.beans.factory.support;

import com.learn.project.springframework.beans.BeanWrapper;
import com.learn.project.springframework.beans.BeanWrapperImpl;
import com.learn.project.springframework.beans.BeansException;
import com.learn.project.springframework.util.MethodInvoker;

import java.lang.reflect.Constructor;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * ConstructorResolver
 *
 * @author chenfuyuan
 * @date 2023/1/13 14:16
 */
public class ConstructorResolver {

    private final AbstractAutowireCapableBeanFactory beanFactory;

    public ConstructorResolver(AbstractAutowireCapableBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public BeanWrapper autowireConstructor(String beanName, RootBeanDefinition beanDefinition, Constructor[] choseConstructors, Object[] explicitArgs) {
        BeanWrapperImpl beanWrapper = new BeanWrapperImpl();
        if (explicitArgs == null) {
            Object instance = instantiate(beanName, beanDefinition, null, null);
            beanWrapper.setBeanInstance(instance);
            return beanWrapper;
        }
        Constructor<?> constructorToUse = null;
        Object[] argsToUse = explicitArgs;

        Constructor[] candidates = choseConstructors;
        if (candidates == null) {
            //从BeanClass中获取构造器列表
            Class<?> beanClass = beanDefinition.getBeanClass();
            candidates = beanClass.getDeclaredConstructors();
        }
        int minNrOfArgs = 0;
        if (explicitArgs != null) {
            minNrOfArgs = explicitArgs.length;
        }
        int minTypeDiffWeight = Integer.MAX_VALUE;
        Set<Constructor<?>> ambiguousConstructors = null;
        for (Constructor candidate : candidates) {
            int parameterCount = candidate.getParameterCount();
            if (constructorToUse != null && argsToUse != null && argsToUse.length > parameterCount) {
                break;
            }
            if (parameterCount != minNrOfArgs) {
                continue;
            }

            Class[] parameterTypes = candidate.getParameterTypes();
            ArgumentsHolder argsHolder = new ArgumentsHolder(argsToUse);
            int typeDiffWeight = argsHolder.getTypeDifferenceWeight(parameterTypes);

            if (typeDiffWeight < minTypeDiffWeight) {
                //匹配的权值越低，越匹配
                constructorToUse = candidate;
                argsToUse = argsHolder.arguments;
                minTypeDiffWeight = typeDiffWeight;
                ambiguousConstructors = null;
            } else if (constructorToUse != null && typeDiffWeight == minTypeDiffWeight) {
                if (ambiguousConstructors == null) {
                    //不知道使用哪一个构造器，初始化模糊构造器列表
                    ambiguousConstructors = new LinkedHashSet<>();
                    //添加前一个匹配的构造器
                    ambiguousConstructors.add(constructorToUse);
                }
                //添加当前正在匹配的构造器
                ambiguousConstructors.add(candidate);
            }
        }
        if (constructorToUse == null) {
            throw new BeansException("Could not resolve matching constructor on bean class[" + beanDefinition.getBeanClassName() + "]" +
                    "(hint: specify index/type/name arguments for simple parameters to avoid type ambiguities)");
        }
        if (ambiguousConstructors != null) {
            throw new BeansException("Could not resolve matching constructor on bean class[" + beanDefinition.getBeanClassName() + "]" +
                    "(hint: specify index/type/name arguments for simple parameters to avoid type ambiguities)");
        }
        Object bean = instantiate(beanName, beanDefinition, constructorToUse, argsToUse);
        beanWrapper.setBeanInstance(bean);
        return beanWrapper;
    }

    private Object instantiate(String beanName, RootBeanDefinition beanDefinition, Constructor<?> constructorToUse, Object[] argsToUse) {
        try {
            InstantiationStrategy strategy = this.beanFactory.getInstantiationStrategy();
            return strategy.instantiate(beanDefinition, beanName, constructorToUse, argsToUse);
        } catch (Throwable ex) {
            throw new BeansException(ex.getMessage(),ex);
        }
    }

    private static class ArgumentsHolder {
        public final Object[] arguments;

        public ArgumentsHolder(Object[] args) {
            arguments = args;
        }

        /**
         * 获取参数的匹配程度
         *
         * @param parameterTypes
         * @return
         */
        public int getTypeDifferenceWeight(Class[] parameterTypes) {
            return MethodInvoker.getTypeDifferenceWeight(parameterTypes, arguments);
        }
    }
}

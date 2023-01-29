package com.learn.project.springframework.beans.factory.support;

import com.learn.project.springframework.beans.BeanWrapper;
import com.learn.project.springframework.beans.BeansException;
import com.learn.project.springframework.beans.MutablePropertyValues;
import com.learn.project.springframework.beans.PropertyValue;
import com.learn.project.springframework.beans.PropertyValues;
import com.learn.project.springframework.beans.factory.config.BeanPostProcessor;
import com.learn.project.springframework.util.Lists;

import java.util.Arrays;
import java.util.List;

/**
 * AbstractAutowireCapableBeanFactory
 *
 * @author chenfuyuan
 * @date 2023/1/7 16:41
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {

    private InstantiationStrategy instantiationStrategy = new CglibInstantiationStrategy();

    @Override
    protected Object createBean(String beanName, RootBeanDefinition mbd, Object[] args) {
        return doCreateBean(beanName, mbd, args);
    }

    protected Object doCreateBean(String beanName, RootBeanDefinition mbd, Object[] args) {
        //创建对象实例
        BeanWrapper instanceWrapper = createBeanInstance(mbd, beanName, args);
        //填充bean
        populateBean(beanName, mbd, instanceWrapper);
        Object bean = instanceWrapper.getWrappedInstance();
        bean = initializeBean(beanName, bean, mbd);
        //缓存实例化的单例bean
        addSingleton(beanName, bean);
        return bean;
    }

    private Object initializeBean(String beanName, Object bean, RootBeanDefinition mbd) {
        // 1. 执行 BeanPostProcessor Before 处理
        Object wrappedBean = applyBeanPostProcessorsBeforeInitialization(bean, beanName);

        // 执行 Bean 对象的初始化方法
        try {
            //TODO 待完成invokeInitMethods
            invokeInitMethods(beanName,wrappedBean, mbd);
        } catch (Exception e) {
            throw new BeansException("Invocation of init method of bean[" + beanName + "] failed", e);
        }

        // 2. 执行 BeanPostProcessor After 处理
        wrappedBean = applyBeanPostProcessorsAfterInitialization(wrappedBean, beanName);
        return wrappedBean;
    }

    private void invokeInitMethods(String beanName, Object bean, RootBeanDefinition mbd) throws Exception{

    }


    /**
     * 执行后置操作
     * @param existingBean
     * @param beanName
     * @return
     */
    private Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) {
        Object result = existingBean;
        for (BeanPostProcessor beanPostProcessor : getBeanPostProcessors()) {
            Object current = beanPostProcessor.postProcessBeforeInitialization(result, beanName);
            if (null == current) {
                return result;
            }
            result = current;
        }
        return result;
    }

    /**
     * 执行前置操作
     * @param existingBean
     * @param beanName
     * @return
     */
    private Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) {
        Object result = existingBean;
        for (BeanPostProcessor beanPostProcessor : getBeanPostProcessors()) {
            Object current = beanPostProcessor.postProcessAfterInitialization(result, beanName);
            if (null == current) {
                return result;
            }
            result = current;
        }
        return result;
    }

    /**
     * 填充Bean
     *
     * @param beanName
     * @param mbd
     * @param bean
     */
    private void populateBean(String beanName, RootBeanDefinition mbd, BeanWrapper bean) {
        if (bean == null) {
            if (mbd.hasPropertyValues()) {
                throw new BeansException("Cannot apply property values to null instance");
            } else {
                //SKip property population phase for null instance
                return;
            }
        }

        MutablePropertyValues pvs = mbd.hasPropertyValues() ? mbd.getPropertyValues() : null;

        if (pvs != null) {
            applyPropertyValues(beanName, mbd, bean, pvs);
        }
    }

    /**
     * 填充属性
     *
     * @param beanName
     * @param mbd
     * @param beanWrapper
     * @param pvs
     */
    private void applyPropertyValues(String beanName, RootBeanDefinition mbd, BeanWrapper beanWrapper, PropertyValues pvs) {
        if (pvs.isEmpty()) {
            return;
        }
        List<PropertyValue> original = pvs instanceof MutablePropertyValues mpvs ?
                mpvs.getPropertyValueList() : Arrays.asList(pvs.getPropertyValues());

        BeanDefinitionValueResolver valueResolver = new BeanDefinitionValueResolver(this, beanName, mbd);

        List<PropertyValue> deepCopy = Lists.newArrayList(original.size());
        for (PropertyValue pv : original) {
            String propertyName = pv.getName();
            Object originalValue = pv.getValue();
            Object resolvedValue = valueResolver.resolveValueIfNecessary(pv, originalValue);
            if (resolvedValue == originalValue) {
                deepCopy.add(pv);
            } else {
                deepCopy.add(new PropertyValue(propertyName, resolvedValue));
            }
        }
        beanWrapper.setPropertyValues(new MutablePropertyValues(deepCopy));
    }

    /**
     * 创建Bean对象
     *
     * @param beanDefinition
     * @param beanName
     * @param args
     * @return
     */
    protected BeanWrapper createBeanInstance(RootBeanDefinition beanDefinition, String beanName, Object[] args) {
        ConstructorResolver constructorResolver = new ConstructorResolver(this);
        //解析出对应的构造器
        return constructorResolver.autowireConstructor(beanName, beanDefinition, null, args);
    }


    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }
}

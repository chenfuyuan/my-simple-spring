package com.learn.project.springframework.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import com.learn.project.springframework.beans.BeanWrapper;
import com.learn.project.springframework.beans.BeansException;
import com.learn.project.springframework.beans.MutablePropertyValues;
import com.learn.project.springframework.beans.PropertyValue;
import com.learn.project.springframework.beans.PropertyValues;
import com.learn.project.springframework.beans.factory.config.BeanReference;
import com.learn.project.springframework.util.Lists;

import java.lang.reflect.Constructor;
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
        //缓存实例化的单例bean
        addSingleton(beanName, bean);
        return bean;
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

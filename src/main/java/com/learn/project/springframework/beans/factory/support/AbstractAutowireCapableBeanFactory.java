package com.learn.project.springframework.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import com.learn.project.springframework.beans.BeansException;
import com.learn.project.springframework.beans.MutablePropertyValues;
import com.learn.project.springframework.beans.PropertyValue;
import com.learn.project.springframework.beans.PropertyValues;
import com.learn.project.springframework.beans.factory.config.BeanReference;

import java.lang.reflect.Constructor;
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
    protected Object createBean(String beanName, RootBeanDefinition mbd,Object[] args) {
        return doCreateBean(beanName, mbd, args);
    }

    protected Object doCreateBean(String beanName, RootBeanDefinition mbd, Object[] args) {
        Object bean = null;
        try {
            bean = createBeanInstance(mbd, beanName, args);
            populateBean(beanName, mbd, bean);
        } catch (Exception e) {
            throw new BeansException("Instantiation of bean failed", e);
        }
        //缓存实例化的单例bean
        addSingleton(beanName,bean);
        return bean;
    }

    /**
     * 填充Bean
     * @param beanName
     * @param mbd
     * @param bean
     */
    private void populateBean(String beanName, RootBeanDefinition mbd, Object bean) {
        if (bean == null) {
            if (mbd.hasPropertyValues()) {
                throw new BeansException("Cannot apply property values to null instance");
            }else {
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
     * @param beanName
     * @param mbd
     * @param bean
     * @param pvs
     */
    private void applyPropertyValues(String beanName, RootBeanDefinition mbd, Object bean, PropertyValues pvs) {
        if (pvs.isEmpty()) {
            return;
        }
        try {
            for (PropertyValue propertyValue : pvs.getPropertyValues()) {
                String name = propertyValue.getName();
                Object value = propertyValue.getValue();
                if (value instanceof BeanReference) {
                    BeanReference beanReference = (BeanReference) value;
                    //获取对应的依赖bean
                    value = getBean(beanReference.getBeanName());
                }
                //借用hutool的BeanUtil设置参数
                BeanUtil.setFieldValue(bean, name, value);
            }
        } catch (Exception e) {
            throw new BeansException("Error setting property values: " + beanName,e);
        }
    }

    /**
     * 创建Bean对象
     * @param beanDefinition
     * @param beanName
     * @param args
     * @return
     */
    protected Object createBeanInstance(RootBeanDefinition beanDefinition, String beanName, Object[] args) {
        ConstructorResolver constructorResolver = new ConstructorResolver(this);
        //解析出对应的构造器
        Constructor constructorToUse = constructorResolver.autowireConstructor(beanName, beanDefinition, null, args);
        return instantiationStrategy.instantiate(beanDefinition, beanName, constructorToUse, args);
    }
}

package com.learn.project.springframework.beans.factory;

import com.learn.project.springframework.beans.BeansException;
import com.learn.project.springframework.beans.MutablePropertyValues;
import com.learn.project.springframework.beans.PropertyValue;
import com.learn.project.springframework.beans.factory.config.BeanDefinition;
import com.learn.project.springframework.beans.factory.config.BeanFactoryPostProcessor;
import com.learn.project.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import com.learn.project.springframework.core.io.DefaultResourceLoader;
import com.learn.project.springframework.core.io.Resource;

import java.util.Properties;

/**
 * PropertyPlaceholderConfigurer
 *
 * @author chenfuyuan
 * @date 2023/2/21 14:43
 */
public class PropertyPlaceholderConfigurer implements BeanFactoryPostProcessor {

    public static final String DEFAULT_PLACEHOLDER_PREFIX = "${";

    public static final String DEFAULT_PLACEHOLDER_SUFFIX = "}";

    private String location;

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        //加载属性文件
        try {
            DefaultResourceLoader loader = new DefaultResourceLoader();
            Resource resource = loader.getResource(location);
            Properties properties = new Properties();
            properties.load(resource.getInputStream());
            for (BeanDefinition beanDefinition : beanFactory.getBeanDefinitions()) {
                MutablePropertyValues propertyValues = beanDefinition.getPropertyValues();
                for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {
                    Object value = propertyValue.getValue();
                    if (!(value instanceof String)) {
                        continue;
                    }
                    String strValue = (String) value;
                    StringBuilder strBuffer = new StringBuilder(strValue);
                    int startIndex = strValue.indexOf(DEFAULT_PLACEHOLDER_PREFIX);
                    int stopIndex = strValue.indexOf(DEFAULT_PLACEHOLDER_SUFFIX);
                    if (startIndex != -1 && stopIndex != -1 && startIndex < stopIndex) {
                        String propKey = strValue.substring(startIndex + 2, stopIndex);
                        String proValue = properties.getProperty(propKey);
                        strBuffer.replace(startIndex, stopIndex + 1, proValue);
                        propertyValues.addPropertyValue(new PropertyValue(propertyValue.getName(), strBuffer.toString()));
                    }
                }
            }
        } catch (Exception e) {
            throw new BeansException("Could not load properties");
        }
    }

    public void setLocation(String location) {
        this.location = location;
    }
}

package com.learn.project.springframework.context.support;

import com.learn.project.springframework.beans.BeansException;
import com.learn.project.springframework.beans.factory.config.BeanFactoryPostProcessor;
import com.learn.project.springframework.beans.factory.config.BeanPostProcessor;
import com.learn.project.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import com.learn.project.springframework.context.ApplicationEvent;
import com.learn.project.springframework.context.ApplicationListener;
import com.learn.project.springframework.context.ConfigurableApplicationContext;
import com.learn.project.springframework.context.event.ApplicationEventMulticaster;
import com.learn.project.springframework.context.event.ContextClosedEvent;
import com.learn.project.springframework.context.event.ContextRefreshedEvent;
import com.learn.project.springframework.context.event.SimpleApplicationEventMulticaster;
import com.learn.project.springframework.core.io.DefaultResourceLoader;
import com.learn.project.springframework.core.io.Resource;

import java.util.Collection;
import java.util.Map;

/**
 * AbstractApplicationContext
 *
 * @author chenfuyuan
 * @date 2023/1/29 23:01
 */
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {

    public static final String APPLICATION_EVENT_MULTICASTER_BEAN_NAME = "applicationEventMulticaster";

    private ApplicationEventMulticaster applicationEventMulticaster;


    @Override
    public void refresh() throws BeansException {
        //1. 创建BeanFactory 并加载BeanDefinition
        refreshBeanFactory();
        //2. 获取BeanFactory
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();
        //3. 添加ApplicationContextAwareProcessor，让继承自ApplicationContextAware的对象可以感知到所属的ApplicationContext
        beanFactory.addBeanPostProcessor(new ApplicationContextAwareProcessor(this));
        //4. 在Bean实例化之前，执行BeanFactoryPostProcessor
        invokeBeanFactoryPostProcessors(beanFactory);
        //5. BeanPostProcessor 需要提前于其他Bean对象实例化之前执行注册操作
        registryBeanPostProcessors(beanFactory);
        //6. 初始化事件发布者
        initApplicationEventMulticaster();
        //7. 注册事件监听器
        registerListeners();
        //8. 提前实例化单例Bean对象
        beanFactory.preInstantiateSingletons();
        //9. 发布容器刷新完成事件
        finishRefresh();
    }

    private void finishRefresh() {
        publishEvent(new ContextRefreshedEvent(this));
    }

    @Override
    public void publishEvent(ApplicationEvent event) {
        applicationEventMulticaster.multicastEvent(event);
    }

    private void registerListeners() {
        Collection<ApplicationListener> applicationListeners = getBeansOfType(ApplicationListener.class).values();
        for (ApplicationListener applicationListener : applicationListeners) {
            applicationEventMulticaster.addApplicationListener(applicationListener);
        }
    }

    private void initApplicationEventMulticaster() {
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();
        applicationEventMulticaster = new SimpleApplicationEventMulticaster(beanFactory);
        beanFactory.registerSingleton(APPLICATION_EVENT_MULTICASTER_BEAN_NAME, applicationEventMulticaster);
    }

    @Override
    public void registerShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(this::close));
    }

    @Override
    public void close() {
        // 发布容器关闭事件
        publishEvent(new ContextClosedEvent(this));

        //执行销毁单例Bean的销毁方法
        getBeanFactory().destroySingletons();
    }



    private void registryBeanPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanPostProcessor> beanPostProcessorMap = beanFactory.getBeansOfType(BeanPostProcessor.class);
        for (BeanPostProcessor beanPostProcessor : beanPostProcessorMap.values()) {
            //为factory添加beanPostProcessor
            beanFactory.addBeanPostProcessor(beanPostProcessor);
        }
    }

    private void invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanFactoryPostProcessor> beanFactoryPostProcessorMap = beanFactory.getBeansOfType(BeanFactoryPostProcessor.class);
        for (BeanFactoryPostProcessor beanFactoryPostProcessor : beanFactoryPostProcessorMap.values()) {
            beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
        }
    }

    /**
     * 获取BeanFactory
     * @return
     */
    protected abstract ConfigurableListableBeanFactory getBeanFactory();

    /**
     * 创建BeanFactory并加载BeanDefinition
     * @throws BeansException
     */
    protected abstract void refreshBeanFactory() throws BeansException;

    @Override
    public Object getBean(String beanName) {
        return getBeanFactory().getBean(beanName);
    }

    @Override
    public Object getBean(String beanName, Object... args) {
        return getBeanFactory().getBean(beanName,args);
    }

    @Override
    public <T> T getBean(String beanName, Class<T> beanType) {
        return getBeanFactory().getBean(beanName,beanType);
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) {
        return getBeanFactory().getBeansOfType(type);
    }
}

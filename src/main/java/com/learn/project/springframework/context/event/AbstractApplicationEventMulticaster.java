package com.learn.project.springframework.context.event;

import com.learn.project.springframework.beans.BeansException;
import com.learn.project.springframework.beans.factory.BeanFactory;
import com.learn.project.springframework.beans.factory.BeanFactoryAware;
import com.learn.project.springframework.context.ApplicationEvent;
import com.learn.project.springframework.context.ApplicationListener;
import com.learn.project.springframework.util.ClassUtils;

import javax.swing.plaf.ActionMapUIResource;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * AbstractApplicationEventMulticaster
 *
 * @author chenfuyuan
 * @date 2023/2/8 17:08
 */
public abstract class AbstractApplicationEventMulticaster implements ApplicationEventMulticaster, BeanFactoryAware {
    private BeanFactory beanFactory;

    private final Set<ApplicationListener<ApplicationEvent>> applicationListeners = new LinkedHashSet<>();
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void addApplicationListener(ApplicationListener<ApplicationEvent> listener) {
        applicationListeners.add(listener);
    }

    @Override
    public void removeApplicationListener(ApplicationListener<ApplicationEvent> listener) {
        applicationListeners.remove(listener);
    }

    protected Collection<ApplicationListener> getApplicationListeners(ApplicationEvent event) {
        LinkedList<ApplicationListener> allListeners = new LinkedList<>();
        for (ApplicationListener<ApplicationEvent> listener : applicationListeners) {
            if (supportsEvent(listener,event)) {
                allListeners.add(listener);
            }
        }
        return allListeners;
    }

    private boolean supportsEvent(ApplicationListener<ApplicationEvent> listener, ApplicationEvent event) {
        Class<? extends ApplicationListener> listenerClass = listener.getClass();

        //按照CglibSubclassingInstantiationStrategy.SimpleInstantiationStrategy 不同的实例化类型，需要判断后获取目标class
        Class<?> targetClass = ClassUtils.isCglibProxyClass(listenerClass) ? listenerClass.getSuperclass() : listenerClass;
        Type genericInterface = targetClass.getGenericInterfaces()[0];

        Type actualTypeArgument = ((ParameterizedType) genericInterface).getActualTypeArguments()[0];

        String className = actualTypeArgument.getTypeName();

        Class<?> eventClassName;
        try {
            eventClassName = Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new BeansException("wrong event class name: " + className);
        }
        return eventClassName.isAssignableFrom(event.getClass());
    }

}

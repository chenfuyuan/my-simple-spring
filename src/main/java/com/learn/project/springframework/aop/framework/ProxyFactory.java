package com.learn.project.springframework.aop.framework;

import com.learn.project.springframework.aop.AdvisedSupport;

/**
 * ProxyFactory
 *
 * @author chenfuyuan
 * @date 2023/2/16 14:44
 */
public class ProxyFactory {

    private AdvisedSupport advisedSupport;

    public ProxyFactory(AdvisedSupport advisedSupport) {
        this.advisedSupport = advisedSupport;
    }

    public Object getProxy() {
        return createAopProxy().getProxy();
    }

    private AopProxy createAopProxy() {
        if (advisedSupport.isProxyTargetClass()) {
            return new Cglib2AopProxy(advisedSupport);
        }

        return new JdkDynamicAopProxy(advisedSupport);
    }


}

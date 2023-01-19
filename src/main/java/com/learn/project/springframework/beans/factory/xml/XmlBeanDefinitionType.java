package com.learn.project.springframework.beans.factory.xml;

import org.w3c.dom.Element;
import org.w3c.dom.Node;


/**
 * XmlBeanDefinitionType
 *
 * @author chenfuyuan
 * @date 2023/1/19 11:06
 */
public enum XmlBeanDefinitionType {

    BEAN("bean", Element.class),
    PROPERTY("property",Element.class);
    /**
     * 节点名称
     */
    private String nodeName;

    private Class<? extends Node> nodeType;

    XmlBeanDefinitionType(String nodeName, Class<? extends Node> nodeType) {
        this.nodeName = nodeName;
        this.nodeType = nodeType;
    }

    public String getNodeName() {
        return nodeName;
    }

    public Class<? extends Node> getNodeType() {
        return nodeType;
    }
}

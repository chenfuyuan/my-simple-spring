package com.learn.project.springframework.beans.factory.xml;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.XmlUtil;
import com.learn.project.springframework.beans.BeansException;
import com.learn.project.springframework.beans.PropertyValue;
import com.learn.project.springframework.beans.factory.config.BeanDefinition;
import com.learn.project.springframework.beans.factory.config.RuntimeBeanReference;
import com.learn.project.springframework.beans.factory.support.AbstractBeanDefinitionReader;
import com.learn.project.springframework.beans.factory.support.BeanDefinitionRegistry;
import com.learn.project.springframework.beans.factory.support.RootBeanDefinition;
import com.learn.project.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import com.learn.project.springframework.core.io.Resource;
import com.learn.project.springframework.core.io.ResourceLoader;
import com.learn.project.springframework.util.StringUtils;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.InputStream;

/**
 * XmlBeanDefinitionReader
 *
 * @author chenfuyuan
 * @date 2023/1/19 10:01
 */
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {


    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
        super(registry);
    }

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        super(registry, resourceLoader);
    }

    @Override
    public void loadBeanDefinitions(Resource resource) {
        try (InputStream inputStream = resource.getInputStream()) {
            doLoadBeanDefinition(inputStream);
        } catch (IOException | ClassNotFoundException e) {
            throw new BeansException("IOException parsing XML document from " + resource, e);
        }
    }

    @Override
    public void loadBeanDefinitions(Resource... resources) {
        for (Resource resource : resources) {
            loadBeanDefinitions(resource);
        }
    }

    @Override
    public void loadBeanDefinitions(String location) {
        ResourceLoader resourceLoader = getResourceLoader();
        Resource resource = resourceLoader.getResource(location);
        loadBeanDefinitions(resource);
    }

    @Override
    public void loadBeanDefinitions(String... locations) throws BeansException {
        for (String location : locations) {
            loadBeanDefinitions(location);
        }
    }

    protected void doLoadBeanDefinition(InputStream inputStream) throws ClassNotFoundException {
        Document document = doLoadDocument(inputStream);
        Element root = document.getDocumentElement();
        NodeList childNodes = root.getChildNodes();


        for (int i = 0,length = childNodes.getLength(); i < length; i++) {
            Node node = childNodes.item(i);
            if (!checkXmlNode(node, XmlBeanDefinitionType.BEAN)) {
                if (checkXmlNode(node, XmlBeanDefinitionType.COMPONENT_SCAN)) {
                    Element componentScanNode = (Element) node;
                    String scanPath = componentScanNode.getAttribute("base-package");
                    if (StringUtils.isEmpty(scanPath)) {
                        throw new BeansException("The value of base-package attribute can not be empty or null");
                    }
                    scanPackage(scanPath);
                }
                continue;
            }
            //??????bean??????
            Element bean = (Element) node;
            String id = bean.getAttribute("id");
            String name = bean.getAttribute("name");
            String className = bean.getAttribute("class");

            // ?????????init-method???destroy-method???????????????
            String initMethodName = bean.getAttribute("init-method");
            String destroyMethodName = bean.getAttribute("destroy-method");

            //?????????scope?????????
            String beanScope = bean.getAttribute("scope");

            //??????Class
            Class<?> clazz = Class.forName(className);
            // ??????BeanName
            String beanName = StrUtil.isNotEmpty(id) ? id : name;
            if (StrUtil.isEmpty(beanName)) {
                beanName = StrUtil.lowerFirst(clazz.getSimpleName());
            }
            //??????Bean
            RootBeanDefinition beanDefinition = new RootBeanDefinition(clazz);
            //??????initMethod???destroyMethod
            beanDefinition.setInitMethodName(initMethodName);
            beanDefinition.setDestroyMethodName(destroyMethodName);

            //scope
            if (StringUtils.isNotEmpty(beanScope)) {
                beanDefinition.setScope(beanScope);
            }

            //?????????????????????
            NodeList beanConfigNodeList = bean.getChildNodes();
            int beanConfigNodeNum = beanConfigNodeList.getLength();
            for (int j = 0; j < beanConfigNodeNum; j++) {
                Node beanConfigNode = beanConfigNodeList.item(j);
                if (!checkXmlNode(beanConfigNode, XmlBeanDefinitionType.PROPERTY)) {
                    continue;
                }
                //??????property??????
                Element property = (Element) beanConfigNode;
                String attrName = property.getAttribute("name");
                String attrValue = property.getAttribute("value");
                String attrRef = property.getAttribute("ref");
                // ??????????????????????????????????????????
                Object value = StrUtil.isNotEmpty(attrRef) ? new RuntimeBeanReference(attrRef) : attrValue;
                // ???????????????
                PropertyValue propertyValue = new PropertyValue(attrName, value);
                beanDefinition.getPropertyValues().addPropertyValue(propertyValue);
            }
            if (getRegister().containsBeanDefinition(beanName)) {
                throw new BeansException("Duplicate beanName [" + beanName + "] is not allowed");
            }
            getRegister().registerBeanDefinition(beanName, beanDefinition);
        }
    }

    private void scanPackage(String scanPath) {
        String[] basePackages = StringUtils.splitToArray(scanPath, ",");
        if (basePackages == null || basePackages.length == 0) {
            return;
        }
        ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(getRegister());
        scanner.scan(basePackages);
    }

    private Document doLoadDocument(InputStream inputStream) throws ClassNotFoundException {
        Document document = XmlUtil.readXML(inputStream);
        return document;
    }

    private boolean checkXmlNode(Node node, XmlBeanDefinitionType nodeType) {
        if (!(node instanceof Element)) {
            return false;
        }
        if (!nodeType.getNodeName().equals(node.getNodeName())) {
            return false;
        }
        return true;
    }
}

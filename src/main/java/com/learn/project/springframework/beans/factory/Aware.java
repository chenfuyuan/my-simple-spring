package com.learn.project.springframework.beans.factory;

/**
 * Aware
 * 标记类接口，实现该接口可以被Spring容器感知
 *
 * 在Spring中有特别多类似这样的标记接口的设计方式，它们的存在就像是一种标签一样，可以方便统一摘取处属于此类接口的实现类，通常会有instanceof一起判断使用。
 * @author chenfuyuan
 * @date 2023/2/6 11:20
 */
public interface Aware {

}

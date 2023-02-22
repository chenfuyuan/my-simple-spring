package com.learn.project.springframework.core.collection;

import cn.hutool.core.collection.IterableIter;

import java.io.Serializable;
import java.util.Enumeration;

/**
 * EnumerationIter
 *
 * @author chenfuyuan
 * @date 2023/2/22 14:33
 */
public class EnumerationIter<E> implements IterableIter<E>, Serializable {
    private static final long serialVersionUID = 1L;
    private final Enumeration<E> e;

    public EnumerationIter(Enumeration<E> enumeration) {
        this.e = enumeration;
    }

    public boolean hasNext() {
        return this.e.hasMoreElements();
    }

    public E next() {
        return this.e.nextElement();
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }
}

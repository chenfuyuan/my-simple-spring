package com.learn.project.springframework.util;

import java.util.ArrayList;

/**
 * Lists
 *
 * @author chenfuyuan
 * @date 2023/1/14 13:10
 */
public abstract class Lists {


    public static ArrayList<?> newArrayList() {
        return new ArrayList<>();
    }

    public static <T> ArrayList<T> newArrayList(int capacity) {
        return new ArrayList<>(capacity);
    }
}

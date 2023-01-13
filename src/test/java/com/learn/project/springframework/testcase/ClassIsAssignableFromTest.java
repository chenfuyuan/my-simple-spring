package com.learn.project.springframework.testcase;

import org.junit.jupiter.api.Test;

/**
 * ClassIsAssignableFromTest
 *
 * @author chenfuyuan
 * @date 2023/1/13 15:15
 */
public class ClassIsAssignableFromTest {

    @Test
    public void test() {
        A a = new A();
        B b = new B();
        B b1 = new B();
        C c = new C();
        System.out.println(a.getClass().isAssignableFrom(a.getClass()));
        System.out.println(a.getClass().isAssignableFrom(b.getClass()));
        System.out.println(a.getClass().isAssignableFrom(c.getClass()));
        System.out.println(b1.getClass().isAssignableFrom(b.getClass()));

        System.out.println(b.getClass().isAssignableFrom(c.getClass()));

        System.out.println("=====================================");
        System.out.println(A.class.isAssignableFrom(a.getClass()));
        System.out.println(A.class.isAssignableFrom(b.getClass()));
        System.out.println(A.class.isAssignableFrom(c.getClass()));

        System.out.println("=====================================");
        System.out.println(Object.class.isAssignableFrom(a.getClass()));
        System.out.println(Object.class.isAssignableFrom(String.class));
        //String不是Object的父类，所以返回false
        System.out.println(String.class.isAssignableFrom(Object.class));
        //B不是A的父类，所以也返回false
        System.out.println(b.getClass().isAssignableFrom(a.getClass()));
    }
}

class A{
}
class B extends A{
}
class C extends B{
}

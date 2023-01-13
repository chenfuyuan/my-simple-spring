package com.learn.project.springframework.util;

/**
 * MethodInvoker
 *
 * @author chenfuyuan
 * @date 2023/1/13 14:53
 */
public class MethodInvoker {

    /**
     * 获取类型匹配程度
     * @param paramTypes 类型列表
     * @param args 对应的参数值
     * @return
     */
    public static int getTypeDifferenceWeight(Class<?>[] paramTypes, Object[] args) {
        int result = 0;
        for (int i = 0; i < paramTypes.length; i++) {
            if (!ClassUtils.isAssignableValue(paramTypes[i], args[i])) {
                return Integer.MAX_VALUE;
            }
            if (args[i] != null) {
                //比较父类
                Class<?> paramType = paramTypes[i];
                Class<?> superclass = args[i].getClass().getSuperclass();
                while (superclass != null) {
                    if (paramType.equals(superclass)) {
                        result += 2;
                        superclass = null;
                    } else if (ClassUtils.isAssignableValue(paramType, superclass)) {
                        result += 2;
                        superclass = superclass.getSuperclass();
                    } else {
                        superclass = null;
                    }
                }
                if (paramType.isInterface()) {
                    result += 1;
                }
            }
        }
        return result;
    }
}

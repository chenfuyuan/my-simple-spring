package com.learn.project.springframework.core.lang;

import cn.hutool.core.lang.Filter;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import com.learn.project.springframework.beans.BeansException;
import com.learn.project.springframework.util.CharsetUtils;
import com.learn.project.springframework.util.ClassUtils;
import com.learn.project.springframework.util.ResourceUtils;

import java.io.File;
import java.lang.annotation.Annotation;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * ClassScanner
 *
 * @author chenfuyuan
 * @date 2023/2/22 14:13
 */
public class ClassScanner {

    private final String packageName;

    private final String packagePath;

    private final Filter<Class<?>> classFilter;

    private final Charset charset;

    private final String packageDirName;

    private final String packageNameWithDot;

    private final Set<Class<?>> classes;

    private final ClassLoader classLoader;

    public ClassScanner(String packageName, Filter<Class<?>> classFilter) {
        this(packageName, classFilter, CharsetUtils.CHARSET_UTF_8);
    }

    public ClassScanner(String packageName, Filter<Class<?>> classFilter, Charset charset) {
        this.packageName = packageName;
        this.classFilter =classFilter;
        this.packagePath = packageName.replace('.','/');
        this.charset = charset;
        this.packageDirName = packageName.replace('.', File.separatorChar);
        this.packageNameWithDot = StrUtil.addSuffixIfNot(packageName, ".");
        this.classes = new HashSet<>();
        this.classLoader = ClassUtils.getDefaultClassLoader();
    }

    private Set<Class<?>> scan() {
        //获取该包路径下的所有类
        Iterator<URL> iterator = ResourceUtils.getResourceIter(packagePath).iterator();
        while (iterator.hasNext()) {
            URL url = iterator.next();
            switch (url.getProtocol()) {
                case "file" :
                    this.scanFile(new File(URLUtil.decode(url.getFile(),this.charset)),null);
            }
        }
        return classes;
    }

    private void scanFile(File file,String rootDir) {
        if (file.isFile()) {
            // 出
            String fileName = file.getAbsolutePath();
            if (fileName.endsWith(".class")) {
                String className = fileName.substring(rootDir.length(), fileName.length() - 6).replace(File.separatorChar, '.');
                this.addIfAccept(className);
            }
        } else if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (null != files) {
                File[] var10 = files;
                int var5 = files.length;

                for(int var6 = 0; var6 < var5; ++var6) {
                    File subFile = var10[var6];
                    this.scanFile(subFile, null == rootDir ? this.subPathBeforePackage(file) : rootDir);
                }
            }
        }
    }

    private void addIfAccept(String className) {
        if (!StrUtil.isBlank(className)) {
            int classLen = className.length();
            int packageLen = this.packageName.length();
            if (classLen == packageLen) {
                if (className.equals(this.packageName)) {
                    this.addIfAccept(this.loadClass(className));
                }
            } else if (classLen > packageLen && (".".equals(this.packageNameWithDot) || className.startsWith(this.packageNameWithDot))) {
                this.addIfAccept(this.loadClass(className));
            }

        }
    }


    protected Class<?> loadClass(String className) {
        ClassLoader loader = this.classLoader;
        Class<?> clazz = null;
        try {
            clazz = Class.forName(className, false, loader);
        } catch (Exception e) {
            throw new BeansException("load class error.");
        }
        return clazz;
    }

    private void addIfAccept(Class<?> clazz) {
        if (null != clazz) {
            Filter<Class<?>> classFilter = this.classFilter;
            if (classFilter == null || classFilter.accept(clazz)) {
                this.classes.add(clazz);
            }
        }

    }

    private String subPathBeforePackage(File file) {
        String filePath = file.getAbsolutePath();
        if (StrUtil.isNotEmpty(this.packageDirName)) {
            filePath = StrUtil.subBefore(filePath, this.packageDirName, true);
        }
        return StrUtil.addSuffixIfNot(filePath, File.separator);
    }

    public static Set<Class<?>> scanPackageByAnnotation(String packageName, Class<? extends Annotation> annotationClass) {
        return scanPackage(packageName, (clazz) -> {
            return clazz.isAnnotationPresent(annotationClass);
        });
    }

    public static Set<Class<?>> scanPackage(String packageName, Filter<Class<?>> classFilter) {
        return (new ClassScanner(packageName, classFilter)).scan();
    }
}

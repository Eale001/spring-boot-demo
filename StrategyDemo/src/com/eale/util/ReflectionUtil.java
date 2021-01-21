package com.eale.util;


import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @Author Admin
 * @Date 2020/7/2
 * @Description 反射工具
 * @Version 1.0
 **/
public class ReflectionUtil {

    /**
     * 定义集合类（用于存放所以的加载类）
     */

    private static String packageName;

    private static Set<Class<?>> CLAZZ_SET;

    static {
        // 指定加载路径
        CLAZZ_SET = getClassSet("com.eale");
    }


    /**
     * 获取类加载器
     * @return
     */
    public static ClassLoader getClassLoader(){
        return Thread.currentThread().getContextClassLoader();
    }

    /**
     * 加载类
     * @param className 类全限定名称
     * @param isInitialized 是否在加载完成后执行静态代码块
     * @return
     */
    public static Class<?> loadClass(String className,boolean isInitialized){
        Class<?> cls;
        try {
            cls = Class.forName(className,isInitialized,getClassLoader());
        } catch (ClassNotFoundException e) {
            throw new  RuntimeException(e);
        }
        return cls;
    }

    public static Class<?> loadClass(String className){
        return loadClass(className,true);
    }

    /**
     *
     * @param packageName
     * @return
     */
    private static Set<Class<?>> getClassSet(String packageName) {
        Set<Class<?>> classSet = new HashSet<>();
        try {
            Enumeration<URL> urls = getClassLoader().getResources(packageName.replace(".","/" ));
            while (urls.hasMoreElements()){
                URL url = urls.nextElement();
                if (url != null) {
                    String protocol = url.getProtocol();
                    if (Objects.equals(protocol,"file" )){
                        String packagePath = url.getPath().replace("20%", "");
                        addClass(classSet,packagePath,packageName);

                    }else if (Objects.equals(protocol, "jar")){
                        JarURLConnection jarURLConnection = (JarURLConnection) url.openConnection();
                        if (jarURLConnection != null) {
                            JarFile jarFile = jarURLConnection.getJarFile();
                            if (jarFile != null) {
                                Enumeration<JarEntry> entries = jarFile.entries();
                                while (entries.hasMoreElements()){
                                    JarEntry jarEntry = entries.nextElement();
                                    String jarEntryName = jarEntry.getName();
                                    if (jarEntryName.endsWith(".class")){
                                        String className = jarEntryName.substring(0, jarEntryName.lastIndexOf(".")).replace("/", ".");
                                        doAddClass(classSet,className);
                                    }

                                }
                            }
                        }
                    }

                }

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return classSet;
    }

    private static void doAddClass(Set<Class<?>> classSet, String className) {
        Class<?> cls = loadClass(className);
        classSet.add(cls);
    }


    private static void addClass(Set<Class<?>> classSet, String packagePath, String packageName) {
        final File[] files = new File(packagePath).listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return (file.isFile() && file.getName().endsWith(".class")) || file.isDirectory();
            }
        });
        for (File file : files) {
            String fileName = file.getName();
            if (file.isFile()){
                String className = fileName.substring(0, fileName.lastIndexOf("."));
                if (!Objects.isNull(packageName)){
                    className = packageName+"."+className;
                }
                doAddClass(classSet, className);
            }else {
                String subPackagePath = fileName;
                if (!Objects.isNull(subPackagePath)){
                    subPackagePath = packagePath + "/" +subPackagePath;
                }
                String subPackageName = fileName;
                if (!Objects.isNull(subPackageName)){
                    subPackageName = packageName + "." + subPackageName;
                }
                addClass(classSet, subPackagePath, subPackageName);
            }
        }
    }


    public static Set<Class<?>> getClassSet(){
        return CLAZZ_SET;
    }

    /**
     * 获取应用包名下某父类（或接口）的所有子类
     * @param superClass
     * @return
     */
    public static Set<Class<?>> getClazzSetBySuper(Class<?> superClass){
        Set<Class<?>> classSet = new HashSet<>();
        for (Class<?> cls : CLAZZ_SET) {
            if (superClass.isAssignableFrom(cls) && !superClass.equals(cls)){
                classSet.add(cls);
            }
        }
        return classSet;
    }

    /**
     * 获取应用包名下带有某注解的类
     * @param annotationClass
     * @return
     */
    public static Set<Class<?>> getClazzSetByAnnotation(Class<? extends Annotation> annotationClass){
        Set<Class<?>> classSet = new HashSet<>();
        for (Class<?> cls : CLAZZ_SET) {
            if (cls.isAnnotationPresent(annotationClass)){
                classSet.add(cls);
            }
        }
        return classSet;
    }

}

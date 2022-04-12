package com.atguigu.boot.utils.bean;

import org.springframework.cglib.beans.BeanCopier;
import org.springframework.cglib.core.Converter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

/**
 * Bean 工具类
 *
 * @author
 */
public class BeanUtils extends org.springframework.beans.BeanUtils {
    private static final Map<String, BeanCopier> BEAN_COPIERS_CACHE = new HashMap<>();

//    Student student = new Student();
//    net.sf.cglib.beans.BeanCopier beanCopier = BeanCopier.create(studentDto.getClass(), student.getClass(), false);
//        beanCopier.copy(studentDto,studentDto,null);
//
    public static void copy(Object source, Object target) {
        copy(source, target, false, null);
    }

    public static void copy(Object source, Object target, boolean useConverter, Converter converter) {
        BeanCopier copier = getBeanCopier(source, target, useConverter);
        copier.copy(source, target, converter);
    }

    public static BeanCopier getBeanCopier(Object source, Object target, boolean useConverter) {
        String key = getKey(source.getClass(), target.getClass(), useConverter);
        if (BEAN_COPIERS_CACHE.containsKey(key)) {
            return BEAN_COPIERS_CACHE.get(key);
        }
        BeanCopier copier = BeanCopier.create(source.getClass(), target.getClass(), useConverter);
        BEAN_COPIERS_CACHE.put(key, copier);
        return copier;
    }

    private static String getKey(Class<?> sourceClazz, Class<?> targetClazz, boolean useConverter) {
        return sourceClazz.getName() + targetClazz.getName() + useConverter;
    }


    /**
     * 转换对象
     *
     * @param sources        源对象list
     * @param targetSupplier 目标对象供应方
     * @param <S>            源对象类型
     * @param <T>            目标对象类型
     * @return 目标对象list
     */
    public static <S, T> List<T> convertListTo(List<S> sources, Supplier<T> targetSupplier) {
        if (null == sources || null == targetSupplier) {
            return null;
        }
        List<T> list = new ArrayList<>(sources.size());
        for (S source : sources) {
            T target = targetSupplier.get();
            copyProperties(source, target);
            list.add(target);
        }
        return list;
    }

}

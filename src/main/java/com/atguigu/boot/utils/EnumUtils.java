package com.atguigu.boot.utils;


import com.atguigu.boot.enums.BaseEnum;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lingo
 */
public final class EnumUtils {

    /**
     * 将enum转换为list
     *
     * @param enumType
     * @param <T>
     * @return
     */
   public static <T extends BaseEnum> List<Map<String, Object>> enum2List(Class<? extends BaseEnum> enumType) {
        if (enumType == null) {
            return null;
        }
        T[] enumConstants = (T[]) enumType.getEnumConstants();
        if (enumConstants == null) {
            return null;
        }
        ArrayList<Map<String, Object>> results = new ArrayList<>();
        for (T bean : enumConstants) {
            String value = bean.getValue();
            Integer code = bean.getCode();
            HashMap<String, Object> map = new HashMap<>();
            map.put("code", code);
            map.put("value", value);
            results.add(map);
        }
        return results;
    }
    /**
     * 判断枚举值是否存在于指定枚举数组中
     * @param enums  枚举数组
     * @param value  枚举值
     * @return
     */
    public static boolean isExist(BaseEnum[] enums, Integer value) {
        if (value == null) {
            return false;
        }
        for (BaseEnum e : enums) {
            if (value.equals(e.getCode())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 根据value获取其对应的code
     * @param enums
     * @param value
     * @urn code
     */
    public static Integer getCodeByValue(BaseEnum[] enums, String  value) {
        if (StringUtils.isEmpty(value)) {
            return null;
        }
        for (BaseEnum e : enums) {
            if (value.equals(e.getValue())) {
                return e.getCode();
            }
        }
        return null;
    }

    /**
     * 根据code获取其对应的value
     * @param enums
     * @param code
     * @urn value
     */
    public static String getCodeByValue(BaseEnum[] enums, Integer  code) {
        if (code==null) {
            return "";
        }
        for (BaseEnum e : enums) {
            if (code.equals(e.getCode())) {
                return e.getValue();
            }
        }
        return null;
    }
}
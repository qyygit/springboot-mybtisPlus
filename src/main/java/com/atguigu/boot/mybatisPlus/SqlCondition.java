package com.atguigu.boot.mybatisPlus;

public class SqlCondition {
    //下面的字符串中, %s 是占位符, 第一个 %s 是列名, 第二个 %s 是列的值
    public static final String EQUAL = "%s=#{%s}";
    public static final String NOT_EQUAL = "%s&lt;&gt;#{%s}";
    public static final String LIKE = "%s LIKE CONCAT('%%',#{%s},'%%')";
    public static final String LIKE_LEFT = "%s LIKE CONCAT('%%',#{%s})";
    public static final String LIKE_RIGHT = "%s LIKE CONCAT(#{%s},'%%')";
}

package com.atguigu.boot.constants;

/**
 * @Classname SystemConstants
 * @Description 系统的常量
 * @Date 2021/7/30 9:59
 * @Created by guolin
 */
public class SystemConstants {

    public static final String AUTHORIZE_TOKEN = "token";
    public static final String AUTHORIZE_UUID = "uuid";

    //默认密码 6个 1
    public static final String DEFAULT_PWD = "111111";

    /**
     * 功能类型
     */
    public static final String FUN_TYPE_ADD = "ADD";
    public static final String FUN_TYPE_UPDATE = "UPDATE";

    //读取文件,开始页码
    public static final Integer ROW_NUMBER_BEGIN = 2;

    /**
     * UTF-8 字符集
     */
    public static final String UTF8 = "UTF-8";

    /**
     * 英文
     */
    public static final String LANGUAGE_EN = "en";

    /**
     * 中文
     */
    public static final String LANGUAGE_ZH = "zh";

    /**
     * city 省市区
     */
    public static final String CITY_INIT = "city";

    /**
     * 资源映射路径 前缀
     */
    public static final String RESOURCE_PREFIX = "/profile";

    /**
     * 导出最大条数
     */
    public static final Integer EXCEL_EXPORT_MAX_COUNT = 5000;

    /**
     *  登录请求过滤token
     */
    public static final String AUTH_LOGIN = "/auth/login";
}

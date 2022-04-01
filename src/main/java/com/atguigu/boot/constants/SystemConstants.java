package com.atguigu.boot.constants;

/**
 * @Classname SystemConstants
 * @Description 系统的常量
 * @Date 2021/7/30 9:59
 * @Created by guolin
 */
public class SystemConstants {

    public static final  String DEVICE_SUPPLIER  ="北京休恩博得科技股份有限公司";
    public static final  String REDIS_USER_MEUN ="system:user:menu";
    public static final  String REDIS_USER_ROLE ="system:user:role";
    public static final String AUTHORIZE_TOKEN = "token";
    public static final String AUTHORIZE_UUID = "uuid";
    public static final String AUTHORIZE_SIMI = "simi";
    public static final String START_TIME = " 00:00:00";
    public static final String END_TIME = " 23:59:59";
    public static final Integer EXCEL_EXPORT_MAX_COUNT = 5000;

    //工具用户前缀
//    public static final String TOOL_USER_PREFIX = "T-";

    //用户默认密码 haiyou123..
    public static final String USER_PSWD= "Haiyou123..";

    //角色名只读  需要和数据库角色表中名字对应
    public static final String READ_ONLY= "只读";
    //角色名只读  需要和数据库角色表中名字对应
    public static final String READ_ONLY_ID= "2";

//    仓库号
    public static final String B01= "B01";
    public static final String B14= "B14";
    public static final String B16= "B16";




}

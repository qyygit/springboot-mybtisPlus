package com.atguigu.boot.datebase;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * CodeGenerator
 * mp生成工具
 */
public class CodeGenerator {

    //数据库路径
    private static String url = "jdbc:mysql://127.0.0.1:3306/qyy_db?useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=GMT%2B8";
    private static String username = "root";
    private static String password = "root";
    private static String packagePath = "com.qyy.device"; //包路径
    private static String author = "qyy"; //作者
    private static String prefix = ""; //前缀
    private static String projectPath = "D:\\mybatisPlus\\workspace\\qyy_db\\plus"; //项目的路径，在下面自动拼接了 /src/main/java
    private static String[] tables = {"iot_device"};//生成的表


    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        //String projectPath = "D:\\ideaWork\\iot-coldchain";
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor(author);
        gc.setOpen(false);// 生成后是否打开资源管理器
        gc.setFileOverride(true);//文件是否覆盖
        gc.setServiceName("%sService");//去掉Service接口的首字母I
        gc.setIdType(IdType.AUTO);// 主键策略
        gc.setDateType(DateType.ONLY_DATE);// 日期类型
        gc.setSwagger2(false); // 实体属性 Swagger2 注解
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(url);
        // dsc.setSchemaName("public");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername(username);
        dsc.setPassword(password);
        dsc.setDbType(DbType.MYSQL);
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        // 主模块，会自动多创建一层包
        //pc.setModuleName("user");
        pc.setParent(packagePath);
        pc.setController("controller");
        pc.setService("service");
        pc.setEntity("entity");
        pc.setMapper("mapper");
        mpg.setPackageInfo(pc);


        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setInclude(tables);
        strategy.setNaming(NamingStrategy.underline_to_camel);//表和实体类的命名规则
        strategy.setTablePrefix(prefix);
        //strategy.setTablePrefix(pc.getModuleName()+"_");
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);//字段映射规则
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true); //restfulapi风格
        strategy.setControllerMappingHyphenStyle(true);//url中驼峰转连字符
        mpg.setStrategy(strategy);


        mpg.execute();
    }
}
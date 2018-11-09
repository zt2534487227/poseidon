package com.zt.poseidon.common.util;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * @Author: ZhouTian
 * @Description: Mybatis-Plus 代码生成器
 * @Date: 2018/8/22
 */
public class MpGenerator {

    private static final String url="jdbc:mysql://localhost:3306/blog?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai&useSSL=false";
    private static final String driverName="com.mysql.cj.jdbc.Driver";
    private static final String username="root";
    private static final String password="123456";
    private static final String packageParent="com.zt.blog";
    private static final String outputDir="D:\\zttttt";
    private static final String src="//src//main//java";
    private static final String tablePrefix="t_";
    private static final String[] includeTables={"t_user_token"};

    public static void main(String[] args) {
        new AutoGenerator()
                .setDataSource(new DataSourceConfig()  //数据源配置
                        .setDbType(DbType.MYSQL)
                        .setDriverName(driverName)
                        .setUrl(url)
                        .setUsername(username)
                        .setPassword(password)
                        .setTypeConvert(new ITypeConvert() {   //自定义类型映射
                            @Override
                            public DbColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
                                String t = fieldType.toLowerCase();
                                if (t.contains("bigint")) {
                                    return DbColumnType.LONG;
                                } else if (t.contains("tinyint(1)")) {
                                    return DbColumnType.BOOLEAN;
                                } else if (t.contains("int")) {
                                    return DbColumnType.INTEGER;
                                } else if (t.contains("text")) {
                                    return DbColumnType.STRING;
                                } else if (t.contains("bit")) {
                                    return DbColumnType.BOOLEAN;
                                } else if (t.contains("decimal")) {
                                    return DbColumnType.BIG_DECIMAL;
                                } else if (t.contains("clob")) {
                                    return DbColumnType.CLOB;
                                } else if (t.contains("blob")) {
                                    return DbColumnType.BLOB;
                                } else if (t.contains("binary")) {
                                    return DbColumnType.BYTE_ARRAY;
                                } else if (t.contains("float")) {
                                    return DbColumnType.FLOAT;
                                } else if (t.contains("double")) {
                                    return DbColumnType.DOUBLE;
                                }else if (t.contains("date")||t.contains("time")){
                                    return DbColumnType.DATE;
                                }else {
                                    return DbColumnType.STRING;
                                }
                            }
                        }))
                .setStrategy(new StrategyConfig()   //策略配置
                        .setTablePrefix(tablePrefix)
                        .setNaming(NamingStrategy.underline_to_camel)
                        .setInclude(includeTables)
                        .setEntityLombokModel(true)
                        )
                .setPackageInfo(new PackageConfig()  //包配置
                        .setParent(packageParent)
                        .setEntity("model")
                        .setMapper("dao")
                        .setXml("mapping")
                        .setService("service")
                        .setServiceImpl("service.impl"))
                .setGlobalConfig(new GlobalConfig()   //全局配置
                        .setOutputDir(outputDir+src)
                        .setMapperName("%sDao")
                        .setServiceName("%sService")
                        .setBaseColumnList(true)
                        .setBaseResultMap(true)
                        .setActiveRecord(true)
                        .setEnableCache(false)
                        .setSwagger2(true)
                        .setAuthor("ZhouTian"))
                .setTemplate(new TemplateConfig()   //模板配置
                        .setController(null)
                        )
                .execute();

    }


}

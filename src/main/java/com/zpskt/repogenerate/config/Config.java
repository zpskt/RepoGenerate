package com.zpskt.repogenerate.config;

import lombok.Data;

/**
 * @descriptions: 代码生成器配置
 * @author: 22035483 zhangpeng
 * @date: 2022/11/28 9:11
 * @version: 1.0
 */
@Data
public class Config {
    /**
     * 作者
     */
    private String author;

    /**
     * 包名
     */
    private String packageName;

    /**
     * 父包名
     */
    private String parentPackageName;

    /**
     * 模块名
     */
    private String moduleName;

    /**
     * 项目根路径
     */
    private String finalProjectPath;
    /**
     * 数据库url
     */
    private String dataBaseUrl;

    /**
     * 数据库用户
     */
    private String dataBaseUser;

    /**
     * 数据库密码
     */
    private String dataBasePassword;

    /**
     * 表名：用,分隔
     */
    private String tables;

    public Config() {

    }

    public Config(String author, String packageName, String parentPackageName, String moduleName, String finalProjectPath, String dataBaseUrl, String dataBaseUser, String dataBasePassword, String tables) {
        this.author = author;
        this.packageName = packageName;
        this.parentPackageName = parentPackageName;
        this.moduleName = moduleName;
        this.finalProjectPath = finalProjectPath;
        this.dataBaseUrl = dataBaseUrl;
        this.dataBaseUser = dataBaseUser;
        this.dataBasePassword = dataBasePassword;
        this.tables = tables;
    }
}

package com.zpskt.repogenerate.execute;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.TemplateType;
import com.baomidou.mybatisplus.generator.config.builder.CustomFile;
import com.baomidou.mybatisplus.generator.fill.Column;
import com.zpskt.repogenerate.config.Config;

import java.util.*;

/**
 * @descriptions: my-batis-plus-generator原生生成方式
 * @author: 22035483 zhangpeng
 * @date: 2022/11/28 9:20
 * @version: 1.0
 */
public class OriginGenerator {
    private Config config;

    public OriginGenerator(Config config) {
        this.config = config;
    }

    public void generateCode(){
        String finalProjectPath = config.getFinalProjectPath();
        FastAutoGenerator.create(config.getDataBaseUrl(),config.getDataBaseUser(),config.getDataBasePassword())
                // 全局配置
                .globalConfig(builder -> {
                    builder.author(config.getAuthor()) // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .disableOpenDir() //禁止打开输出目录
                            .outputDir(finalProjectPath + "/src/main/java"); // 指定输出目录
                })
                // 包配置
                .packageConfig(builder -> {
                    builder.parent(config.getParentPackageName()) // 设置父包名
//                            .moduleName("test") // 设置父包模块名
                            .entity("entity") //设置entity包名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, finalProjectPath + "/src/main/resources/mapper"))
                        ; // 设置mapperXml生成路径

                })
                .templateConfig(builder -> {
                    builder.disable(TemplateType.ENTITY)
                            .entity("/templates/Myentity.java.vm");
                })
                // 策略配置
                .strategyConfig((scanner, builder) -> builder.addInclude(getTables(config.getTables()))
                        .controllerBuilder().enableRestStyle().enableHyphenStyle()
                        .entityBuilder().enableLombok().addTableFills(
                                new Column("create_time", FieldFill.INSERT)
                        ).build())

//                //控制类策略配置
                .strategyConfig(builder -> {
                    builder.controllerBuilder()
                            .enableRestStyle();
                })
                //服务类策略配置
                .strategyConfig(builder -> {
                    builder.serviceBuilder()
                            .formatServiceFileName("%sService")
                            .formatServiceImplFileName("%sServiceImp");
                })

//                .strategyConfig(builder -> {
//                    builder
////                            .addInclude("tb_teachplan")// 设置数据库中表名，按其中属性内容进行生成
////                            .addInclude("tb_floor")
////                            .addInclude("tb_room")
//                            .addTablePrefix("tb_", "c_", "sys_"); // 设置过滤表前缀进行生成
//                })
                //注入配置，自定义模板配置
                .injectionConfig(builder -> {
                        builder.customMap(Collections.singletonMap("test", "baomidou"))
                            .customFile(new CustomFile.Builder()
                            .fileName("DTO.java")
                            .templatePath("/templates/dto.java.vm")
                            .packageName("dto")
                            .build());
                })
                //mapper策略配置
                .strategyConfig(builder -> {
                        builder.mapperBuilder()
                                    .enableBaseColumnList() // 启用 columnList (通用查询结果列)
                                    .enableBaseResultMap() // 启动resultMap
                                    .formatMapperFileName("%sMapper") // Mapper 文件名称
                                    .formatXmlFileName("%sMapper"); // Xml 文件名称

                })
                .execute();
    }



    // 处理 all 情况
    protected static List<String> getTables(String tables) {
        return "all".equals(tables) ? Collections.emptyList() : Arrays.asList(tables.split(","));
    }
}
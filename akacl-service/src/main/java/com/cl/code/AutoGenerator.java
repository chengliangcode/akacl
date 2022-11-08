package com.cl.code;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.TemplateType;
import com.baomidou.mybatisplus.generator.config.po.LikeTable;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.cl.code.common.datasource.entity.BaseEntity;

import java.util.Collections;

/**
 * @author chengliang
 * @since 2022/11/6 0:32
 */
public class AutoGenerator {

    public static void main(String[] args) {
        String projectDir = System.getProperty("user.dir");
        String url = "jdbc:mysql://127.0.0.1:3306/akacl?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=Asia/Shanghai";
        String username = "root";
        String password = "123456";
        FastAutoGenerator.create(url, username, password)
                .globalConfig(builder -> {
                    builder.disableOpenDir()
                            .author("chengliang") // 设置作者
                            .outputDir(projectDir + "\\akacl-service\\src\\main\\java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.cl.code.module") // 设置父包名
                            .moduleName("system") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, projectDir + "\\akacl-service\\src\\main\\resources\\module")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.likeTable(new LikeTable("cl_")); // 设置需要生成的表名

                    // entity config
                    builder.entityBuilder()
                            .enableLombok()
                            .enableColumnConstant()
                            .superClass(BaseEntity.class)
                            .enableTableFieldAnnotation()
                            .fileOverride()
                            .addSuperEntityColumns("id", "create_user_id", "create_time", "update_user_id", "update_time", "enable", "deleted");

                    builder.mapperBuilder()
                            .fileOverride()
                            .enableMapperAnnotation();

                })
                .templateConfig(builder -> {
                    builder.disable(TemplateType.CONTROLLER);
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }

}

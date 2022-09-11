package com.demo.carrental;

import java.io.File;
import java.net.URL;
import java.util.Collections;
import java.util.Objects;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

public class Generator {

    private String url = "jdbc:mysql://localhost:3306/car_rental_db?characterEncoding=utf8&allowMultiQueries=true&useSSL=false&serverTimezone=GMT%2B8";
    private String userName = "root";
    private String password = "123456";

    private String[] INCLUDE_TABLE_NAMES = {
            "table_customer",
            "table_car_category",
            "table_car",
            "table_rental_order"
    };

    private String TABLE_PREFIX = "table_";

    public static void main(String[] args) {
        new Generator().start();
    }

    private void start(){
        FastAutoGenerator.create(url, userName, password)
                .globalConfig(builder -> {
                    builder.author("baomidou") // 设置作者
//                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir(this.getRootPath()); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.demo") // 设置父包名
                            .moduleName("carrental") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, this.getRootPath()+"/mapper")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude(INCLUDE_TABLE_NAMES) // 设置需要生成的表名
                            .addTablePrefix(TABLE_PREFIX); // 设置过滤表前缀
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }

    private String getRootPath() {
        String file = ((URL) Objects.requireNonNull(this.getClass().getClassLoader().getResource(""))).getFile();
        return (new File(file)).getParentFile().getParent();
    }

    private String getJavaPath() {
        String javaPath = this.getRootPath() + "/src/main/java";
        System.err.println(" Generator Java Path:【 " + javaPath + " 】");
        return javaPath;
    }

    private String getResourcePath() {
        String resourcePath = this.getRootPath() + "/src/main/resources";
        System.err.println(" Generator Resource Path:【 " + resourcePath + " 】");
        return resourcePath;
    }

    private String getTestPath() {
        String testPath = this.getRootPath() + "/src/test/java";
        System.err.println(" Generator Test Path:【 " + testPath + " 】");
        return testPath;
    }
}

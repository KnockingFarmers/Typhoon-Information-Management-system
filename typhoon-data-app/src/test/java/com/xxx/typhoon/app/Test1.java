package com.xxx.typhoon.app;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.xxx.common.util.FileUtil;
import com.xxx.tphoon.fileOperation.exception.FileCommonException;
import com.xxx.tphoon.fileOperation.service.impl.CsvFileServiceImpl;
import com.xxx.typhoon.app.entity.TyphoonData;
import com.xxx.typhoon.app.mapper.TyphoonDataMapper;
import com.xxx.typhoon.app.service.TyphoonDataService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.lang.reflect.Field;
import java.util.List;

/**
 * @Author 甘龙
 * @Date 2022/8/29 16:49
 * @PackageName:com.xxx.typhoon
 * @ClassName: Test1
 * @Description: TODO
 * @Version 1.0
 */
@SpringBootTest
public class Test1 {

//    @Autowired
//    FileUtil<TyphoonData> fileUtil;

    @Autowired
    TyphoonDataService dataService;

    @Autowired
    TyphoonDataMapper dataMapper;

    @Test
    void test1() {
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/typhoon?uesSSL=true&useUnicode=true&characterEncoding=utf-8", "root", "")
                .globalConfig(builder -> {
                    builder.author("") // 设置作者
                            .outputDir("D:\\兼职接单\\L2903\\代码\\VisualizationOfTyphoonData\\typhoon-data-app\\src\\main\\java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.xxx.typhoon.app")
                            .service("service")
                            .xml("mapper")
                            .controller("controller")
                            .entity("entity")
                            .serviceImpl("serviceImpl");

                })
                .strategyConfig(builder -> {
                    // 设置需要生成的表名
                    builder.addInclude("typhoon_news")
                            .serviceBuilder()
                            .formatServiceImplFileName("%sServiceImpl")
                            .formatServiceFileName("%sService")
                            .entityBuilder()
                            .enableLombok()
                            .versionColumnName("version")
                            .logicDeleteColumnName("deleted")
                            .idType(IdType.ASSIGN_ID)
                            .enableTableFieldAnnotation()
                            .controllerBuilder()
                            .formatFileName("%sController")
                            .enableRestStyle()
                            .mapperBuilder()
                            .superClass(BaseMapper.class)
                            .enableMapperAnnotation()
                            .formatXmlFileName("%sMapper")
                            .formatMapperFileName("%sMapper");
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();

    }

    @Test
    void fileTest() throws Exception {
        FileUtil<TyphoonData> fileUtil = new FileUtil<>();
        File file = new File("C:\\Users\\GL\\Desktop\\台风黑格比登陆.xlsx");
        try {
//            List<TyphoonData> typhoonData = fileUtil.readExcelFile(file, TyphoonData.class);
//            typhoonData.forEach(System.out::println);
            dataService.redExcel(file,"黑格比");
        } catch (FileCommonException e) {
            e.printStackTrace();
        }
    }


    @Test
    void fileTest2() {
        FileUtil<TyphoonData> fileUtil = new FileUtil<>();
        File file = new File("D:\\兼职接单\\L2903\\资料\\台风数据集(1)\\台风数据集\\台风黑格比登陆.csv");
        try {
//
            CsvFileServiceImpl service=new CsvFileServiceImpl();
            List<Object> list = service.readCsv(file, TyphoonData.class);
            list.forEach(System.out::println);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

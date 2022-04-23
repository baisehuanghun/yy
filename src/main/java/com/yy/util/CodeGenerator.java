package com.yy.util;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;


/**
 * @author bs
 * @create 2022-04-20
 */
public class CodeGenerator {
	public static void main(String[] args) {
		String tebleName = "t_tnt_4k";
		// 代码生成器
		AutoGenerator mpg = new AutoGenerator();
		// 全局配置
		GlobalConfig gc = new GlobalConfig();
		String projectPath = System.getProperty("user.dir");
		gc.setOutputDir(projectPath + "/src/main/java");
		gc.setAuthor("bs");
		gc.setOpen(false);
		gc.setActiveRecord(true);
		//gc.setEntityName("%s");
		mpg.setGlobalConfig(gc);

		// 数据源配置
		DataSourceConfig dsc = new DataSourceConfig();
		dsc.setUrl("jdbc:mysql://localhost:3306/yy?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8");
		dsc.setDriverName("com.mysql.cj.jdbc.Driver");
		dsc.setUsername("root");
		dsc.setPassword("root");
		mpg.setDataSource(dsc);

		// 包配置
		PackageConfig pc = new PackageConfig();
		pc.setParent("com.yy");
		mpg.setPackageInfo(pc);
		InjectionConfig cfg = new InjectionConfig() {
			@Override
			public void initMap() {
			}
		};

		String templatePath = "/templates/mapper.xml.ftl";
		List<FileOutConfig> focList = new ArrayList<>();
		focList.add(new FileOutConfig(templatePath) {
			@Override
			public String outputFile(TableInfo tableInfo) {
				return projectPath + "/src/main/resources/mapper/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
			}
		});

		cfg.setFileOutConfigList(focList);
		mpg.setCfg(cfg);

		TemplateConfig templateConfig = new TemplateConfig();

		templateConfig.setXml(null);
		mpg.setTemplate(templateConfig);

		StrategyConfig strategy = new StrategyConfig();
		strategy.setTablePrefix("t_");// 此处可以修改为您的表前缀
		strategy.setNaming(NamingStrategy.underline_to_camel);
		strategy.setColumnNaming(NamingStrategy.underline_to_camel);
		strategy.setEntityLombokModel(true);
		strategy.setRestControllerStyle(false);
		strategy.setInclude(tebleName);
		strategy.setControllerMappingHyphenStyle(true);
		mpg.setStrategy(strategy);
		mpg.setTemplateEngine(new FreemarkerTemplateEngine());
		mpg.execute();
	}

}

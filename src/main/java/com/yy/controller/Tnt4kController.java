package com.yy.controller;


import com.google.common.collect.Lists;
import com.yy.config.Constants;
import com.yy.entity.Tnt;
import com.yy.entity.Tnt4k;
import com.yy.service.ITnt4kService;
import com.yy.service.ITntService;
import com.yy.util.JsoupUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author bs
 * @since 2022-04-23
 */
@Controller
@RequestMapping("/tnt4-k")
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan("com.jav.mapper")
public class Tnt4kController {
	@Autowired
	private ITnt4kService tnt4kService;


	@Test
	public void init() {
		tnt4kService.init();

	}

}

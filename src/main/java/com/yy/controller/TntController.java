package com.yy.controller;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yy.config.BaseController;
import com.yy.util.TableDataInfo;
import com.yy.entity.Tnt;
import com.yy.service.ITntService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author bs
 * @since 2022-04-22
 */
@Controller
@RequestMapping("/tnt")
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan("com.jav.mapper")
public class TntController extends BaseController {

	private String prefix = "tnt";

	@Autowired
	private ITntService tntService;

	@GetMapping()
	public String dictType() {
		return prefix + "/type";
	}

	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Tnt tnt) {
		QueryWrapper<Tnt> wrapper = Wrappers.query();
		Page<Tnt> page = startPage();
		page =  tntService.page(page,wrapper);
		return getDataTable(page);
	}

	@Test
	public void init() {
		tntService.init();

	}

}

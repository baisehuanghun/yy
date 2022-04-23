package com.yy.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.yy.config.BaseController;
import com.yy.entity.Menu;
import com.yy.service.IMenuService;
import com.yy.util.TreeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @author bs
 * @create 2022-04-22
 */
@Controller
public class IndexController extends BaseController {

	@Autowired
	private IMenuService menuService;

	@GetMapping("/index")
	public String index(ModelMap mmap){
		//where m.menu_type in ('M', 'C') and m.visible = 0
		QueryWrapper<Menu> wrapper = Wrappers.query();
		wrapper.in("menu_type","M","C");
		wrapper.eq("visible",0);
		List<Menu> menus = menuService.list(wrapper);

		menus = TreeUtils.getChildPerms(menus, 0);

		mmap.put("menus", menus);
		return "index";
	}
}

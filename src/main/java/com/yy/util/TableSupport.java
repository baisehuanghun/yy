package com.yy.util;

import cn.hutool.core.convert.Convert;
import lombok.Data;

/**
 * @author bs
 * @create 2022-04-22
 */
@Data
public class TableSupport {
	/**
	 * 当前记录起始索引
	 */
	private Integer pageNum;

	/**
	 * 每页显示记录数
	 */
	private Integer pageSize;

	public static TableSupport buildPageRequest() {
		TableSupport pageDomain = new TableSupport();
		pageDomain.setPageNum(Convert.toInt(ServletUtils.getParameter("pageNum"), 1));
		pageDomain.setPageSize(Convert.toInt(ServletUtils.getParameter("pageSize"), 10));
		return pageDomain;
	}
}

package com.yy.config;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yy.util.TableDataInfo;
import com.yy.util.TableSupport;

import java.util.List;

/**
 * @author bs
 * @create 2022-04-22
 */
public class BaseController {

	/**
	 * 设置请求分页数据
	 */
	protected <E> Page<E> startPage() {
		TableSupport tableSupport = TableSupport.buildPageRequest();

		int pageNum = tableSupport.getPageNum();
		int pageSize = tableSupport.getPageSize();
		return new Page<>(pageNum, pageSize);

	}


	protected TableDataInfo getDataTable(Page<?> page)
	{
		TableDataInfo rspData = new TableDataInfo();
		rspData.setCode(0);
		rspData.setRows(page.getRecords());
		rspData.setTotal(page.getTotal());
		return rspData;
	}
}

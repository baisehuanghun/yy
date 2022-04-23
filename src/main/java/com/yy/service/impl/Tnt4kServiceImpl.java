package com.yy.service.impl;

import com.google.common.collect.Lists;
import com.yy.config.Constants;
import com.yy.entity.Tnt4k;
import com.yy.mapper.Tnt4kMapper;
import com.yy.service.ITnt4kService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yy.util.JsoupUtil;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author bs
 * @since 2022-04-23
 */
@Service
public class Tnt4kServiceImpl extends ServiceImpl<Tnt4kMapper, Tnt4k> implements ITnt4kService {

	@Override
	public void init() {
		//int maxTid = tntService.getMaxTidByFid(fid);

		int page = 1;
		while(true){
			List<Tnt4k> tnt4kList = Lists.newArrayList();
			Document document = JsoupUtil.sendGetProxy(Constants.TNT_LIST_PREFIX + 202 + Constants.TNT_LIST_JOINER + page);

			Elements elementsList = document.getElementsByClass("c cl");
			if(CollectionUtils.isEmpty(elementsList)){
				break;
			}
			for (Element element : elementsList) {

				Element aElement =element.child(0);
				String title = aElement.attr("title");
				String detailsLink = StringUtils.substringBefore(aElement.attr("href"),"&extra");
				//Integer tid = Integer.parseInt(StringUtils.substringBetween(titleHref,"tid=",

				String releaseDate = element.parent().getElementsByTag("em").last().text();
				String[] strings = releaseDate.split("-");
				String d = strings[0] +"-" + String.format("%02d", Integer.parseInt(strings[1]))
						+"-" + String.format("%02d", Integer.parseInt(strings[2]));
				//if(tid > maxTid){
				Tnt4k tnt4k = Tnt4k.builder()
						.title(title)
						.releaseDate(d).detailsLink(detailsLink)
						.source("tnt")
						.build();
				tnt4kList.add(tnt4k);
				//}
			}
			if(CollectionUtils.isEmpty(tnt4kList)){
				break;
			}
			this.saveBatch(tnt4kList);
			page++;
		}
	}
}

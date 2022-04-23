package com.yy.service.impl;

import cn.hutool.core.io.file.FileNameUtil;
import com.google.common.collect.Lists;
import com.yy.config.Constants;
import com.yy.entity.Tnt;
import com.yy.mapper.TntMapper;
import com.yy.service.ITntService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yy.util.JsoupUtil;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author bs
 * @since 2022-04-22
 */
@Service
public class TntServiceImpl extends ServiceImpl<TntMapper, Tnt> implements ITntService {

	@SneakyThrows
	@Override
	public void init() {
		Integer fid = 202;
		String fname = "4K超清";

		String firstUrl = Constants.TNT_LIST_PREFIX + fid +Constants.TNT_LIST_JOINER + 1;
		Document firstPage = JsoupUtil.sendGetProxy(firstUrl);
		Element pageElement = firstPage.getElementsByClass("last").first();
		int maxPage = Integer.parseInt(StringUtils.substringAfter(pageElement.attr("href"),"page="));

		ExecutorService executorService = Executors.newCachedThreadPool();
		final Semaphore semaphore = new Semaphore(20);
		final CountDownLatch countDownLatch = new CountDownLatch(maxPage);

		for (int i = 1; i <= maxPage; i++) {
			Thread.sleep(20);
			int finalI = i;
			executorService.execute(() -> {
				try {
					semaphore.acquire();
					String url = firstUrl + Constants.TNT_LIST_JOINER + finalI;
					Document document = JsoupUtil.sendGetProxy(url);
					List<Tnt> tntList = Lists.newArrayList();
					Elements elementsList = document.getElementsByClass("c cl");

					for (Element element : elementsList) {

						Element aElement =element.child(0);
						String title = aElement.attr("title");
						title = FileNameUtil.cleanInvalid(title);
						String titleHref = aElement.attr("href");
						String detailsLink = StringUtils.substringBefore(titleHref,"&extra").replace(Constants.TNT_PREFIX,"");
						Long id = Long.parseLong(StringUtils.substringAfter(detailsLink,"tid="));

						String releaseDate = element.parent().getElementsByTag("em").last().text();
						String[] strings = releaseDate.split("-");
						String d = strings[0] +"-" + String.format("%02d", Integer.parseInt(strings[1]))
								+"-" + String.format("%02d", Integer.parseInt(strings[2]));
						//if(tid > maxTid){
						Tnt tnt = Tnt.builder()
								.id(id)
								.fid(202).fname(fname)
								.title(title)
								.releaseDate(d).detailsLink(detailsLink).build();
						tntList.add(tnt);
						//}
					}

					this.saveBatch(tntList);

					semaphore.release();
				}catch (Exception e) {
					log.error(e.getMessage());
				}finally {
					countDownLatch.countDown();
				}

			});
		}
		countDownLatch.await();
		executorService.shutdown();
	}
}

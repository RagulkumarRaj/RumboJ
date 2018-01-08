package com.rumboj.crawlers.botDelegators;

import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.rumboj.crawlers.scraperRobot.FlipkartPhoneInfoScraperBot;
import com.rumboj.services.fileService.StaticDataLoader;

public class FlipkartInfoScraperBotDelegator extends AbstractInfoScraperBotDelegator {
	private List<String> startUrlList = StaticDataLoader
			.getStringArray("flipkartStartUrlList");

	public FlipkartInfoScraperBotDelegator() {
		super();
	}

	@Override
	public void scrapeAllPhoneLinks() {
		for (int i=0; i<startUrlList.size() && !isStopSignalReceived(); i++) {
			String pageData = this.downloadProductListPage(startUrlList.get(i));
			Document doc = Jsoup.parse(pageData);
			Elements elements = tryCombinationForClassName(doc); // a with href
			
			for (int j=0; j<NUM_OF_RESULTS && !isStopSignalReceived(); j++) {
				Element ele = elements.get(j);
				String url = "https://www.flipkart.com" + ele.attr("href");
				logger.info(this.getClass().getSimpleName() + " passing url :"
						+ url + " to Flipkart scraper task");
				FlipkartPhoneInfoScraperBot task = new FlipkartPhoneInfoScraperBot(
						url);
				this.getProcessManagerService().submitTask(task);
			}
		}
	}

	@Override
	public void scrapeAllWatchLinks() {
		// TODO Auto-generated method stub
	}

	@Override
	public Elements tryCombinationForClassName(Document doc) {
		Elements elements = doc.select("a[class=_1UoZlX]");
		if(elements.size() != 0){
			return elements;
		}
		elements = doc.select("a[class=_2cLu-l]");
		return elements;
	}
}

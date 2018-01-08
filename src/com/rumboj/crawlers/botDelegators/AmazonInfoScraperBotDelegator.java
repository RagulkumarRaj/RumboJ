package com.rumboj.crawlers.botDelegators;

import java.util.List;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.rumboj.crawlers.scraperRobot.AmazonPhoneInfoScraperBot;
import com.rumboj.services.fileService.StaticDataLoader;

public class AmazonInfoScraperBotDelegator extends AbstractInfoScraperBotDelegator {
	final static Logger logger = Logger.getLogger(AmazonInfoScraperBotDelegator.class);
	private List<String> startUrlList = StaticDataLoader
			.getStringArray("amazonStartUrlList");

	public AmazonInfoScraperBotDelegator() {
		super();
	}

	@Override
	public void scrapeAllPhoneLinks() {
		for (int i=0; i<startUrlList.size() && !isStopSignalReceived(); i++) {
			String pageData = this.downloadProductListPage(startUrlList.get(i));
			Document doc = Jsoup.parse(pageData);
			//Elements elements = doc.select("a[class=s-access-detail-page]"); // a with
			Elements elements = doc.select("a.a-link-normal.s-access-detail-page"); // a with href
			
			for (int j=0; j<NUM_OF_RESULTS && !isStopSignalReceived(); j++) {
				Element ele = elements.get(j);
				String url = ele.attr("href");
				AmazonPhoneInfoScraperBot task = new AmazonPhoneInfoScraperBot(
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
	public List<Element> tryCombinationForClassName(Document doc) {
		// TODO Auto-generated method stub
		return null;
	}
}

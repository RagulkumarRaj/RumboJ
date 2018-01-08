package com.rumboj.crawlers.scraperRobot;

import org.jsoup.nodes.Document;

import com.rumboj.core.InterruptibleWorkThread;

public interface ItemInfoScraperBot extends InterruptibleWorkThread {
	public void startScraper();

	public String downloadProductPage(String url);

	public String reducePage(Document pageData);
	
	public String extractPrice(Document pageData);
}

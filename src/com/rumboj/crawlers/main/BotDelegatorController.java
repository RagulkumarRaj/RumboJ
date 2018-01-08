package com.rumboj.crawlers.main;

import org.apache.log4j.Logger;

import com.rumboj.crawlers.botDelegators.AmazonInfoScraperBotDelegator;
import com.rumboj.crawlers.botDelegators.FlipkartInfoScraperBotDelegator;
import com.rumboj.crawlers.botDelegators.InfoScraperBotDelegator;
import com.rumboj.services.beanCreationService.BeanFactory;
import com.rumboj.services.downloadService.HtmlPageDownloadService;
import com.rumboj.workflow.ProcessManager;

public class BotDelegatorController {
	final static Logger logger = Logger.getLogger(BotDelegatorController.class);
	private static HtmlPageDownloadService browser;
	private static final ProcessManager processManager = ProcessManager.getInstance();

	public BotDelegatorController(HtmlPageDownloadService browser) {
		// this.browser = browser;
	}

	public BotDelegatorController() {
	}

	public static void startAllCrawlers() {
		InfoScraperBotDelegator amazonCrawler = (InfoScraperBotDelegator) BeanFactory.getInstance("amazonWebCrawler");
		InfoScraperBotDelegator flipkartCrawler = (InfoScraperBotDelegator) BeanFactory.getInstance("flipkartWebCrawler");
		processManager.submitTask(flipkartCrawler);
		processManager.submitTask(amazonCrawler);
	}
	
	public static void stopAllTasks(){
		processManager.stopAllTasks();
	}
}

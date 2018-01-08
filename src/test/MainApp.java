package test;

import java.io.File;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.rumboj.crawlers.botDelegators.AmazonInfoScraperBotDelegator;
import com.rumboj.crawlers.botDelegators.FlipkartInfoScraperBotDelegator;
import com.rumboj.crawlers.scraperRobot.AmazonPhoneInfoScraperBot;
import com.rumboj.crawlers.scraperRobot.FlipkartPhoneInfoScraperBot;
import com.rumboj.services.dataService.InMemoryTextSearchEngine;
import com.rumboj.services.downloadService.JBrowserHtmlPageDownloadService;

public class MainApp {
	public static void main(String args[]) {
		
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"test.xml");
		//HelloWorld obj = (HelloWorld) context.getBean("helloWorld");
		//System.out.println(obj.getMessage());
		/*
		AmazonPhoneInfoScraperBot obj1 = (AmazonPhoneInfoScraperBot) context
				.getBean("amazonPhoneInfoScraper");
		System.out.println(obj1);
		
		FlipkartPhoneInfoScraperBot obj2 = (FlipkartPhoneInfoScraperBot) context
				.getBean("flipkartPhoneInfoScraper");
		System.out.println(obj2);
		
		AmazonInfoScraperBotDelegator obj3 = (AmazonInfoScraperBotDelegator) context
				.getBean("amazonWebCrawler");
		System.out.println(obj2);
		
		FlipkartInfoScraperBotDelegator obj4 = (FlipkartInfoScraperBotDelegator) context
				.getBean("flipkartWebCrawler");
		System.out.println(obj2);
		*/
	}
}

package test;

import java.util.ArrayList;
import java.util.List;

import com.rumboj.crawlers.scraperRobot.AbstractInfoScraperBot;

public class Application {
	public static void main(String[] args) {
		/*
		 * List amazonDataList = new ArrayList();
		 * amazonDataList.add(new AmazonPhoneDataExtractImpl());
		 * amazonDataList.add(new AmazonWatchDataExtractorImpl()());
		 * DataExtractor ex1 = new AmazonDataExtractor(amazonDataList);
		 * 
		 * DataExtractor ex2 = new FlipkartDataExtractor(new FlipkartPhoneDataExtractImpl());
		 * ex1.start();
		 * ex2.start();
		 * Try to put the data into separate files in separate folders
		 */
		List<AbstractInfoScraperBot> itemInfoScraperList = new ArrayList<AbstractInfoScraperBot>();
		
	}
}

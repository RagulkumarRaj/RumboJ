package com.rumboj.crawlers.scraperRobot;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import com.rumboj.crawlers.main.BotDelegatorController;
import com.rumboj.services.fileService.StaticDataLoader;

public class AmazonPhoneInfoScraperBot extends AbstractInfoScraperBot {
	final static Logger logger = Logger.getLogger(BotDelegatorController.class);
	
	public AmazonPhoneInfoScraperBot(String productUrl) {
		super(productUrl);
	}
	
	public void run(){
		try {
			if(!isStopSignalReceived()){
			logger.info(this.getClass().getSimpleName() + " loading url  :: "
					+ "\n"+productUrl);
			if(StaticDataLoader.amazonDownloadedUrlList.contains(productUrl)){
				return;
			}
			//int randomSleepTime = new Random().nextInt((int) 3000L);
			//Thread.sleep(BASE_SLEEP_TIME + randomSleepTime);
			startScraper();
			//Thread.sleep(BASE_SLEEP_TIME + randomSleepTime);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	@Override
	public String downloadProductPage(String url) {
		String pageData = this.getBrowserService().downloadPageAsString(url);
		// reducePage(pageData, "filePath");
		return pageData;
	}

	@Override
	public String reducePage(Document doc) {
		Elements elements1 = doc.select("div.a-section.a-spacing-medium.a-spacing-top-small"); // a with href
		//Ul element
		Element ulElement = elements1.first();
		//Iterator<Element> it = elements.iterator();
		StringBuilder sb = new StringBuilder();
			//Each Li item under ul
			//for (int i = 0; i < ulElement.children().size(); i++) 
				Element ele1 = ulElement.child(0);
				//Each span item under li
				for (int j = 0; j < ele1.children().size(); j++) {
					Element ele2 = ele1.child(j);
					sb.append(ele2.text());
					sb.append("|");
				}
		return sb.toString();
	}

	@Override
	public String extractPrice(Document pageData) {
		Elements elements = pageData.select("span.a-size-medium.a-color-price");
		String price = "";
		if(elements.size() > 0){
			price = elements.get(0).text();
			return price;
		}
		
		elements = pageData.select("span[class=a-color-price]");
		//Or a-size-medium a-color-price
		price = elements.get(0).text();
		return price.trim();
	}
	
	@Override
	public void writeImageData(Document doc, String title){
		// Selects the first instance of img within .class
		Element element = doc.select(".imgTagWrapper img").first();
		String src = element.attr("src");
		try(InputStream in = new URL(src).openStream()){
			title = title.replaceAll(":", "").replaceAll(" ", "");
		    Files.copy(in, Paths.get("C://Data//images//"+title+".jpg"));
		}
		catch(IOException ex){
			
		}
	}
}

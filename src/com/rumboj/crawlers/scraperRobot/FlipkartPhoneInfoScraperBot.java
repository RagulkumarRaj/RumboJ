package com.rumboj.crawlers.scraperRobot;

import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import com.rumboj.services.fileService.StaticDataLoader;

public class FlipkartPhoneInfoScraperBot extends AbstractInfoScraperBot {
	// private InMemoryTextSearchEngine searchEngine =
	// InMemoryTextSearchEngine.getInstance();
	public FlipkartPhoneInfoScraperBot(String productUrl){
		super(productUrl);
	}
	
	public void run(){
		try{
			if(!isStopSignalReceived()){
				logger.info(this.getClass().getSimpleName() + " loading url  :: "
						+ "\n"+productUrl);
				if(StaticDataLoader.flipkartDownloadedUrlList.contains(productUrl)){
					return;
				}
		    //int randomSleepTime = new Random().nextInt((int) 3000L);
		    //Thread.sleep(BASE_SLEEP_TIME + randomSleepTime);
		    startScraper();
		    //Thread.sleep(BASE_SLEEP_TIME + randomSleepTime);
			}
	     }
	    catch(Exception e){
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
		//Document doc = Jsoup.parse(pageData);
		//Get the price data man, very important
		//Elements elements = doc.select("div[class=_2PF8IO]"); // a with href
		Elements elements = tryCombinationForClassName(doc); // a with href
		Iterator<Element> it = elements.iterator();

		StringBuilder sb = new StringBuilder();
		while (it.hasNext()) {
			Element ele = it.next();
			for (int i = 0; i < ele.children().size(); i++) {
				Element ele1 = ele.child(i);
				for (int j = 0; j < ele1.childNodeSize(); j++) {
					Element ele2 = ele1.child(j);
					// System.out.println(ele2.outerHtml());
					sb.append(ele2.text());
					sb.append(",");
				}
			}
		}
		return sb.toString();
	}

	@Override
	public String extractPrice(Document pageData) {
		Elements elements = pageData.select("div[class=_1vC4OE _37U4_g]");
		String price = elements.get(0).text();
		
		Pattern regex = Pattern.compile("(\\d+(?:\\.\\d+)?)");
		Matcher matcher = regex.matcher(price);
		 while(matcher.find()){
			 String num = matcher.group(1);
		     return new Float(num)+"";
		}
		
		//price = TechSpecificationHelper.extractNumbersFromString(price)+"";
		return 0.0+"";
	}
	
	public Elements tryCombinationForClassName(Document doc) {
		Elements elements = doc.select("div[class=_2PF8IO]");
		if(elements.size() != 0){
			return elements;
		}
		elements = doc.select("div[class=_3WHvuP]");
		return elements;
	}
	
	@Override
	public void writeImageData(Document doc, String title){
		
	}
}

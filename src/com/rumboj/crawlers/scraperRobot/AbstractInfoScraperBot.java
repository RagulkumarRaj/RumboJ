package com.rumboj.crawlers.scraperRobot;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.rumboj.services.comparisonService.AbstractComparator;
import com.rumboj.services.comparisonService.PhoneComparator;
import com.rumboj.services.downloadService.HtmlPageDownloadService;
import com.rumboj.services.downloadService.PhantomHtmlPageDownloadService;
import com.rumboj.services.fileService.StaticDataLoader;

public abstract class AbstractInfoScraperBot implements
		ItemInfoScraperBot {
	static final Integer BASE_SLEEP_TIME = 3500;
	final static Logger logger = Logger.getLogger(AbstractInfoScraperBot.class);
	public boolean isStopSignalReceived = false; 
	//@Autowired
	//@Qualifier("inMemoryTextSearch")
	//private InMemoryTextSearchEngine textSearchEngine;
	//InMemoryTextSearchEngine textSearchEngine = InMemoryTextSearchEngine.getInstance();
	AbstractComparator phoneComparator = new PhoneComparator();

	//@Autowired
	//@Qualifier("pageDownloadService")
	//private HtmlPageDownloadService browserService;
	HtmlPageDownloadService browserService = new PhantomHtmlPageDownloadService();
	
	public String productUrl;
	public AbstractInfoScraperBot(String productUrl) {
		this.productUrl = productUrl;
	}

	public void startScraper() {
		//if(!isStopSignalReceived){
		if(!isStopSignalReceived()){
			String data = downloadProductPage(productUrl);
			Document doc = Jsoup.parse(data);
			String text = reducePage(doc);
			String price = extractPrice(doc);
			String title = doc.select("title").text();
			writeImageData(doc, title);
			if(this.getClass().getSimpleName().contains("Flipkart")){
				logger.info("Adding "+title+" to flipkart index : Content :: "+text+"Price :: "+price);
				StaticDataLoader.addUrlToDownloadList("flipkart", productUrl);
				phoneComparator.prepareComparisonData("Flipkart", title, text, price);
			}
			else{
				logger.info("Adding "+title+" to Amazon index : Content :: "+text+"Price :: "+price);
				StaticDataLoader.addUrlToDownloadList("amazon", productUrl);
				phoneComparator.prepareComparisonData("Amazon", title, text, price);
			}
		}
	}
/*
	private void addTextToInMemoryIndex(String website, String title, String text) {
		try {
			textSearchEngine.createDocument(website,  title, text);
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
	}
*/
	public void run() {}
	
	public boolean isStopSignalReceived(){
		return Thread.currentThread().isInterrupted();
	}
	
	/*public InMemoryTextSearchEngine getTextSearchEngine() {
		return textSearchEngine;
	}

	public void setTextSearchEngine(InMemoryTextSearchEngine textSearchEngine) {
		this.textSearchEngine = textSearchEngine;
	}
*/
	public HtmlPageDownloadService getBrowserService() {
		return browserService;
	}

	public void setBrowserService(HtmlPageDownloadService browserService) {
		this.browserService = browserService;
	}
	
	public void writeImageData(Document doc, String title){
		
	}
	public String toString() {
		return this.getClass().getName() + "::"+productUrl;
	}
}

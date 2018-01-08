package com.rumboj.crawlers.scraperRobot;

import java.io.IOException;
import java.util.List;

import com.google.gson.JsonObject;
import com.rumboj.services.backgroundService.SearchResultsReadWriteService;
import com.rumboj.services.dataService.InMemoryTextSearchEngine;
import com.sun.media.jfxmedia.logging.Logger;

public class TestScraperTask {

	public static void main(String[] args) throws IOException {
		// AbstractInfoScraperBot flipTask = new
		// FlipkartPhoneInfoScraperBot("");
		AbstractInfoScraperBot amazonTask = new TestAmazonScraperTask("");
		amazonTask.startScraper();
		//AbstractInfoScraperBot flipkartTask = new TestFlipkartScraperTask("");
		//flipkartTask.startScraper();
		
		//InMemoryTextSearchEngine search = InMemoryTextSearchEngine.getInstance();
		//List<JsonObject> objs= search.getSearchResults("Nexus");
		//System.out.println("Final Res "+objs);
		//SearchResultsReadWriteService.writeSearchResultsData();
		
		//List<JsonObject> objs1= search.getSearchResults("Nexus");
		//System.out.println("Final Res "+objs1);
	}
}

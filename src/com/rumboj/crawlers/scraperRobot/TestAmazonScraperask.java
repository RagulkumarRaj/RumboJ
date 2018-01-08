package com.rumboj.crawlers.scraperRobot;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import com.google.gson.JsonObject;
import com.rumboj.services.dataService.InMemoryTextSearchEngine;

public class TestAmazonScraperask extends AmazonPhoneInfoScraperBot {

	TestAmazonScraperask(String rul) {
		super(rul);
	}

	public static void main(String[] args) {
		// AbstractInfoScraperBot flipTask = new
		// FlipkartPhoneInfoScraperBot("");
		AbstractInfoScraperBot amaoznTask = new TestAmazonScraperask("");
		amaoznTask.startScraper();
		AbstractInfoScraperBot amaoznTask1 = new TestAmazonScraperask("");
		amaoznTask1.startScraper();
		
		InMemoryTextSearchEngine search = InMemoryTextSearchEngine.getInstance();
		 List<JsonObject> objs= search.getSearchResults("Nexus");
		 System.out.println(objs.get(0).toString());
	}

	@Override
	public String downloadProductPage(String url) {
		File in = new File("C:\\PhantomJS\\Data\\AmaNexus5X.html");
		FileReader fr;
		try {
			fr = new FileReader(in);
			BufferedReader br = new BufferedReader(fr);
			StringBuilder sb = new StringBuilder();
			String line;
			line = br.readLine();
			while (line != null) {
				sb.append(line + "\n");
				line = br.readLine();
			}
			br.close();
			fr.close();
			return sb.toString();
		} catch (IOException e) {
			System.out.println(e.getMessage());
			return "";
		}
	}
}

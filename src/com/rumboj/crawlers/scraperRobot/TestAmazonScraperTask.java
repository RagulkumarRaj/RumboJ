package com.rumboj.crawlers.scraperRobot;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.gson.JsonObject;
import com.rumboj.services.dataService.InMemoryTextSearchEngine;
import com.rumboj.services.fileService.StaticDataLoader;

public class TestAmazonScraperTask extends AmazonPhoneInfoScraperBot {

	TestAmazonScraperTask(String rul) {
		super(rul);
	}

	@Override
	public String downloadProductPage(String url) {
		File in = new File("C:\\PhantomJS\\Data\\AmaIphone7.html");
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

	public void startScraper() {
		// if(!isStopSignalReceived){
		{
			long start = System.currentTimeMillis();
			String data = downloadProductPage(productUrl);
			Document doc = Jsoup.parse(data);
			Elements elements1 = doc
					.select("div.a-section.a-spacing-medium.a-spacing-top-small,span.a-size-medium.a-color-price,span.a-color-price,span.a-color-price,title");
			for (int i = 0; i < elements1.size(); i++) {
				Element ele = elements1.get(i);
				System.out.println(ele.text());
			}
			long end = System.currentTimeMillis();
			System.out.println(end - start);
		}
	}
}

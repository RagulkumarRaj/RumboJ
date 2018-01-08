package com.rumboj.crawlers.scraperRobot;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.rumboj.crawlers.botDelegators.FlipkartInfoScraperBotDelegator;

public class TestFlipkartDelegator extends FlipkartInfoScraperBotDelegator{

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public void scrapeAllPhoneLinks() {
			String pageData = downloadProductListPage("");
			Document doc = Jsoup.parse(pageData);
			List<String> urlList = new ArrayList<String>();
			Elements elements = tryCombinationForClassName(doc); // a with href
			
			for (int j=0; j<4 && !isStopSignalReceived(); j++) {
				Element ele = elements.get(j);
				String url = "https://www.flipkart.com" + ele.attr("href");
				urlList.add(url);
				FlipkartPhoneInfoScraperBot task = new FlipkartPhoneInfoScraperBot(
						url);
				this.getProcessManagerService().submitTask(task);
			}
	}
	
	public String downloadProductListPage(String url) {
		File in = new File("C:\\PhantomJS\\FlipkartNexus5X.html");
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

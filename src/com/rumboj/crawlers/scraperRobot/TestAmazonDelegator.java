package com.rumboj.crawlers.scraperRobot;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class TestAmazonDelegator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public void scrapeAllPhoneLinks() {
			String pageData = this.downloadProductListPage("");
			Document doc = Jsoup.parse(pageData);
			//Elements elements = doc.select("a[class=s-access-detail-page]"); // a with
			Elements elements = doc.select("a.a-link-normal.s-access-detail-page"); // a with href
			
			for (int j=0; j<3; j++) {
				Element ele = elements.get(j);
				String url = ele.attr("href");
				AmazonPhoneInfoScraperBot task = new AmazonPhoneInfoScraperBot(
						url);
			
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

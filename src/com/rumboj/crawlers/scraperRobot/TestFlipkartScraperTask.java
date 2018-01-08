package com.rumboj.crawlers.scraperRobot;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import com.google.gson.JsonObject;
import com.rumboj.services.dataService.InMemoryTextSearchEngine;

public class TestFlipkartScraperTask extends FlipkartPhoneInfoScraperBot {

	TestFlipkartScraperTask(String rul) {
		super(rul);
	}

	@Override
	public String downloadProductPage(String url) {
		File in = new File("C:\\PhantomJS\\Data\\FlipkartNexus5X.html");
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

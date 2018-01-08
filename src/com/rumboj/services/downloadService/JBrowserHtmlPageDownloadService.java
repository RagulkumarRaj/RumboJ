package com.rumboj.services.downloadService;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import jsoup.AmazonDataExtractor;
import jsoup.FlipkartDataExtractor;

import com.machinepublishers.jbrowserdriver.JBrowserDriver;
import com.machinepublishers.jbrowserdriver.ProxyConfig;
import com.machinepublishers.jbrowserdriver.ProxyConfig.Type;
import com.machinepublishers.jbrowserdriver.Settings;
import com.machinepublishers.jbrowserdriver.Timezone;
import com.machinepublishers.jbrowserdriver.UserAgent;

public class JBrowserHtmlPageDownloadService extends AbstractHtmlPageDownloadService{
	static final Settings settings = Settings.builder().timezone(Timezone.AMERICA_NEWYORK).build();
	static UserAgent userAgent = new UserAgent(UserAgent.Family.MOZILLA, "", "Windows 7", "", "5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.75 Safari/537.36", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36");
	static JBrowserDriver driver; 
	static{
		driver = new JBrowserDriver(settings.builder().userAgent(userAgent).
                build());
	}
	public static void main(String args[]) {
		try {
			// ProxyConfig pConfig = new ProxyConfig(Type.SOCKS, "127.0.0.1",
			// 9150);
			// JBrowserDriver driver = new JBrowserDriver(Settings.builder()
			// .timezone(Timezone.AMERICA_NEWYORK).proxy(pConfig).build());
			//JBrowserDriver driver = new JBrowserDriver(Settings.builder()
			//		.timezone(Timezone.AMERICA_NEWYORK).build());
			//String page = getPageData("http://google.com");
			//page = getPageData("https://people.cs.clemson.edu/~bcdean/dp_practice/");
			// driver.get("https://www.flipkart.com/google-pixel-very-silver-32-gb/p/itmemv9ssrsvgrez?pid=MOBEMV9SFVHJFMZS&srno=s_1_2&otracker=search&lid=LSTMOBEMV9SFVHJFMZSPXKAWA&qH=c822c1b63853ed27");
			// String pageSource = driver.getPageSource();
			// writePagedatatoFile(pageSource,
			// "C:\\Data\\GoogleMobileFlipkart.html");
			// FlipkartDataExtractor.reduce(pageSource,
			// "C:\\Data\\RedGoogleMobileFlipkart.html");
			JBrowserHtmlPageDownloadService ser = new JBrowserHtmlPageDownloadService();
			String page = ser.downloadPageAsString("http://google.com");
			System.out.println(page);
			page = ser.downloadPageAsString("http://yahoo.com");
			System.out.println(page);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public String downloadPageAsString(String url) {
		JBrowserDriver driver = new JBrowserDriver(settings.builder().userAgent(userAgent).
              build());
		driver.get(url);
		String pageSource = driver.getPageSource();
		driver.reset();
		driver.quit();
		return pageSource;
	}

	@Override
	public String downloadPageWithProxyAsString(String url, String proxy, String port,
			String proxyType) {
		return null;
	}
}

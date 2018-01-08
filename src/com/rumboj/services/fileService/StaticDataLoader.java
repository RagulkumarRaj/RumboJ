package com.rumboj.services.fileService;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;

import com.rumboj.crawlers.main.BotDelegatorController;
import com.rumboj.utilities.PropertiesReader;
import com.sun.org.apache.bcel.internal.generic.NEW;

public class StaticDataLoader {
	
	public static final String RUMBOJ_PHONES_LIST = "rumboj.phones.list";
	public static final String RUMBOJ_AMAZON_STARTURLS_LIST = "rumboj.amazon.starturls.list";
	public static final String RUMBOJ_FLIPKART_STARTURLS_LIST = "rumboj.flipkart.starturls.list";
	public static final String RUMBOJ_FLIPKART_URLS_DOWNLOADED_LIST = "rumboj.flipkart.urls.downloaded.list";
	public static final String RUMBOJ_AMAZON_URLS_DOWNLOADED_LIST = "rumboj.amazon.urls.downloaded.list";
	
	public static List<String> flipkartDownloadedUrlList = new ArrayList<String>(); 
	public static  List<String> amazonDownloadedUrlList = new ArrayList<String>(); 
	
	final static Logger logger = Logger.getLogger(StaticDataLoader.class);
	
	private static Map<String, List<String>> staticData;
	
	static {
		try{
	    HashMap<String, List<String>> map = new HashMap<String, List<String>>();
	    map.put("phonesList", readAllPropertyFileLines(RUMBOJ_PHONES_LIST));
		//map.put("downloadedUrlListFlipkart", readAllPropertyFileLines(RUMBOJ_AMAZON_URLS_DOWNLOADED_LIST));
		//map.put("downloadedUrlListAmazon", readAllPropertyFileLines(RUMBOJ_FLIPKART_URLS_DOWNLOADED_LIST));
		map.put("flipkartStartUrlList", readAllPropertyFileLines(RUMBOJ_FLIPKART_STARTURLS_LIST));
		map.put("amazonStartUrlList", readAllPropertyFileLines(RUMBOJ_AMAZON_STARTURLS_LIST));
		
		flipkartDownloadedUrlList = readAllPropertyFileLines(RUMBOJ_FLIPKART_URLS_DOWNLOADED_LIST);
		amazonDownloadedUrlList = readAllPropertyFileLines(RUMBOJ_AMAZON_URLS_DOWNLOADED_LIST);
		
		staticData = Collections.unmodifiableMap(map);
		}
		catch(Exception e){
           logger.info("Exception occured "+e.getMessage());
		}
	}
	
	public static void writeAllCacheDataToFile(String filename){
		//Write some thread that will start at load time..periodically write data to file
		//Something ContextloaderListener or ApplicationListener something
		//Under beans.xml..Google it out....
		// TODO Auto-generated method stub
	}
	
	public static List<String> getStringArray(String stringArrayName){
		if(staticData.containsKey(stringArrayName)){
			return staticData.get(stringArrayName);
		}
		else{
			logger.error("Entry not found");;
			return null;
		}
	}

	public static void readAllCacheDataFromFile(String filename){
		// TODO Auto-generated method stub
		//Read all data at stratup...VVI
	}
	
	public static List<String> readAllPropertyFileLines(String resourceName){
        //InputStream str = ClassLoader.getSystemResourceAsStream("com/rumboj/res"+resourceName);
		//InputStream str = StaticDataLoader.class.getResourceAsStream("com/rumboj/res"+resourceName);
		InputStream str = StaticDataLoader.class.getClassLoader().getResourceAsStream("com/rumboj/resources/"+resourceName);
		
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(str))) {
            return reader.lines().collect(Collectors.toList());
        } catch (IOException e1) {
			logger.error(e1.getMessage());
			return new ArrayList<String>();
		}
	}
	
	private static void writeLinesToPropertyFile(String resourceName, List<String> lines){
        //InputStream str = ClassLoader.getSystemResourceAsStream("com/rumboj/res"+resourceName);
		//InputStream str = StaticDataLoader.class.getResourceAsStream("com/rumboj/res"+resourceName);
		URL path = StaticDataLoader.class.getResource("/com/rumboj/resources/"+resourceName);
        final File file = new File( path.getFile() ); // toString()
        
        try{
        	Files.write(Paths.get(file.getName()),lines);
         }
        catch(IOException ex){
        	logger.error(ex.getMessage());
        }
	}
	
	public static void addUrlToDownloadList(String website, String url){
		if(website.equalsIgnoreCase("amazon")){
			amazonDownloadedUrlList.add(url);
			writeLinesToPropertyFile(RUMBOJ_AMAZON_URLS_DOWNLOADED_LIST, amazonDownloadedUrlList);
		}
		if(website.equalsIgnoreCase("flipkart")){
			flipkartDownloadedUrlList.add(url);
			writeLinesToPropertyFile(RUMBOJ_FLIPKART_URLS_DOWNLOADED_LIST, flipkartDownloadedUrlList);
		}
	}
	
	//Does not work..DOnt use this method
	
	private static void writeUrlToDwnldCompletedList(String website, String url){
		if(website.equalsIgnoreCase("amazon")){
			try {
				List<String> list = Files.readAllLines(Paths.get(RUMBOJ_AMAZON_URLS_DOWNLOADED_LIST));
				list.add(url);
				Files.write(Paths.get(RUMBOJ_AMAZON_URLS_DOWNLOADED_LIST), list);
			} catch (IOException e) {
				logger.error(e.getMessage());
			}
		}
		if(website.equalsIgnoreCase("flipkart")){
			try {
				List<String> list = Files.readAllLines(Paths.get(RUMBOJ_FLIPKART_URLS_DOWNLOADED_LIST));
				list.add(url);
				Files.write(Paths.get(RUMBOJ_FLIPKART_URLS_DOWNLOADED_LIST), list);
			} catch (IOException e) {
				logger.error(e.getMessage());
			}
		}
	}
}

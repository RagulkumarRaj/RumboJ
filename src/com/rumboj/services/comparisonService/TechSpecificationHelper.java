package com.rumboj.services.comparisonService;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TechSpecificationHelper {
	public static float extractNumbersFromString(String token){
		//Working Change
		//Pattern regex = Pattern.compile("(\\d+(?:\\.\\d+)?)");
		//New Change
		//Not for price..Because regex contains ^ in beginning. WHile extracting price from flipkart, lots of invalid characters are present in price string
		Pattern regex = Pattern.compile("^(\\d+(?:\\.\\d+)?)");
		Matcher matcher = regex.matcher(token);
		 while(matcher.find()){
			 String num = matcher.group(1);
		     return new Float(num);
		}
		 return 0;
	}
	
	static boolean isNumber(String s){
		try{
			Float.parseFloat(s);	
			return true;
		}
		catch(NumberFormatException e){
			return true;
		}
	}
	
	//Like Primary Camera, RAM, ROM etc
	static boolean interestedTokens(String token){
		if((token.equalsIgnoreCase("camera")) || (token.equalsIgnoreCase("touchscreen")) || (token.equalsIgnoreCase("RAM"))
				||(token.equalsIgnoreCase("battery")) || (token.equalsIgnoreCase("Processor"))){
			return true;
		}
		else
			return false;
	}
	
	static String getDimension(String token){
		if(token.equalsIgnoreCase("touchscreen")){
			return "inch";
		}
		if(token.equalsIgnoreCase("RAM")){
			return "GB";
		}
		if(token.equalsIgnoreCase("camera")){
			return "MP";
		}
		if(token.equalsIgnoreCase("battery")){
			return "mAH";
		}
		if(token.equalsIgnoreCase("processor")){
			return "ghz";
		}
		else{
			return "";
		}
	}
	
	static String extractTitleFromWebsite(String websiteName, String productTitle){
		String title = "";
		if(websiteName.equals("Flipkart")){
			String tokens[] = productTitle.split("\\|");
			title = removeUnwantedCharactersFromTitle(tokens[1]);
		}
		else if(websiteName.equals("Amazon")){
			String tokens[] = productTitle.split(":");
			title = removeUnwantedCharactersFromTitle(tokens[0]);
		}
		return title;
	}
	
	static String removeUnwantedCharactersFromTitle(String title){
		title = title.replaceAll("[(),]", "");
		title = title.replaceAll("Gold", "");
		title = title.replaceAll("Edition", "");
		title = title.replaceAll("Standard", "");
		title = title.replaceAll("Black", "");
		title = title.replaceAll("Jet", "");
		title = title.replaceAll("  ", " ");
		title = title.replaceAll("Mobile Phone Online at Best Price in India", "");
		title = title.replaceAll("Buy ", " ");
		return title;
	}
}

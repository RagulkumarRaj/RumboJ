package com.rumboj.services.comparisonService;

import java.io.File;
import java.io.IOException;
import java.util.List;


import org.apache.log4j.Logger;

import com.google.common.io.Files;
import com.google.gson.JsonObject;

public class PhoneComparator extends AbstractComparator{
	
	final static Logger logger = Logger.getLogger(PhoneComparator.class);
	private static final String PATH_TO_STORE_DETAILS = "C://Data//ReducedPhoneDetails//";
	
	@Override
	public List<JsonObject> getAggregateResult(String searchString) {
		List<JsonObject> prods = searchEngine.getSearchResults(searchString);
		return prods;
	}

	@Override
	public JsonObject prepareComparisonData(String website, String productTitle, String data, String price) {
		productTitle = TechSpecificationHelper.extractTitleFromWebsite(website, productTitle);
		
		try {
			Files.write(data.getBytes(),new File(PATH_TO_STORE_DETAILS+website+"_"+productTitle+"_"+price+".txt"));
		} catch (IOException e1) {
			logger.error(e1.getMessage());
		}
		
		List<JsonObject> list = searchEngine.getSearchResults(productTitle);
		if(list.size() == 0){
			try{
			if(website.equals("Flipkart")){
				JsonObject prod = extractFlipkartTechSpecs(data.split(","), price, productTitle);
				logger.info("Adding to Product Index :: "+prod);
				searchEngine.addProductDatatoIndex(prod);
			}
			else if(website.equals("Amazon")){
				JsonObject prod = extractAmazonTechSpecs(data.split("\\|"), price, productTitle);
				logger.info("Adding to Product Index :: "+prod);
				searchEngine.addProductDatatoIndex(prod);
			}
			}
			catch(ArithmeticException | IOException e){
				logger.error(e.getMessage());
			}
		}
		else{
			JsonObject prod = list.get(0);
			//searchEngine.updateProduct(prod);
			if(website.equals("Flipkart")){
				logger.info("Updating multiple prices :: "+prod.get("title").getAsString());
				prod.addProperty("flipkartprice", price);
				searchEngine.updateProduct(prod);
			}
			else{
				logger.info("Updating multiple prices :: "+prod.get("title").getAsString());
				prod.addProperty("amazonprice", price);
				searchEngine.updateProduct(prod);
			}
		}
		
		//Check if the product title is existing in the index
		//If yes, check if the price for the current website exists.If no, add
		//If product does not exist, add the text to the index
		//Each time you insert, split the text into tokens and then add the content
		/*
		 * if(searchString exists){
		 * check the website name and add website price accordingly
		 * }
		 * else{
		 * Add the content to the index
		 * Add the website price accordingly
		 * }
		 * 
		 * 
		 */
		return null;
	}
	
	//Takes an argument content[]..That is array of lines
	private JsonObject extractAmazonTechSpecs(String content[], String price, String title){
		JsonObject prod = new JsonObject();
		prod.addProperty("title", title);
		prod.addProperty("searchstring", title);
		prod.addProperty("amazonprice", price);
		for(int i=0; i<content.length; i++){
			String tokens[] = content[i].split(",");
			boolean numberExtracted = false;
			float num = 0;
			for(int j=0; j<tokens.length; j++){
				for(String tok : tokens[j].split(" ")){
					if(!numberExtracted){
						num = TechSpecificationHelper.extractNumbersFromString(tok);
						if(num != 0){
							numberExtracted = true;		
						}
					}
					if(numberExtracted){
						if(TechSpecificationHelper.interestedTokens(tok)){
							prod.addProperty(tok.toLowerCase(), num+" "+TechSpecificationHelper.getDimension(tok));
							break;
						}
					}
				}
			}
		}
		return prod;
	}
	
	private JsonObject extractFlipkartTechSpecs(String content[], String price, String title){
		JsonObject prod = new JsonObject();
		prod.addProperty("title", title);
		prod.addProperty("searchstring", title);
		prod.addProperty("flipkartprice", price);
		for(int i=0; i<content.length; i++){
			String tokens[] = content[i].split("\\|");
			boolean numberExtracted = false;
			float num = 0;
			for(int j=0; j<tokens.length; j++){
				String token = tokens[j];
				for(String tok : tokens[j].split(" ")){
				if(!numberExtracted){
					num = TechSpecificationHelper.extractNumbersFromString(tok);
					if(num != 0){
						numberExtracted = true;		
					}
				}
				if(numberExtracted){
					if(TechSpecificationHelper.interestedTokens(tok)){
						prod.addProperty(tok.toLowerCase(), num+" "+TechSpecificationHelper.getDimension(tok));
						break;
					}
				}
				}
			}
		}
		return prod;
	}
}

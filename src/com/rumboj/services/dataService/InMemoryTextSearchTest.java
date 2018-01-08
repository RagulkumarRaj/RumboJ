package com.rumboj.services.dataService;

import java.io.IOException;

import org.apache.lucene.index.CorruptIndexException;

import com.google.gson.JsonObject;

public class InMemoryTextSearchTest {

	public static void main(String[] args) throws CorruptIndexException, IOException {
		InMemoryTextSearchEngine search = InMemoryTextSearchEngine.getInstance();
		JsonObject prod = new JsonObject();
		
		prod.addProperty("Name", "Nexus 5x");
		prod.addProperty("RAM", "1 GB");
		prod.addProperty("Processor", "1 Ghz");
		prod.addProperty("FlipkartPrice", "25000");
		
		search.addProductDatatoIndex(prod);
		
		prod = new JsonObject();
		prod.addProperty("Name", "Nexus 6p");
		prod.addProperty("RAM", "3 GB");
		prod.addProperty("Processor", "3 Ghz");
		prod.addProperty("FlipkartPrice", "33000");
		
		search.addProductDatatoIndex(prod);
		
		search.getSearchResults("3 AND Ghz");
		search.getSearchResults("1 AND Ghz");
		search.getSearchResults("Nexus AND 5x");
	}
}

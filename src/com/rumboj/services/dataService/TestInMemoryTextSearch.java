package com.rumboj.services.dataService;

import java.io.IOException;

import org.apache.lucene.index.CorruptIndexException;

public class TestInMemoryTextSearch {
	public static void main(String args[]) throws CorruptIndexException, IOException {
		InMemoryTextSearchEngine obj = InMemoryTextSearchEngine.getInstance();
		obj.createDocument("Flipkart", "Nexus 5x", "google phone");
		obj.createDocument("Amazon", "Nexus 5x", "awesome phone");
		obj.createDocument("Amazon", "LG G2", "superb phone");
		obj.createDocument("Amazon", "Pixel", "superb phone");
		String text = obj.search("Flipkart","google");
		//text = obj.search("Amazon","google");
		text = obj.search("Amazon","Nexus 5x");
		text = obj.search("Amazon","awesome");
		System.out.println(text);
	}
}

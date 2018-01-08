package com.rumboj.services.dataService;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Hit;
import org.apache.lucene.search.Hits;
import org.apache.lucene.search.MultiPhraseQuery;
import org.apache.lucene.search.PhraseQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.document.Field;
import org.apache.lucene.search.Searcher;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexDeletionPolicy;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.index.TermDocs;
import org.apache.lucene.document.Document;
import org.apache.lucene.store.LockObtainFailedException;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.SimpleAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import com.rumboj.crawlers.scraperRobot.AbstractInfoScraperBot;
import com.rumboj.services.backgroundService.SearchResultsReadWriteService;

public class InMemoryTextSearchEngine {
	// Final class
	final static Logger logger = Logger.getLogger(InMemoryTextSearchEngine.class);
	public static InMemoryTextSearchEngine searchEngine;
	private RAMDirectory ramDirectory;
	private IndexWriter writer;
	private Searcher searcher;

	private InMemoryTextSearchEngine() {
		try {
			init();
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
	}

	/**
	 * 
	 * @throws CorruptIndexException
	 * @throws LockObtainFailedException
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	private void init() throws CorruptIndexException,
			LockObtainFailedException, IOException, FileNotFoundException {
		//Get the ramdir object..loaded from directory.getDir FSDIr
		ramDirectory = SearchResultsReadWriteService.readSearchResultsData();

		//New Change
		//If ramdir is empty, creating a writer with false as arg will fail..Thats why true is passed to writer
		if(ramDirectory.sizeInBytes() == 0){
			writer = new IndexWriter(ramDirectory, new StandardAnalyzer(), true);	
		}
		else{
			//If ramdir is not empty, creating a writer with true as arg will fail..Thats why false is passed to writer
			writer = new IndexWriter(ramDirectory, new StandardAnalyzer(), false);
		}
	}

	/**
	 * Singleton instance used across application
	 * @return
	 */
	public static InMemoryTextSearchEngine getInstance() {
		if (searchEngine == null) {
			synchronized (InMemoryTextSearchEngine.class) {
				if (searchEngine == null) {
					searchEngine = new InMemoryTextSearchEngine();
				}
			}
		}
		return searchEngine;
	}

/**
 * Working getSearchResults method. Others with private not used
 * First searches for the string. And then returns the result as an array
 * Each time a search is made, creating an object of Searcher ensures that the 
 * index search is up to date
 * @param searchString
 * @return
 */
	public List<JsonObject> getSearchResults(String searchString) {
		List<JsonObject> productList = new ArrayList<JsonObject>();
		try {
			logger.info("Searching Index for search string :: "+searchString);
			Analyzer analyzer = new StandardAnalyzer();
			searcher = new IndexSearcher(ramDirectory);
			MultiFieldQueryParser queryParser = new MultiFieldQueryParser(
					new String[] { "searchstring", "ram", "battery" }, analyzer);

			Hits hits = searcher.search(queryParser.parse(searchString));

			Iterator<Hit> iter = hits.iterator();
			while (iter.hasNext()) {
				Hit hit = iter.next();
				String content = hit.get("content");
				JsonParser parse = new JsonParser();
				JsonObject obj = parse.parse(content).getAsJsonObject();
				productList.add(obj);
			}
			logger.info("Found "+hits.length()+" results for search string :: "+searchString);
		} catch (IOException | ParseException e) {
			logger.error(e.getMessage());
		}
		return productList;
	}

	/**
	 * Adding a product to Index
	 * @param prod
	 * @throws CorruptIndexException
	 * @throws IOException
	 */
	public void addProductDatatoIndex(JsonObject prod)
			throws CorruptIndexException, IOException {
		Document doc = getDocumentFromJSON(prod);
		writer.addDocument(doc);
		writer.optimize();
	}
	
	/**
	 * Remember. Update method works only with Term Query. Always update will work only for
	 * terms that are unique. Searching for a term returns a single result. Only then UpdateDOc works.
	 * If Update is not working, then your query is returning more than one docuemnt.
	 * Here I have used TermQuery on title field which is Untokenized
	 * @param prod
	 * @return
	 */
	public boolean updateProduct(JsonObject prod){
		try {
 			Term term = new Term("title", prod.get("title").getAsString());
			Document doc = getDocumentFromJSON(prod);
			writer.updateDocument(term, doc);
            writer.optimize();
			return true;
		} catch (IOException  e) {
			logger.error(e.getMessage());
			return false;
		}
	}
	
	/**
	 * Preparing a Document out of a JSON. Mainly to store product details to Index
	 * @param prodObject
	 * @return
	 */
	private Document getDocumentFromJSON(JsonObject prodObject){
		Document prodDoc = new Document();
		prodDoc.add(new Field("searchstring", prodObject.get("searchstring").getAsString(),
				Field.Store.YES, Field.Index.TOKENIZED));
		prodDoc.add(new Field("title", prodObject.get("title").getAsString(),
				Field.Store.YES, Field.Index.UN_TOKENIZED));
		prodDoc.add(new Field("ram", prodObject.get("ram").getAsString(),
				Field.Store.YES, Field.Index.TOKENIZED));
		prodDoc.add(new Field("processor", prodObject.get("processor").getAsString(),
				Field.Store.YES, Field.Index.TOKENIZED));
		prodDoc.add(new Field("content", prodObject.toString(), Field.Store.YES,
				Field.Index.NO));
		return prodDoc;
	}
}

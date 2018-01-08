package com.rumboj.services.dataService;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.Hits;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.Searcher;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.RAMDirectory;

public class InMemoryTest {
	static RAMDirectory rDir;
	static IndexWriter writer;
	static Searcher searcher;

	static{
		rDir = new RAMDirectory();
		try {
			writer = new IndexWriter(rDir, new StandardAnalyzer(), true);
		} catch (IOException e) {}
	}
	public static void main(String[] args) throws CorruptIndexException, IOException {
		InMemoryTest test = new InMemoryTest();
		try {
			test.createDocument(
					"Theodore Roosevelt",
					"It behooves every man to remember that the work of the "
							+ "critic, is of altogether secondary importance, and that, "
							+ "in the end, progress is accomplished by the man who does "
							+ "things.");
			test.createDocument(
					"Theodore",
					"It behooves every man to remember that the work of the "
							+ "things.");			
			test.createDocument(
					"HAHA Hayek",
					"The case for individual freedom rests largely on the "
							+ "recognition of the inevitable and universal ignorance "
							+ "of all of us concerning a great many of the factors on "
							+ "which the achievements of our ends and welfare depend.");
			test.createDocument(
					"Ayn Rand",
					"There is nothing to take a man’s freedomee away from "
							+ "him, save other men. To be free, a man must be free "
							+ "of his brothers.");
			test.createDocument("Mohandas Gandhi",
					"Freedom is not worth having if it does not connote "
							+ "freedom to err.");
			//test.search(searcher, "Theodore");
			//test.search(searcher, "Ayn Rand");
			//writer = new IndexWriter(rDir, new StandardAnalyzer());
			
			writer.close(); 
			test.search(searcher, "Theodore");
			// searcher.close();
		} catch (IOException ioe) {
			// In this example we aren’t really doing an I/O, so this
			// exception should never actually be thrown.
			ioe.printStackTrace();
		} catch (ParseException pe) {
			pe.printStackTrace();
		}
	}
	
	private int index(File indexDir, File dataDir, String suffix) throws Exception {
	    Directory.copy(rDir, FSDirectory.getDirectory(indexDir), false); // 3

	}
	
	/**
	 * Make a Document object with an un-indexed title field and an indexed
	 * content field.
	 * @throws IOException 
	 * @throws CorruptIndexException 
	 */
	private Document createDocument(String title, String content) throws CorruptIndexException, IOException {
		Document doc = new Document();
		// Add the title as an unindexed field…
		doc.add(new Field("searchstring", title, Field.Store.YES, Field.Index.TOKENIZED));
		doc.add(new Field("title", title, Field.Store.YES, Field.Index.UN_TOKENIZED));
		// …and the content as an indexed field. Note that indexed
		// Text fields are constructed using a Reader. Lucene can read
		// and index very large chunks of text, without storing the
		// entire content verbatim in the index. In this example we
		// can just wrap the content string in a StringReader.
		doc.add(new Field("content", content, Field.Store.YES, Field.Index.TOKENIZED));
		writer.addDocument(doc);
		writer.optimize();
		return doc;
	}

	/**
	 * Searches for the given string in the "content" field
	 */
	private void search(Searcher searcher, String queryString)
			throws ParseException, IOException {
		searcher = new IndexSearcher(rDir);
		
		//TermQuery termQuery = new TermQuery(new Term("title", queryString));
		QueryParser parser = new QueryParser("searchstring", new StandardAnalyzer());
		//QueryParser parser = new QueryParser("title", new StandardAnalyzer());
		Query query = parser.parse(queryString);
		// Search for the query
		Hits hits = searcher.search(query);
		// Examine the Hits object to see if there were any matches
		int hitCount = hits.length();
		if (hitCount == 0) {
			System.out.println("No matches were found for \"" + queryString
					+ "\"");
		} else {
			System.out.println("Hits for \"" + queryString
					+ "\" were found in quotes by:");
			// Iterate over the Documents in the Hits object
			for (int i = 0; i < hitCount; i++) {
				Document doc = hits.doc(i);
				// Print the value that we stored in the "title" field. Note
				// that this Field was not indexed, but (unlike the
				// "contents" field) was stored verbatim and can be
				// retrieved.
				System.out.println(" " + (i + 1) + ". " + doc.get("title")+" "+doc.get("content"));
			}
		}
		System.out.println();
	}
}

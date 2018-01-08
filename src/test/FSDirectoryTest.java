package test;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.Hits;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.Searcher;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.RAMDirectory;

import com.rumboj.services.dataService.InMemoryTest;

public class FSDirectoryTest {
	static FSDirectory rDir;
	static IndexWriter writer;
	static Searcher searcher;

	static{
		try {
			rDir = FSDirectory.getDirectory("C:\\Data\\Data1");
			writer = new IndexWriter(rDir, new StandardAnalyzer(), false);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	public static void main(String[] args) throws CorruptIndexException, IOException, ParseException {
		FSDirectoryTest test = new FSDirectoryTest();
		test.search(searcher, "Salma");
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

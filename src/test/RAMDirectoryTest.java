package test;

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

public class RAMDirectoryTest {
	static FSDirectory dir;
	static RAMDirectory rdir;
	static IndexWriter writer;
	static Searcher searcher;
	
	static FSDirectory destDir;

	static{
		try {
			dir = FSDirectory.getDirectory("C:\\Data\\SearchResults");
			rdir = new RAMDirectory(dir);
			if(rdir.sizeInBytes() == 0){
				rdir = new RAMDirectory();
				writer = new IndexWriter(rdir, new StandardAnalyzer(), true);
			}
			else{
				writer = new IndexWriter(rdir, new StandardAnalyzer(), false);
			}
			destDir = FSDirectory.getDirectory("C:\\Data\\SearchResults");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	public static void main(String[] args) throws CorruptIndexException, IOException {
		RAMDirectoryTest test = new RAMDirectoryTest();
		try {
			//test.search(searcher, "Theodore");
			//test.search(searcher, "Ayn Rand");
			//writer = new IndexWriter(rDir, new StandardAnalyzer());
			//test.search(searcher, "Salma");
			test.createDocument("Tom Cruise", "Fair enough");
			test.createDocument("Sridevi", "hot");
			test.createDocument("Salma", "mexican");
			
			test.search(searcher, "Tom", rdir);
			test.search(searcher, "Sridevi", rdir);
			test.search(searcher, "Salma", rdir);	
			//Do not close the ramdir while copying files
			Directory.copy(rdir, destDir, false);
			test.search(searcher, "Tom", destDir);
			//writer.close(); 
			// searcher.close();
		} catch (IOException ioe) {
			// In this example we aren’t really doing an I/O, so this
			// exception should never actually be thrown.
			ioe.printStackTrace();
		} catch (ParseException pe) {
			pe.printStackTrace();
		}
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
		//if false, means to merge with the existing index, will throw error if rdir is empty
		//writer = new IndexWriter(rdir, new StandardAnalyzer(), true);
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
	private void search(Searcher searcher, String queryString, Directory rdir)
			throws ParseException, IOException {
		searcher = new IndexSearcher(rdir);
		
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

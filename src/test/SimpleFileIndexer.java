package test;

import org.apache.lucene.analysis.SimpleAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.store.FSDirectory;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class SimpleFileIndexer {
    public static void main(String[] args) throws Exception {
        File indexDir = new File("C:/Users/Raden/Documents/lucene/LuceneHibernate/adi");
        File dataDir = new File("C:/Users/Raden/Documents/lucene/LuceneHibernate/adi");
        String suffix = "txt";
        SimpleFileIndexer indexer = new SimpleFileIndexer();
        int numIndex = indexer.index(indexDir, dataDir, suffix);
        System.out.println("Total files indexed " + numIndex);
    }

    private int index(File indexDir, File dataDir, String suffix) throws Exception {
        RAMDirectory ramDir = new RAMDirectory();          // 1
        IndexWriter indexWriter = new IndexWriter(
                ramDir,                                    // 2
                new SimpleAnalyzer(),
                true,
                IndexWriter.MaxFieldLength.LIMITED);
        indexWriter.setUseCompoundFile(false);
        indexDirectory(indexWriter, dataDir, suffix);
        int numIndexed = indexWriter.maxDoc();
        indexWriter.optimize();
        indexWriter.close();

        Directory.copy(ramDir, FSDirectory.open(indexDir), false); // 3

        return numIndexed;
    }

    private void indexDirectory(IndexWriter indexWriter, File dataDir, String suffix) throws IOException {
        File[] files = dataDir.listFiles();
        for (int i = 0; i < files.length; i++) {
            File f = files[i];
            if (f.isDirectory()) {
                indexDirectory(indexWriter, f, suffix);
            } else {
                indexFileWithIndexWriter(indexWriter, f, suffix);
            }
        }
    }

    private void indexFileWithIndexWriter(IndexWriter indexWriter, File f, String suffix) throws IOException {
        if (f.isHidden() || f.isDirectory() || !f.canRead() || !f.exists()) {
            return;
        }
        if (suffix != null && !f.getName().endsWith(suffix)) {
            return;
        }
        System.out.println("Indexing file " + f.getCanonicalPath());
        Document doc = new Document();
        doc.add(new Field("contents", new FileReader(f)));
        doc.add(new Field("filename", f.getCanonicalPath(), Field.Store.YES, Field.Index.ANALYZED));
        indexWriter.addDocument(doc);
    }
}

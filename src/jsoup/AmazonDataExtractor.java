package jsoup;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

public class AmazonDataExtractor {
	public static void reduce(String pageData, String filePath) throws IOException {
		//File inFile = new File("C:\\PhantomJS\\PhantomJSAmazonNexus5x.html");
		//Document doc = Jsoup.parse(inFile, "UTF-8", "");
		Document doc = Jsoup.parse(pageData);
		Elements elements = doc.select("table"); // a with href
		Iterator<Element> it = elements.iterator();
		
		File outputFile = new File(filePath);
		FileWriter fw = new FileWriter(outputFile);
		BufferedWriter bw = new BufferedWriter(fw);
		
		while (it.hasNext()) {
			Element ele = it.next();
			bw.write(ele.outerHtml());
		}
		bw.close();
		fw.close();
	}
}

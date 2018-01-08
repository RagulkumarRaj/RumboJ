package jsoup;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Application {
public static void main(String[] args) throws IOException {
	File input = new File("C:\\PhantomJS\\Flipkart.html");
	Document doc = Jsoup.parse(input, "UTF-8");
	
	//Elements elements = doc.select("a.a-link-normal.s-access-detail-page"); // a with href
	//Elements elements = doc.select("a.a-link-normal.s-access-detail-page"); // a with href
	Elements elements = doc.select("a._1UoZlX");
	Iterator<Element> it = elements.iterator();
	
	while (it.hasNext()) {
		Element ele = it.next();
		System.out.println(ele.outerHtml());
	}
}
}

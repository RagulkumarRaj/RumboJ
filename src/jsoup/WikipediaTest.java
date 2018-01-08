package jsoup;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WikipediaTest {
	public static void main(String[] args) throws
			 MalformedURLException, IOException {
		File inFile = new File("C:\\Data\\1.html");
		Document doc = Jsoup.parse(inFile, "UTF-8", "");

		Elements elements = doc.select("img[class=_1Nyybr]"); // a with href

		Iterator<Element> it = elements.listIterator();
		while (it.hasNext()) {
			Element ele = it.next();
			// System.out.println(ele.attr("data-aid")); }
			System.out.println(ele.attr("alt"));
			System.out.println();
		}
	}
}

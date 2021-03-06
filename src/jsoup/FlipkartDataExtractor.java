package jsoup;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

import org.eclipse.jdt.internal.compiler.batch.Main;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

public class FlipkartDataExtractor {
	public static void reduce(String pageData, String filePath) throws IOException {
		//File inFile = new File("C:\\PhantomJS\\PhantomJSFlipkartNexus5x.html");
		Document doc = Jsoup.parse(pageData);
		Elements elements = doc.select("div[class=_2PF8IO]"); // a with href
		Iterator<Element> it = elements.iterator();
		
		File outputFile = new File(filePath);
		FileWriter fw = new FileWriter(outputFile);
		BufferedWriter bw = new BufferedWriter(fw);
		
		while (it.hasNext()) {
			Element ele = it.next();
			for (int i = 0; i < ele.childNodeSize(); i++) {
				Node ele1 = ele.childNode(i);
				for (int j = 0; j < ele1.childNodeSize(); j++) {
					Node ele2 = ele1.childNode(j);
					System.out.println(ele2.outerHtml());
					bw.write(ele2.outerHtml());
				}
			}
		}
		bw.close();
		fw.close();
	}
}

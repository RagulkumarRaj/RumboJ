package test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.rumboj.services.downloadService.JBrowserHtmlPageDownloadService;

public class HelloWorldSuper {

	private JBrowserHtmlPageDownloadService serv; 
	public HelloWorldSuper() {
		System.out.println("inside HelloWorldSUper cons");
	}
	public JBrowserHtmlPageDownloadService getServ() {
		System.out.println("inside getServ");
		return serv;
	}
	@Autowired
	@Qualifier("jBrowserHtmlPageDownloadService")
	public void setServ(JBrowserHtmlPageDownloadService serv) {
		System.out.println("inside setServ");
		this.serv = serv;
	}
}

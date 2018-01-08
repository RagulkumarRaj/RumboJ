package com.rumboj.crawlers.botDelegators;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.rumboj.services.downloadService.HtmlPageDownloadService;
import com.rumboj.workflow.ProcessManager;

public abstract class AbstractInfoScraperBotDelegator implements InfoScraperBotDelegator {
	final static Logger logger = Logger.getLogger(AbstractInfoScraperBotDelegator.class);
	private HtmlPageDownloadService browserService;
	private ProcessManager processManager = ProcessManager.getInstance();
    public boolean isStopSignalReceived = false; 
    static final Integer NUM_OF_RESULTS = 7;
	public AbstractInfoScraperBotDelegator() {
	}

	@Override
	public void run() {
		logger.info("Thread " + this.getClass().getSimpleName() + " started at "
				+ new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar
						.getInstance().getTime()));
		scrapeAllPhoneLinks();
		scrapeAllWatchLinks();
	}

	public abstract void scrapeAllPhoneLinks();

	public abstract void scrapeAllWatchLinks();
	
	public abstract List<Element> tryCombinationForClassName(Document doc);

	public void setProcessManagerService(
			ProcessManager processManager) {
		this.processManager = processManager;
	}

	public ProcessManager getProcessManagerService() {
		return processManager;
	}

	public HtmlPageDownloadService getBrowserService() {
		return browserService;
	}

	@Autowired
	@Qualifier("pageDownloadService")
	public void setBrowserService(HtmlPageDownloadService browserService) {
		this.browserService = browserService;
	}

	public String downloadProductListPage(String url) {
		String pageData = this.getBrowserService().downloadPageAsString(url);
		return pageData;
	}
	
	public boolean checkIfUrlisDownloaded(String urls){
		
		return false;
	}

	@Override
	public boolean isStopSignalReceived(){
		boolean isStopSignal =Thread.currentThread().isInterrupted();
		return isStopSignal;
	}

	public String toString() {
		return this.getClass().getSimpleName();
	}
}

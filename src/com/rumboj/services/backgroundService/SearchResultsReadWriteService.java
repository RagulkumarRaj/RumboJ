package com.rumboj.services.backgroundService;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.log4j.Logger;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.RAMDirectory;
import org.springframework.stereotype.Component;

@Component
public class SearchResultsReadWriteService {

	final static Logger logger = Logger
			.getLogger(SearchResultsReadWriteService.class);
	private ScheduledExecutorService scheduledExecutorService;
    private static final String DATA_DIRECTORY = "C:\\Data\\SearchResults";
    private static FSDirectory fsDirectory;
    private static RAMDirectory ramDirectory;
    
	public static void writeSearchResultsData() throws IOException{
		fsDirectory = FSDirectory.getDirectory(DATA_DIRECTORY);
		Directory.copy(ramDirectory, fsDirectory, false);
	}
	
	public static void writeSearchResultsData(RAMDirectory rDir) throws IOException{
		fsDirectory = FSDirectory.getDirectory(DATA_DIRECTORY);
		Directory.copy(rDir, fsDirectory, false);
	}
	
	public static RAMDirectory readSearchResultsData() throws IOException{
		fsDirectory = FSDirectory.getDirectory(DATA_DIRECTORY);
		ramDirectory = new RAMDirectory(fsDirectory);
		return ramDirectory;
	}
	
	
	@PostConstruct
	public void startService() {
		//Convert RAMDirectory to FSDirectory
		//Convert FSDirectory to RAMDirectory
		Runnable task = new Runnable() {
			@Override
			public void run() {
				try {
					logger.info("Writing Search results data");
					writeSearchResultsData();
				} catch (Exception e) {
					logger.error("indexing failed", e);
				}
			}
		};
		scheduledExecutorService = Executors.newScheduledThreadPool(1);
		scheduledExecutorService.scheduleAtFixedRate(task, 10, 3, TimeUnit.MINUTES);
	}

	@PreDestroy
	public void destroyService() {
		if (scheduledExecutorService != null) {
			try {
				// wait 1 second for closing all threads
				scheduledExecutorService.awaitTermination(1, TimeUnit.SECONDS);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
	}
}
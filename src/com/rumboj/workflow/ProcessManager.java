package com.rumboj.workflow;

import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.rumboj.core.InterruptibleWorkThread;
import com.rumboj.crawlers.main.BotDelegatorController;
import com.rumboj.services.dataService.InMemoryTextSearchEngine;

public class ProcessManager implements IProcessManager{

	final static Logger logger = Logger.getLogger(ProcessManager.class);
	
	public static ProcessManager processManager;
	//private ScheduledExecutorService  sheduledExecutorService;
	private ExecutorService  sheduledExecutorService;
	final Random random = new Random();
	
	private ProcessManager(){
		sheduledExecutorService = Executors.newScheduledThreadPool(1, new TimeIntervalThreadFactory());
	}
	
	public static ProcessManager getInstance() {
		if (processManager == null) {
			synchronized (ProcessManager.class) {
				if (processManager == null) {
					processManager = new ProcessManager();
				}
			}
		}
		return processManager;
	}
	
	@Override
	public void submitTask(InterruptibleWorkThread task) {
		sheduledExecutorService.submit(task);
	}
	
	@Override
	public boolean interupptTask(Runnable task) {
		return false;
	}

	@Override
	public void startAllTasks() {
		// TODO Auto-generated method stub
	}

	@Override
	public void stopAllTasks() {
		sheduledExecutorService.shutdownNow();
	}

	@Override
	public boolean getTaskStatus(Runnable task) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void limitNumofThreads(int n) {
		// TODO Auto-generated method stub
	}

	@Override
	public void timeoutTask(Runnable task) {
		// TODO Auto-generated method stub
	}
   
}

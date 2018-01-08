package com.rumboj.workflow;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

class TimeIntervalThreadFactory implements ThreadFactory{
private static final ThreadFactory defaultFactory = Executors.defaultThreadFactory();
private final Integer BASE_TIME = 3500;
	@Override
	public Thread newThread(Runnable r) {
		int randomSleepTime = new Random().nextInt((int) 3000L);
		try {
			Thread.sleep(randomSleepTime + BASE_TIME);
			final Thread t = defaultFactory.newThread(r);
			Thread.sleep(randomSleepTime + BASE_TIME);
			return t;
		} catch (InterruptedException e) {
			return null;
		} 
		
	}
}

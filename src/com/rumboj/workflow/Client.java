package com.rumboj.workflow;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Client {
	private final static ScheduledExecutorService executor = Executors.newScheduledThreadPool(10);

    public static void main(String[] args) {
        final Random random = new Random();
        final long maxSleepTime=2000L;
        for (int i = 0; i < 10; i++) {
            int randomSleepTime = random.nextInt((int) maxSleepTime);
            Runnable command = ()  -> {System.out.println("Thread Started"); };
            executor.scheduleAtFixedRate(command,randomSleepTime,randomSleepTime, TimeUnit.MILLISECONDS);
        }
    }
}

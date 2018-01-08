package com.rumboj.workflow;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TestProcessorManager {
  public static void main(String args[]){
	  
	   Random r = new Random();
	   int randomSleepTime = new Random().nextInt((int) 3000L);
	   System.out.println(1500 + randomSleepTime);
  }
}

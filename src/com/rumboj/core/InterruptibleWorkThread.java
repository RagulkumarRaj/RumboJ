package com.rumboj.core;

public interface InterruptibleWorkThread extends Runnable{
   public boolean isStopSignalReceived();
}

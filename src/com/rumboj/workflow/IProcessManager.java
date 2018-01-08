package com.rumboj.workflow;

import com.rumboj.core.InterruptibleWorkThread;

public interface IProcessManager {
   public void submitTask(InterruptibleWorkThread task);
   public boolean interupptTask(Runnable task);
   public void startAllTasks();
   public void stopAllTasks();
   public boolean getTaskStatus(Runnable task);
   public void limitNumofThreads(int n);
   public void timeoutTask( Runnable task);
}

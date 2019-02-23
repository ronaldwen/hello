package com.ronald;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * 
 * @author ronald
 * @date 2016年6月6日上午11:32:48
 * 
 */
public class TestTimerPool {

	final static ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
	
	
	public static void main(String[] args) {
		TestTimerPool t = new TestTimerPool();
		ScheduledFuture<?> scheduleWithFixedDelay = null;
		for (int i = 0; i < 3; i++) {
			
			scheduleWithFixedDelay = executorService.scheduleWithFixedDelay(t.new Task1("task" + i), 1000, 2000, TimeUnit.MILLISECONDS);
		}
		try {
			Thread.sleep(30000);
			System.out.println("执行");
			scheduleWithFixedDelay.cancel(false);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	class Task1 implements Runnable {

		private String taskName;
		private int i = 0;
		
		public Task1() {
			super();
		}

		public Task1(String taskName) {
			super();
			this.taskName = taskName;
		}

		@Override
		public void run() {
			i++;
			System.out.println(taskName + "--定时器执行。。。" + i + ":" + new Date());
			if(i == 5) {
//				executorService.shutdown();
			}
		}
		
	}
	
	
}



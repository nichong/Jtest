/**
 * 
 */
package com.wh.test.thread.future.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author weiH
 *
 */
public class Executor {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
		for (int i = 0; i < 10; i++) {
			    int index = i;  
			   try {  
			    Thread.sleep( (10-index) * 1000);  
			   } catch (InterruptedException e) {  
			    e.printStackTrace();  
			   } 
			   
			   cachedThreadPool.execute(new Runnable(){

				@Override
				public void run() {
					System.out.println(index + "," + Thread.currentThread().getId());
				}
				   
			   });
		}
		
		cachedThreadPool.shutdown();
	}

}

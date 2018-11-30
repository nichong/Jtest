/**
 * 
 */
package com.wh.test.thread.future.executor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author weiH
 *
 */
public class BlockQuer {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		BlockingQueue queue = new ArrayBlockingQueue(1024);
		queue.put("1");
		queue.put("2");
		Object object = queue.take();
		System.out.println(object);
	}

}

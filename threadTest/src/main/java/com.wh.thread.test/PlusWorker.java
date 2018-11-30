package com.wh.thread.test;

/**利用Master-Worker模式实现计算立方和的应用。计算1^3+2^3+3^3+…+100^3。
         这个计算任务被划分成100个子任务，每个任务仅仅用于计算单独的立方和。
 * @author weiH
 *
 */
public class PlusWorker extends Worker { //求立方和 
	  @Override  
	    public Object handle(Object input) {  
	        int i = (Integer)input;  
	        //return i * i * i;
		return "hello" + i;
	    }
}

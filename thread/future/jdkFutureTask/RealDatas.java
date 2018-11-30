package com.wh.test.thread.future.jdkFutureTask;

import java.util.concurrent.Callable;

/**
 *  * @author weiH
 *Callable的说明：产生结果
 * 
 * 
 * Future模式的JDK内置实现
由于Future是非常常用的多线程设计模式，因此在JDK中内置了Future模式的实现。这些类在java.util.concurrent包里面。
其中最为重要的是FutureTask类，它实现了Runnable接口，作为单独的线程运行。
在其run()方法中，通过Sync内部类调用Callable接口，并维护Callable接口的返回对象。
当使用FutureTask.get()方法时，将返回Callable接口的返回对象。同样，针对上述的实例，如果使用JDK自带的实现，则需要作如下调整。
首先，Data接口和FutureData就不需要了，JDK帮我们实现了。
其次，RealData改为这样：
 */
public class RealDatas implements Callable<String> {  
    protected String data;  
  
    public RealDatas(String data) {  
        this.data = data;  
    }  
  
    @Override  
    public String call() throws Exception {  
        //利用sleep方法来表示真是业务是非常缓慢的  
        try {  
            Thread.sleep(1000);  
        } catch (InterruptedException e) {  
            e.printStackTrace();  
        }  
        return data;  
    }  
}
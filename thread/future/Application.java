package com.wh.test.thread.future;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.wh.test.thread.future.jdkFutureTask.RealDatas;

//测试运行
//主函数主要负责调用Client发起请求，并使用返回的数据
/**
 * Future模式核心思想
Future模式的核心在于：去除了主函数的等待时间，并使得原本需要等待的时间段可以用于处理其他业务逻辑（根据《Java程序性能优化》）。
Future模式有点类似于商品订单。在网上购物时，提交订单后，在收货的这段时间里无需一直在家里等候，可以先干别的事情。类推到程序设计中时，当提交请求时，期望得到答复时，如果这个答复可能很慢。传统的时一直等待到这个答复收到时再去做别的事情，但如果利用Future设计模式就无需等待答复的到来，在等待答复的过程中可以干其他事情。
例如如下的请求调用过程时序图。当call请求发出时，需要很长的时间才能返回。左边的图需要一直等待，等返回数据后才能继续其他操作；而右边的Future模式的图中客户端则无需等到可以做其他的事情。服务器段接收到请求后立即返回结果给客户端，这个结果并不是真实的结果（是虚拟的结果），也就是先获得一个假数据，然后执行其他操作。
 * @author weiH
 *
 */
public class Application {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
        Client client = new Client();  
        //这里会立即返回，因为获取的是FutureData，而非RealData  
        Data data = client.request("name");  
        //这里可以用一个sleep代替对其他业务逻辑的处理  
        //在处理这些业务逻辑过程中，RealData也正在创建，从而充分了利用等待时间  
        Thread.sleep(2000);  
        //使用真实数据  
        System.out.println("数据="+data.getResult());  
        
        
        //或者利用jdk的运行
        task();
    }  
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void task() throws InterruptedException, ExecutionException{
        FutureTask<String> futureTask = new FutureTask<String>(new RealDatas("name"));  
        ExecutorService executor = Executors.newFixedThreadPool(1); //使用线程池  
        //执行FutureTask，相当于上例中的client.request("name")发送请求  
        executor.submit(futureTask);  
        //这里可以用一个sleep代替对其他业务逻辑的处理  
        //在处理这些业务逻辑过程中，RealData也正在创建，从而充分了利用等待时间  
        Thread.sleep(2000);  
        //使用真实数据  
        //如果call()没有执行完成依然会等待  
        System.out.println("数据=" + futureTask.get()); 
        System.out.println(executor.isShutdown());
        executor.shutdown();
        System.out.println(executor.isShutdown());
        System.out.println("----------------------------------------------");
        
        
        
        //定时任务   使用newScheduledThreadPool来模拟心跳机制
        ScheduledExecutorService sexecutor = Executors.newScheduledThreadPool(5);
        Runnable task = new Runnable() {
            public void run() {
             //   System.out.println("HeartBeat.........................");
            }
        };
        sexecutor.scheduleAtFixedRate(task,5,3, TimeUnit.SECONDS);   //5秒后第一次执行，之后每隔3秒执行一次
        
        
        
        //newCachedThreadPool
        ExecutorService threadPool = Executors.newCachedThreadPool();//线程池里面的线程数会动态变化，并可在线程线被移除前重用
        for (int i = 1; i <= 3; i ++) {
            final  int task0 = i;   //10个任务
            TimeUnit.SECONDS.sleep(1);//放出第6行的注释其输出如下：（始终重复利用一条线程，因为 主线程sleep，之前run方法中用的线程已经放入newCachedThreadPool中了，此时newCachedThreadPool能重用可用线程）
            threadPool.execute(new Runnable() {    //接受一个Runnable实例
                public void run() {
                        System.out.println("线程名字： " + Thread.currentThread().getName() +  "  任务名为： "+task0);
                }
            });
        }
        
        
        ExecutorService executors = Executors.newFixedThreadPool(10);        //创建含10.条线程的线程池
        CompletionService completionService = new ExecutorCompletionService(executors);
        for (int i =1; i <=10; i ++) {
            final  int result = i;
            completionService.submit(new Callable() {
                public Object call() throws Exception {
                    Thread.sleep(new Random().nextInt(5000));   //让当前线程随机休眠一段时间
                    return result;
                }
            });
        }
        System.out.println("in CompletionService:" + completionService.take().get());   //获取执行结果
        
        
        
        
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);  
        for (int i = 0; i < 10; i++) {  
         final int index = i;  
         fixedThreadPool.execute(new Runnable() {  
          public void run() {  
           try {  
            System.out.println("in fixedThreadPool:"+index);  
            Thread.sleep(2000);  
           } catch (InterruptedException e) {  
            e.printStackTrace();  
           }  
          }  
         });  
        }
        fixedThreadPool.shutdown();
        
    }
}

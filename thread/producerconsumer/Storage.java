/**
 * 
 */
package com.wh.test.thread.producerconsumer;

import java.util.concurrent.LinkedBlockingQueue;

/*http://blog.csdn.net/MONKEY_D_MENG/article/details/6251879
 * 
 * 生产者消费者问题是研究多线程程序时绕不开的经典问题之一，它描述是有一块缓冲区作为仓库，生产者可以将产品放入仓库，消费者则可以从仓库中取走产品。解决生产者/消费者问题的方法可分为两类：（1）采用某种机制保护生产者和消费者之间的同步；（2）在生产者和消费者之间建立一个管道。第一种方式有较高的效率，并且易于实现，代码的可控制性较好，属于常用的模式。第二种管道缓冲区不易控制，被传输数据对象不易于封装等，实用性不强。因此本文只介绍同步机制实现的生产者/消费者问题。
同步问题核心在于：如何保证同一资源被多个线程并发访问时的完整性。常用的同步方法是采用信号或加锁机制，保证资源在任意时刻至多被一个线程访问。Java语言在多线程编程上实现了完全对象化，提供了对同步机制的良好支持。在Java中一共有四种方法支持同步，其中前三个是同步方法，一个是管道方法。
（1）wait() / notify()方法
（2）await() / signal()方法
（3）BlockingQueue阻塞队列方法
（4）PipedInputStream / PipedOutputStream
本文只介绍最常用的前三种，第四种暂不做讨论，有兴趣的读者可以自己去网上找答案。
 
一、wait() / notify()方法
wait() / nofity()方法是基类Object的两个方法，也就意味着所有Java类都会拥有这两个方法，这样，我们就可以为任何对象实现同步机制。
wait()方法：当缓冲区已满/空时，生产者/消费者线程停止自己的执行，放弃锁，使自己处于等等状态，让其他线程执行。
notify()方法：当生产者/消费者向缓冲区放入/取出一个产品时，向其他等待的线程发出可执行的通知，同时放弃锁，使自己处于等待状态。
 */


/**
 * 仓库类Storage实现缓冲区
 * @author weiH
 *
 */
public class Storage {
	
	//wait() / notify()方法实现方式
	/*//仓库最大存储量
	private final int MAX_SIZE = 100;
	//仓库存储的载体
	private LinkedList<Object> list = new LinkedList<Object>();
	
    // get/set方法  
    public LinkedList<Object> getList()  
    {  
        return list;  
    }  
    public void setList(LinkedList<Object> list)  
    {  
        this.list = list;  
    }  
    public int getMAX_SIZE()  
    {  
        return MAX_SIZE;  
    } 
	
	//生产num个产品
	public void produce(int num){
		//同步代码段
		synchronized (list) {
			//如果仓库剩余容量不足
			while(list.size() + num > MAX_SIZE){
				System.out.println("【要生产的产品数量】:" + num + " 【库存量】:"  
                        + list.size() + " 暂时不能执行生产任务!");
				
				try {
					//由于条件不满足，生产阻塞
					list.wait();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			 // 生产条件满足情况下，生产num个产品 
			for (int i = 1; i <= num; i++) {
				list.add(new Object());
			}
			
			System.out.println("【已经生产产品数】:" + num + " 【现仓储量为】:" + list.size());
		
			list.notifyAll();
		}
	}
	
	//消费num个产品
	public void consume(int num){
		//消费num个产品
		synchronized (list) {
			//如果仓库存储量不足
			while(list.size() < num){
				   System.out.println("【要消费的产品数量】:" + num + " 【库存量】:"  
	                        + list.size() + " 暂时不能执行生产任务!"); 
		            try  
	                {  
	                    // 由于条件不满足，消费阻塞  
	                    list.wait();  
	                }  
	                catch (InterruptedException e)  
	                {  
	                    e.printStackTrace();  
	                } 
			}
			
            // 消费条件满足情况下，消费num个产品  
            for (int i = 1; i <= num; ++i)  
            {  
                list.remove();  
            }  
            System.out.println("【已经消费产品数】:" + num + " 【现仓储量为】:" + list.size());  
            list.notifyAll();
		}
	}	*/
	
	
	
	//await() / signal()方法实现方式
	// 仓库最大存储量  
   /* private final int MAX_SIZE = 100;  
  
    // 仓库存储的载体  
    private LinkedList<Object> list = new LinkedList<Object>();  
  
    // 锁  
    private final Lock lock = new ReentrantLock();  
  
    // 仓库满的条件变量  
    private final Condition full = lock.newCondition();  
  
    // 仓库空的条件变量  
    private final Condition empty = lock.newCondition();  
  
    // 生产num个产品  
    public void produce(int num)  
    {  
        // 获得锁  
        lock.lock();  
  
        // 如果仓库剩余容量不足  
        while (list.size() + num > MAX_SIZE)  
        {  
            System.out.println("【要生产的产品数量】:" + num + "/t【库存量】:" + list.size()  
                    + "/t暂时不能执行生产任务!");  
            try  
            {  
                // 由于条件不满足，生产阻塞  
                full.await();  
            }  
            catch (InterruptedException e)  
            {  
                e.printStackTrace();  
            }  
        }  
  
        // 生产条件满足情况下，生产num个产品  
        for (int i = 1; i <= num; ++i)  
        {  
            list.add(new Object());  
        }  
  
        System.out.println("【已经生产产品数】:" + num + "/t【现仓储量为】:" + list.size());  
  
        // 唤醒其他所有线程  
        full.signalAll();  
        empty.signalAll();  
  
        // 释放锁  
        lock.unlock();  
    }  
  
    // 消费num个产品  
    public void consume(int num)  
    {  
        // 获得锁  
        lock.lock();  
  
        // 如果仓库存储量不足  
        while (list.size() < num)  
        {  
            System.out.println("【要消费的产品数量】:" + num + "/t【库存量】:" + list.size()  
                    + "/t暂时不能执行生产任务!");  
            try  
            {  
                // 由于条件不满足，消费阻塞  
                empty.await();  
            }  
            catch (InterruptedException e)  
            {  
                e.printStackTrace();  
            }  
        }  
  
        // 消费条件满足情况下，消费num个产品  
        for (int i = 1; i <= num; ++i)  
        {  
            list.remove();  
        }  
  
        System.out.println("【已经消费产品数】:" + num + "/t【现仓储量为】:" + list.size());  
  
        // 唤醒其他所有线程  
        full.signalAll();  
        empty.signalAll();  
  
        // 释放锁  
        lock.unlock();  
    }  
  
    // set/get方法  
    public int getMAX_SIZE()  
    {  
        return MAX_SIZE;  
    }  
  
    public LinkedList<Object> getList()  
    {  
        return list;  
    }  
  
    public void setList(LinkedList<Object> list)  
    {  
        this.list = list;  
    }*/ 
    
    
    
    
    /*
     * BlockingQueue阻塞队列方法实现方式
     *实现方式采用的是我们第2种await() / signal()方法。它可以在生成对象时指定容量大小。它用于阻塞操作的是put()和take()方法。
		put()方法：类似于我们上面的生产者线程，容量达到最大时，自动阻塞。
		take()方法：类似于我们上面的消费者线程，容量为0时，自动阻塞。
     */
 // 仓库最大存储量  
    private final int MAX_SIZE = 100;  
    // 仓库存储的载体  
    private LinkedBlockingQueue<Object> list = new LinkedBlockingQueue<Object>(100);  
  
    // 生产num个产品  
    public void produce(int num)  
    {  
        // 如果仓库剩余容量为0  
        if (list.size() == MAX_SIZE)  
        {  
            System.out.println("【库存量】:" + MAX_SIZE + "/t暂时不能执行生产任务!");  
        }  
  
        // 生产条件满足情况下，生产num个产品  
        for (int i = 1; i <= num; ++i)  
        {  
            try  
            {  
                // 放入产品，自动阻塞  
                list.put(new Object());  
            }  
            catch (InterruptedException e)  
            {  
                e.printStackTrace();  
            }  
  
            System.out.println("【现仓储量为】:" + list.size());  
        }  
    }  
  
    // 消费num个产品  
    public void consume(int num)  
    {  
        // 如果仓库存储量不足  
        if (list.size() == 0)  
        {  
            System.out.println("【库存量】:0/t暂时不能执行生产任务!");  
        }  
  
        // 消费条件满足情况下，消费num个产品  
        for (int i = 1; i <= num; ++i)  
        {  
            try  
            {  
                // 消费产品，自动阻塞  
                list.take();  
            }  
            catch (InterruptedException e)  
            {  
                e.printStackTrace();  
            }  
        }  
  
        System.out.println("【现仓储量为】:" + list.size());  
    }  
  
    // set/get方法  
    public LinkedBlockingQueue<Object> getList()  
    {  
        return list;  
    }  
  
    public void setList(LinkedBlockingQueue<Object> list)  
    {  
        this.list = list;  
    }  
  
    public int getMAX_SIZE()  
    {  
        return MAX_SIZE;  
    } 
}

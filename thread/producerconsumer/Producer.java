package com.wh.test.thread.producerconsumer;

/**生产者
 * @author weiH
 *
 */
public class Producer extends Thread{
	//每次生产的产品数量
	private int num;
	//所在放置的仓库
	private Storage storage;
	// get/set方法  
    public int getNum(){  
        return num;  
    }  
  
    public void setNum(int num){  
        this.num = num;  
    }  
  
    public Storage getStorage(){  
        return storage;  
    }  
  
    public void setStorage(Storage storage){  
        this.storage = storage;  
    }  
	//构造，设置仓库
    public Producer(Storage storage){  
        this.storage = storage;  
    }
	@Override
	public void run() {
		produce(num);
	}  
	
    // 调用仓库Storage的生产函数  
    public void produce(int num)  
    {  
        storage.produce(num);  
    } 
}

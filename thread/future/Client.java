package com.wh.test.thread.future;

public class Client {
    
	public Client() {
		System.out.println("客户端建立");
	}

	public Data request(final String string){
		final FutureData futureData = new FutureData();
		
		new Thread(new Runnable(){

			@Override
			public void run() {
		        //RealData的构建很慢，所以放在单独的线程中运行  
                RealData realData = new RealData(string);  
                futureData.setRealData(realData);  
			}
			
		}).start();;
		
		return  futureData;//先直接返回FutureData  
	}
}

package com.icss.etc.zhaozichen.main;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

import com.icss.etc.zhaozichen.net.TalkServer;
import com.icss.etc.zhaozichen.turbine.ThreadPoolUtil;

public class AppSatrt {
	
	
	
	
	
	public static void main(String[] args) {
		//初始化线程池
		ThreadPoolUtil.init(5, 20, 60*3, TimeUnit.MILLISECONDS,new ArrayBlockingQueue<Runnable>(5));
		TalkServer server=new TalkServer();
		//开启监听
		server.doListen();
		System.out.println("=======服务器开启监听=======");
	}

}

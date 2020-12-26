package com.icss.etc.zhaozichen.turbine;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池工具类 
 * */
public class ThreadPoolUtil {
	
	
	//这里使用ThreadPoolExecutor类作为线程池工具来处理线程
	private static ThreadPoolExecutor executor=null;
	
	
	
	/**
	 * @param corePoolSize 核心池的大小
	 * @param maximumPoolSize 线程池最大线程数
	 * @param keepAliveTime 线程闲置时间
	 * @param unit 线程闲置时间单位
	 * @param workQueue 阻塞队列大小
	 * */
	public static void init(int corePoolSize,int maximumPoolSize,int keepAliveTime ,TimeUnit unit ,BlockingQueue<Runnable> workQueue) {
		System.out.println("=======初始化线程池开始========");
		System.out.println("设置核心池的大小>>>>>"+corePoolSize);
		System.out.println("设置线程池最大线程数>>>>>"+maximumPoolSize);
		System.out.println("设置线程闲置时间>>>>>"+keepAliveTime);
		System.out.println("设置线程闲置时间单位>>>>>"+unit.name());
		System.out.println("设置阻塞队列大小>>>>>"+workQueue.remainingCapacity());		
		executor=new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
		System.out.println("=======初始化线程池结束========");
	}
	
	
	public static void push(Runnable client) {
		executor.execute(client);
		System.out.println("线程池中线程数目："+executor.getPoolSize()+"，队列中等待执行的任务数目："+
	    executor.getQueue().size()+"，已执行完的任务数目："+executor.getCompletedTaskCount());
	}

}

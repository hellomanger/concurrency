package com.carlton.concurrency.distributed;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.redisson.Redisson;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RBucket;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

/**
 * 
 * @author 分布式锁的简单实现
 *
 */
public class DistributedLock2 {
	public static void main(String[] args) {
//		Config config = new Config();
//		//config.setTransportMode(TransportMode.EPOLL);
//		config.useClusterServers()
//		       // use "rediss://" for SSL connection
//		      .addNodeAddress("redis://127.0.0.1:7181");

//		RedissonClient redisson = Redisson.create();
//		RLongAdder longAdder = redisson.getLongAdder("helloCount");
//		longAdder.increment();
		
//		System.out.println(longAdder.sum());
//		executorService.shutdown();
		
		
//		RLongAdder longAdder = redission.getLongAdder("helloCount");
//		longAdder.increment();
//		System.out.println(longAdder.sum());
//		RMap<String,Integer> helloMap = redission.getMap("helloMap");
		//System.out.println(helloMap.getName());
		
//		RedissonClient redisson = Redisson.create();
//		lock(redisson,2);
		atomic();
	}
	public static void atomic() {
		RedissonClient redisson = Redisson.create();
		RAtomicLong atomicLong = redisson.getAtomicLong("myAtomicLong");
		//atomicLong.set(3);
		ExecutorService executorService = Executors.newFixedThreadPool(5);
		for( int i=0;i<10;i++) {
			final int a = i;
			executorService.submit(new Runnable() {
				
				@Override
				public void run() {
					System.out.println("threadname"+Thread.currentThread().getName()+"   value "+a);
					atomicLong.incrementAndGet();
					try {
						Thread.sleep(10000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
		}
		if(executorService.isTerminated()) {
			System.out.println(atomicLong.get() +"  end");
		}
		
	}
	public static void threads() {
		ExecutorService executorService = Executors.newFixedThreadPool(5);
		for( int i=0;i<10;i++) {
			final int a = i;
			executorService.submit(new Runnable() {
				
				@Override
				public void run() {
					System.out.println("threadname"+Thread.currentThread().getName()+"   value "+a);
					
				}
			});
		}
	}
	public static void lock(RedissonClient redisson,int i) {
		RLock lock = redisson.getLock("tom");
		System.out.println("threadname  Lock2"+Thread.currentThread().getName()+"   获取锁    "+i);
		lock.lock();
		RBucket bucket = redisson.getBucket("test");
		bucket.set("hellotom ===="+i);
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lock.unlock();
		System.out.println("threadname  Lock2"+Thread.currentThread().getName()+"   释放锁     "+i);
		
	}
}

package com.carlton.concurrency.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;

public class AtomicTest {

	public static void main(String[] args) {
		AtomicInteger atomicInteger = new AtomicInteger();
		LongAdder longAdder = new LongAdder();
//		ExecutorService pool = Executors.newCachedThreadPool();
		ExecutorService pool = Executors.newFixedThreadPool(5);
		for(int i=0;i<100;i++) {
			pool.submit(new Runnable() {
				@Override
				public void run() {
					atomicInteger.incrementAndGet();
					longAdder.increment();
					System.out.println("threadName"+Thread.currentThread().getName()+"   atomicInteger:"+atomicInteger.get()+"   longAddr --> "+longAdder.floatValue());
				}
			});
			try {
				Thread.sleep(100);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//pool.shutdown();
		System.out.println(atomicInteger.get()+"   longAddr --> "+longAdder.floatValue());
	}

}

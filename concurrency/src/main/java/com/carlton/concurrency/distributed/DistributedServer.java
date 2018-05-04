package com.carlton.concurrency.distributed;

import org.redisson.Redisson;
import org.redisson.api.RRemoteService;
import org.redisson.api.RedissonClient;

import com.carlton.concurrency.service.HelloService;
import com.carlton.concurrency.service.impl.HelloServiceImpl;

public class DistributedServer {

	public static void main(String[] args) {
		RedissonClient redisson = Redisson.create();
		RRemoteService remoteService = redisson.getRemoteService();
		HelloServiceImpl impl = new HelloServiceImpl();
		remoteService.register(HelloService.class,impl);
//		remoteService.register(HelloService.class,impl,2);
		System.out.println("service one");
		

	}

}

package com.carlton.concurrency.distributed;

import org.redisson.Redisson;
import org.redisson.api.RRemoteService;
import org.redisson.api.RedissonClient;
import org.redisson.api.RemoteInvocationOptions;

import com.carlton.concurrency.service.HelloService;

public class DistributedClient {
	public static void main(String[] args) {
		RedissonClient redisson = Redisson.create();
		RRemoteService remoteService = redisson.getRemoteService();
		HelloService service = remoteService.get(HelloService.class);
		for(int i=0;i<10;i++) {
			try {
				String result = service.sayHello("tom"+i);
				System.out.println(result+"     i "+i);
			}catch (Exception e) {
				RemoteInvocationOptions options = RemoteInvocationOptions.defaults().noAck();
				remoteService = redisson.getRemoteService();
				service = remoteService.get(HelloService.class,options);
				System.out.println(e.getMessage());
				String result = service.sayHello("tom"+(i+1));
				System.out.println(result+"     i "+(i+1));
			}
		}
//		AbstractQueuedSynchronizer
	}
}

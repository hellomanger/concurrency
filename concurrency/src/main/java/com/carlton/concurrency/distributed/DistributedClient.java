package com.carlton.concurrency.distributed;

import org.redisson.Redisson;
import org.redisson.api.RRemoteService;
import org.redisson.api.RedissonClient;
import org.redisson.api.RemoteInvocationOptions;
import org.redisson.remote.RemoteServiceAck;

import com.carlton.concurrency.service.HelloService;

public class DistributedClient {
	public static void main(String[] args) {
		RedissonClient redisson = Redisson.create();
		for(int i=0;i<10;i++) {
			try {
				RRemoteService remoteService = redisson.getRemoteService();
				HelloService service = remoteService.get(HelloService.class);
				String result = service.sayHello("tom"+i);
				System.out.println(result+"     i "+i);
			}catch (Exception e) {
				RemoteInvocationOptions options = RemoteInvocationOptions.defaults().noAck();
				RRemoteService remoteService = redisson.getRemoteService();
				HelloService service = remoteService.get(HelloService.class,options);
				System.out.println(e.getMessage());
				String result = service.sayHello("tom"+(i+1));
				System.out.println(result+"     i "+(i+1));
			}
			
			
		}
		
	}
}

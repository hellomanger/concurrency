package com.carlton.concurrency.service.impl;

import com.carlton.concurrency.service.HelloService;

public class HelloServiceImpl implements HelloService {

	@Override
	public String sayHello(String name) {
		System.out.println("服务实现类提示，你好"+name);
		try {
			if("tom4".equals(name)) {
				Thread.sleep(40000);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "ok      "+name;
	}

}

package com.carlton.concurrency.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.carlton.concurrency.vo.HelloVO;

@Controller
public class DispatchPageController {
	
	private Logger logger = LoggerFactory.getLogger(DispatchPageController.class);

	@RequestMapping("/")
	public String home() {
		logger.info("hello");
		return "home";
	}
	@ResponseBody
	@RequestMapping("/objJson")
	public HelloVO json(String name,Integer age) {
		return new HelloVO(name, age);
	}
	
	@ResponseBody
	@RequestMapping("/sayHello")
	public String find(String name) {
		return "hello   "+name;
	}
}

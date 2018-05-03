package com.carlton.concurrency.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.carlton.concurrency.vo.HelloVO;

@Controller
public class DispatchPageController {

	@RequestMapping("/")
	public String home() {
		System.out.println("hello");
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

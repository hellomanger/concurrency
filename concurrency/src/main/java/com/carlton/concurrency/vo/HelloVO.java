package com.carlton.concurrency.vo;

public class HelloVO {

	private String name;
	private Integer age;
	
	public HelloVO(String name, Integer age) {
		super();
		this.name = name;
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "HelloVO [name=" + name + ", age=" + age + "]";
	}
	
}

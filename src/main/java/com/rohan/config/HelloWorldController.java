package com.rohan.config;

import java.lang.management.MemoryType;

import javax.websocket.server.PathParam;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	
    //@RequestMapping(path = "hello" , method = RequestMethod.GET)
	@GetMapping(path = "/hello")
	public String getHello() {
		return "Hello World";
	}


    @GetMapping(path = "/hello-world")
	public HelloWorldBean getHelloWorldBean() {
		return new HelloWorldBean("hello world");
	}
    
    @GetMapping(path = "/hello-world/{name}")
	public HelloWorldBean getHelloWorldBeanWithParam(@PathVariable String name) {
		//return new HelloWorldBean("hello world Mr." + name);
		return new HelloWorldBean(String.format("hello world, Mr. %s", name));
	}
}


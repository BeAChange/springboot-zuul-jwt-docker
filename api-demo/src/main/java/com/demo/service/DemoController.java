package com.demo.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController
{
	
	@GetMapping("/service1/test1")
	public String test1(@RequestParam("param1") String param1)
	{
		return "test1:" + param1;
	}
	
	@PostMapping("/service2/test2")
	public String test2(@RequestBody Object object)
	{
		return "test2:" + object.toString();
	}
	
	@PatchMapping("/service3/test3")
	public String test3(@RequestBody Object object)
	{
		return "test3:" + object.toString();
	}
	
}

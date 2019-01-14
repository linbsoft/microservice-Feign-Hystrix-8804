package com.linbsoft.microservicefeignhystrix8804;

import org.springframework.stereotype.Component;

@Component
public class HelloClientFallback implements HelloClient {
 //这是回退类,超时就执行这个得到返回值
	
    public String hello() {
        return "fallback hello";
    }
 
    public String toHello() {
        return "fallback timeout hello";
    }

	public String listusers() {
		 return "fallback listusers";
	}
 
}

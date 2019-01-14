package com.linbsoft.microservicefeignhystrix8804;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

// 作为消费者轮询eureka中名字为MicroserviceServerA8801的微服务,如果超时,回退执行HelloClientFallback.class
@FeignClient(name = "MicroserviceServerA8801", fallback = HelloClientFallback.class)
public interface HelloClient {
 
	//定义声明接口
    @RequestMapping(method = RequestMethod.GET, value = "/hello")
    public String hello();
     
    @RequestMapping(method = RequestMethod.GET, value = "/toHello")
    public String toHello();
    
    @RequestMapping(method = RequestMethod.GET, value = "/listusers")
    public String listusers();
}

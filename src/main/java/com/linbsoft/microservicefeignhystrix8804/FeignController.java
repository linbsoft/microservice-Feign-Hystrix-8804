package com.linbsoft.microservicefeignhystrix8804;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.HystrixCircuitBreaker;
import com.netflix.hystrix.HystrixCommandKey;

@RestController
public class FeignController {
     
    @Autowired
    private HelloClient helloClient;
 
    //对声明接口方法的具体实现
    @RequestMapping(method = RequestMethod.GET, value = "/hello")
    public String hello() {
        return helloClient.hello();
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/listusers")
    public String listusers() {
        return helloClient.listusers();
        // helloclient 是一个restemplate的请求,就是去发起连接请求获取返回,不能获取就执行回退程序
    }
     
    @RequestMapping(method = RequestMethod.GET, value = "/toHello")
    public String toHello() throws InterruptedException {
        Thread.sleep(500);
        String result = helloClient.toHello();
        HystrixCircuitBreaker breaker = HystrixCircuitBreaker.Factory
                .getInstance(HystrixCommandKey.Factory
                        .asKey("HelloClient#toHello()"));  
        System.out.println("断路器状态：" + breaker.isOpen());
        return result;
    }
}
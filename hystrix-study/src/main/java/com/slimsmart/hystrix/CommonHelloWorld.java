package com.slimsmart.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixThreadPoolKey;
import com.netflix.hystrix.strategy.properties.HystrixPropertiesCommandDefault;
import com.netflix.hystrix.strategy.properties.HystrixPropertiesThreadPoolDefault;


public class CommonHelloWorld  extends HystrixCommand<String>{

    private String name;

    protected CommonHelloWorld(String name) {
        super(HystrixCommandGroupKey.Factory.asKey("helloWorldGroup"));
        this.name = name;
    }

    @Override
    protected String run() throws Exception {
       // int i = 1/0;
        return "Hello " + name + " !";
    }

    @Override
    protected String getFallback() {
        return "Hello " + name + " getFallback";
    }
}

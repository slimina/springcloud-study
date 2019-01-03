package com.slimsmart.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

public class CommandWithFallBack extends HystrixCommand<String> {

    private final boolean throwException;

    public CommandWithFallBack(boolean throwException){
        super(HystrixCommandGroupKey.Factory.asKey("test_group"));
        this.throwException = throwException;
    }

    @Override
    protected String run() throws Exception {
        if(throwException){
            throw new RuntimeException("error !!!!");
        }
        return "success";
    }

    @Override
    protected String getFallback(){
        return "fallback";
    }
}

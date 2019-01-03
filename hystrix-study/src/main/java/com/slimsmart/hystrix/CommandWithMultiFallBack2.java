package com.slimsmart.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

public class CommandWithMultiFallBack2 extends HystrixCommand<String> {

    private final boolean throwException;

    public CommandWithMultiFallBack2(boolean throwException){
        super(HystrixCommandGroupKey.Factory.asKey("test_group"));
        this.throwException = throwException;
    }

    @Override
    protected String run() throws Exception {
        if(throwException){
            throw new RuntimeException("error !!!!");
        }
        return "fallback 1";
    }

    @Override
    protected String getFallback(){
        return "fallback 2";
    }
}

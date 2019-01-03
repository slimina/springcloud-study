package com.slimsmart.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

public class CommandWithMultiFallBack1 extends HystrixCommand<String>{

    private final boolean throwException;

    public CommandWithMultiFallBack1(boolean throwException){
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
        return new CommandWithMultiFallBack2(true).execute();
    }
}
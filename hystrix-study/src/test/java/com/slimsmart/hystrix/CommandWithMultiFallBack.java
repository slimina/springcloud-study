package com.slimsmart.hystrix;

import com.netflix.hystrix.exception.HystrixRuntimeException;
import org.junit.Test;

public class CommandWithMultiFallBack {

    @Test
    public void testMultiFailure(){
        try{
            System.err.println(new CommandWithMultiFallBack1(true).execute());
        }catch (HystrixRuntimeException e){
            e.printStackTrace();
        }
    }
}

package com.slimsmart.hystrix;

import com.netflix.hystrix.exception.HystrixRuntimeException;
import org.junit.Test;

public class CommandWithFallBackTest {

    @Test
    public void testFailure(){
        try{
            System.err.println(new CommandWithFallBack(true).execute());
        }catch (HystrixRuntimeException e){
            e.printStackTrace();
        }
    }
}

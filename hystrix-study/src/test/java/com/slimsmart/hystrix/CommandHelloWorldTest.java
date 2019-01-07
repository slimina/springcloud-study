package com.slimsmart.hystrix;

import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class CommandHelloWorldTest {

    @Test
    public void testSync(){
        CommandHelloWorld commonHelloWorld = null;

        for(int i= 0;i< 100;i++){
            commonHelloWorld = new CommandHelloWorld("tom");
            String result = null;
            try{
                //同步的执行
                result = commonHelloWorld.execute();
            }catch (Exception e){}
            System.err.println(result);
        }
    }

    @Test
    public void testAsync() throws ExecutionException, InterruptedException {
        Future<String> queue1 = new CommandHelloWorld("tom").queue();
        Future<String> queue2 = new CommandHelloWorld("lucy").queue();
        System.err.println(queue1.get());
        System.err.println(queue2.get());
    }
}

package com.slimsmart.hystrix;

import com.netflix.hystrix.Hystrix;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.junit.Test;

public class CachedCommandTest {

    @Test
    public void test_no_cache(){
        HystrixRequestContext context =
                HystrixRequestContext.initializeContext();
        String name = "abc123";
        CachedCommand cachedCommand = new CachedCommand(name);
        System.err.println(cachedCommand.execute());
        System.err.println(cachedCommand.isResponseFromCache());

        CachedCommand cachedCommand1 = new CachedCommand(name);
        System.err.println(cachedCommand1.execute());
        System.err.println(cachedCommand1.isResponseFromCache());

        CachedCommand.flushCache(name);


        CachedCommand cachedCommand2 = new CachedCommand(name);
        System.err.println(cachedCommand2.execute());
        System.err.println(cachedCommand2.isResponseFromCache());
        context.shutdown();
    }
}

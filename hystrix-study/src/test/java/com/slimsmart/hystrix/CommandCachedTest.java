package com.slimsmart.hystrix;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.junit.Test;

public class CommandCachedTest {

    @Test
    public void test_no_cache(){
        HystrixRequestContext context =
                HystrixRequestContext.initializeContext();
        String name = "abc123";
        CommandCached cachedCommand = new CommandCached(name);
        System.err.println(cachedCommand.execute());
        System.err.println(cachedCommand.isResponseFromCache());

        CommandCached cachedCommand1 = new CommandCached(name);
        System.err.println(cachedCommand1.execute());
        System.err.println(cachedCommand1.isResponseFromCache());

        CommandCached.flushCache(name);


        CommandCached cachedCommand2 = new CommandCached(name);
        System.err.println(cachedCommand2.execute());
        System.err.println(cachedCommand2.isResponseFromCache());
        context.shutdown();
    }
}

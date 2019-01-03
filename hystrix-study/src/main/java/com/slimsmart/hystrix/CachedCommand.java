package com.slimsmart.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixRequestCache;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategyDefault;

/**
 * Hystrix的cache，个人的理解就是在上下文中，多次请求同一个command，返回值不会发生改变的时候可以使用。
 * cache如果要生效，必须声明上下文
 */
public class CachedCommand extends HystrixCommand<String> {
    private  String key;
    private static final HystrixCommandKey COMMAND_KEY =
            HystrixCommandKey.Factory.asKey("CachedCommand_key");
    public CachedCommand(String key){
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("CachedCommand_group"))
        .andCommandKey(COMMAND_KEY));
        this.key = key;
    }

    @Override
    protected String getCacheKey() {
        return this.key;
    }

    public static void flushCache(String key){
        //清缓存，就是先获得到command然后把对应的key删除
        HystrixRequestCache.getInstance(COMMAND_KEY,
                HystrixConcurrencyStrategyDefault.getInstance()).clear(key);
    }

    @Override
    protected String run() throws Exception {
        return "hello "+key + "@@";
    }
}

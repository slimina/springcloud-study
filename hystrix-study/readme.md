# Hystrix

HystrixCommand每次执行都需要new一个，不能使用单例，一个command实例只能执行一次

## HystrixCommand
Hystrix每个command都有对应的commandKey可以认为是command的名字，默认是当前类的名字 getClass().getSimpleName(),每个command也都一个归属的分组，
这两个东西主要方便Hystrix进行监控、报警等。

HystrixCommand使用的线程池也有线程池key，以及对应线程相关的配置
> 自定义command key
> HystrixCommandKey.Factory.asKey("HelloWorld")
> 自定义command group
> HystrixCommandGroupKey.Factory.asKey("ExampleGroup")
> 自定义线程池 key
> HystrixThreadPoolKey.Factory.asKey("HelloWorldPool")
Hystrix command配置有熔断阀值，熔断百分比等配置，ThreadPoll有线程池大小，队列大小等配
```java_holder_method_tree
public CommonHelloWorld(){
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("test_group"))
        .andCommandKey(HystrixCommandKey.Factory.asKey("test_key"))
        .andCommandPropertiesDefaults(HystrixPropertiesCommandDefault.Setter()
        .withCircuitBreakerEnabled(true).withCircuitBreakerForceOpen(true))
        .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("test_thread"))
        .andThreadPoolPropertiesDefaults(HystrixPropertiesThreadPoolDefault.Setter()
        .withCoreSize(20).withMaxQueueSize(128)));
        }
```
commandKey分组内唯一，HystrixCommand和分组、线程池是多对1的关系。分组和线程池没关系。
![HystrixCommand和分组、线程池三者的关系](https://mmbiz.qpic.cn/mmbiz_png/nXyTmFfqCEP0Tc9rcW3Tw4rxExAlhXWVqVpMsuxwcqibeXpiak76RfPvmBJm8T05vZEQjyzmnceDqGmLlML7XQ8w/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)

### fallback
fallback就是当HystrixCommand 执行失败的时候走的后备逻辑，只要实现HystrixCommand 的fallback方法即可

### 多级fallback
当我们执行业务的时候，有时候会有备用方案1、备用方案2，当备用方案1失败的时候启用备用方案2，所以可以使用多级fallback。
多级fallback没有名字那么神秘，说到底其实就是HystrixCommand1执行fallback1， fallback1的执行嵌入HystrixCommand2,当HystrixCommand2执行失败的时候，触发HystrixCommand2的fallback2，以此循环下去实现多级fallback

### 主次多HystrixCommand fallback
主Command里串行执行 多个Command时的fallback执行逻辑
fallback的跳转也比较好理解，次command，不管任何一个执行失败都认为主command的run执行失败，进而进入主command的fallback
![](https://mmbiz.qpic.cn/mmbiz_png/nXyTmFfqCEP0Tc9rcW3Tw4rxExAlhXWVVib8pMKJJWEB7ibkfUbhcibNURvibNBlB7toypxw2mw5S1CapnrwBmpbibg/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)

## HystrixObservableCommand



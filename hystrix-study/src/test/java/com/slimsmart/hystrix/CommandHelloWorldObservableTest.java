/**
*
* @Copyright (c) 2019/1/6, Lianjia Group All Rights Reserved.
*/
package com.slimsmart.hystrix;

import org.junit.Test;
import rx.Observable;
import rx.Observer;
import rx.functions.Action1;

/**
* 虽然HystrixCommand具备了observe()和toObservable()的功能，但是它的实现有一定的局限性，*
 * 它返回的Observable只能发射一次数据，所以Hystrix还提供了HystrixObservableCommand,
 * 通过它实现的命令可以获取能发多次的Observable
*
* @author zhutw
* @date 2019/1/6 下午21:29
*/
public class CommandHelloWorldObservableTest {

    @Test
    public void testObserve() throws InterruptedException {
        Observable<String> observe = new CommandHelloWorld( "test" ).observe();
        observe.subscribe( new Observer<String>() {
            @Override
            public void onCompleted() {
                System.out.println("==============onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onNext(String s) {
                System.out.println("=========onNext: " + s);
            }
        } );

        observe.subscribe( new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println("==================call:" + s);
            }
        } );
        observe.subscribe( new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println("==================call2:" + s);
            }
        } );
        Thread.sleep( 3*1000L );
    }

    @Test
    public void testToObservable() {
        //Cold Observable在没有 “订阅者” 的时候并不会发布事件，
        //而是进行等待，直到有 “订阅者” 之后才发布事件，所以对于
        //Cold Observable的订阅者，它可以保证从一开始看到整个操作的全部过程。
        Observable<String> co = new CommandHelloWorld("World").toObservable();
        System.out.println(co.toBlocking().single());
    }
}
package cn.enjoyedu.ch02.cas;

import java.util.concurrent.atomic.AtomicInteger;

/**
 *类说明：演示基本类型的原子操作类
 */
public class UseAtomicInt {
    static AtomicInteger ai = new AtomicInteger(10);

    public static void main(String[] args) {
        //返回的是我自增以前的值
        int i =  ai.getAndIncrement(); // i++
        //返回自增以后的值
        int b = ai.incrementAndGet();// ++i
        System.out.println(i +"------"+ b);
        //ai.compareAndSet();
        int fianl = ai.addAndGet(24);
        System.out.println("加了24之后的值为："+fianl);
    }
}

package cn.enjoyedu.ch02.cas;

import java.util.concurrent.atomic.AtomicIntegerArray;


/**
 *类说明： 演示原子操作数组
 */
public class AtomicArray {
    static int[] value = new int[] { 1, 2 };
    static AtomicIntegerArray ai = new AtomicIntegerArray(value);
    public static void main(String[] args) {
        ai.getAndSet(0, 3);
        System.out.println(ai.get(0));
        System.out.println(value[0]);//原数组不会变化
        }
}

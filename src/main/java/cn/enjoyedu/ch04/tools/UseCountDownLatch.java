package cn.enjoyedu.ch04.tools;

import cn.enjoyedu.tools.SleepTools;

/**
 *类说明：演示CountDownLatch用法，
 * 共5个初始化子线程，6个闭锁扣除点，扣除完毕后，主线程和业务线程才能继续执行
 */
public class UseCountDownLatch {
	


    /*初始化线程*/
    private static class InitThread implements Runnable{

        public void run() {
        	System.out.println("Thread_"+Thread.currentThread().getId()
        			+" ready init work......");
            //TODO
            for(int i =0;i<2;i++) {
            	System.out.println("Thread_"+Thread.currentThread().getId()
            			+" ........continue do its work");
            }
        }
    }

    /*业务线程等待latch的计数器为0完成*/
    private static class BusiThread implements Runnable{

        public void run() {
            //TODO
            for(int i =0;i<3;i++) {
            	System.out.println("BusiThread_"+Thread.currentThread().getId()
            			+" do business-----");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new Thread(new Runnable() {
            public void run() {
            	SleepTools.ms(1);
                System.out.println("Thread_"+Thread.currentThread().getId()
            			+" ready init work step 1st......");
                //TODO
                System.out.println("begin step 2nd.......");
                SleepTools.ms(1);
                System.out.println("Thread_"+Thread.currentThread().getId()
            			+" ready init work step 2nd......");
                //TODO
            }
        }).start();
        new Thread(new BusiThread()).start();
        for(int i=0;i<=3;i++){
            Thread thread = new Thread(new InitThread());
            thread.start();
        }

        //TODO
        System.out.println("Main do ites work........");
    }
}

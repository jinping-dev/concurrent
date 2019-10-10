package cn.enjoyedu.ch01.syn;

import cn.enjoyedu.tools.SleepTools;

/**
 *类说明：演示实例锁和类锁是不同的，两者可以并行
 */
public class InstanceAndClass {
	
    private static class SynClass extends Thread{
        @Override
        public void run() {
            System.out.println("TestClass is running...");
            synClass();
        }
    }

    private static class ObjSyn implements Runnable{
        private InstanceAndClass SynClassAndInstance;

        public ObjSyn(InstanceAndClass SynClassAndInstance) {
            this.SynClassAndInstance = SynClassAndInstance;
        }

        @Override
        public void run() {
            System.out.println("TestInstance is running..."+SynClassAndInstance);
            SynClassAndInstance.instance();
        }
    }

    //实例方法
    private synchronized void instance(){
        SleepTools.second(1);
        System.out.println("synInstance is going..."+this.toString());
        SleepTools.second(1);
        System.out.println("synInstance ended "+this.toString());
    }

    //静态方法
    private static synchronized void synClass(){
        SleepTools.second(1);
        System.out.println("synClass going...");
        SleepTools.second(1);
        System.out.println("synClass end");
    }

    public static void main(String[] args) {
        InstanceAndClass synClassAndInstance = new InstanceAndClass();
        Thread t1 = new SynClass();
        Thread t2 = new Thread(new ObjSyn(synClassAndInstance));
        t2.start();
        SleepTools.second(1);
        t1.start();
    }
}

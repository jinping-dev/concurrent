package cn.enjoyedu.ch01.syn;

import cn.enjoyedu.tools.SleepTools;

/**
 *类说明：锁的实例不一样，也是可以并行的
 */
public class DiffInst {
	
    private static class Obj1 implements Runnable{
        private DiffInst diffInst;

        public Obj1(DiffInst diffInst) {
            this.diffInst = diffInst;
        }

        @Override
        public void run() {
            System.out.println("TestInstance is running..."+ diffInst);
            diffInst.instance();
        }
    }

    private static class Obj2 implements Runnable{
        private DiffInst diffInst;

        public Obj2(DiffInst diffInst) {
            this.diffInst = diffInst;
        }
        @Override
        public void run() {
            System.out.println("TestInstance2 is running..."+ diffInst);
            diffInst.instance2();
        }
    }

    private synchronized void instance(){
        SleepTools.second(3);
        System.out.println("synInstance is going..."+this.toString());
        SleepTools.second(3);
        System.out.println("synInstance ended "+this.toString());
    }

    private synchronized void instance2(){
        SleepTools.second(3);
        System.out.println("synInstance2 is going..."+this.toString());
        SleepTools.second(3);
        System.out.println("synInstance2 ended "+this.toString());
    }

    public static void main(String[] args) {
        DiffInst instance1 = new DiffInst();
        Thread t3 = new Thread(new Obj2(instance1));
        DiffInst instance2 = new DiffInst();
        Thread t4 = new Thread(new Obj1(instance1));
        t3.start();
        t4.start();
        SleepTools.second(1);
    }
}

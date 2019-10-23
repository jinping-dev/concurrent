package cn.enjoyedu.ch03.tranfer.service;

import cn.enjoyedu.ch03.tranfer.UserAccount;

import java.util.Random;

/**
 *@author Mark老师   享学课堂 https://enjoy.ke.qq.com 
 *
 *类说明：不会产生死锁的安全转账第二种方法
 * 尝试拿锁
 */
public class SafeOperateToo implements ITransfer {

    @Override
    public void transfer(UserAccount from, UserAccount to, int amount)
            throws InterruptedException {
        Random r = new Random();
        while(true){
            if(from.getLock().tryLock()){
                System.out.println(Thread.currentThread().getName()
                        +" get"+from.getName());
                try{
                    if(to.getLock().tryLock()){
                        try{
                            System.out.println(Thread.currentThread().getName()
                                    +" get"+to.getName());
                            from.flyMoney(amount);
                            to.addMoney(amount);
                            System.out.println(from);
                            System.out.println(to);
                            break;
                        }finally{
                            to.getLock().unlock();
                        }
                    }
                }finally {
                    from.getLock().unlock();
                }

            }
            //为什么要休眠两毫秒？拿锁的过程会很长，反复地拿锁，这种情况会造成CPU的浪费。
            //有A,B两个线程，都需要去拿c,d两把锁
            //A持有了c锁，同事B持有了d锁；A想要获取d锁，于是去尝试，但是B想要获取c锁，于是也去尝试，尝试结束之后如果没有获取到锁的话，就将自己持有的锁释放掉，但是释放之后另一个需要相应锁的线程并不知道
            //然后接着又拿起自己的锁去尝试。。。。又去释放，造成了资源的浪费。
            //这种情况叫活锁，让拿锁的时机稍微错开一点点，打断了拿锁和释放锁之间的碰撞情况
            Thread.sleep(r.nextInt(2));
            //线程饥饿：低优先级的线程总是拿不到执行时间以至于这个线程一直干等着得不到执行。
        }

    }
}

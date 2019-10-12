package cn.enjoyedu.ch01.wn;

/**
 * @Author Tinner
 * @create 2019/10/10 17:33
 * @Description 《wait/notify实现生产者和消费者程序》
 * 采用多线程技术，例如wait/notify
 * 设计实现一个符合生产者消费者问题的程序
 * 对某一个对象(枪膛)进行操作，其最大容量是20颗子弹
 * 生产者线程是一个压入线程，它不断向枪膛中压入子弹
 * 消费者线程是一个射出线程，它不断从枪膛中射出子弹
 */
public class Gun {

    //弹夹实时容量
    private Integer danjia = 0;
    //弹夹最大容量
    private static final Integer maxNum = 20;

    //装填动作
    public synchronized void put(){
        while (danjia.equals(maxNum)){
            try {
                System.out.println(Thread.currentThread().getName() + "发现子弹（" + danjia + "+已经装满，等待发射.....");
                wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        danjia++;
        System.out.println(Thread.currentThread().getName() + "装入子弹一枚，当前子弹剩余 = " + danjia);
        notifyAll();
    }

    //发射动作
    public synchronized void fire(){
        while (danjia == 0){
            try {
                System.out.println(Thread.currentThread().getName() + "发现子弹（" + danjia + "+已经发射完了，等待装填.....");
                wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        danjia--;
        System.out.println(Thread.currentThread().getName() + "发射子弹一枚，当前子弹剩余 = " + danjia);
        notifyAll();
    }
}

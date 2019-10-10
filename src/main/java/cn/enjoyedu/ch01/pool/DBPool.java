package cn.enjoyedu.ch01.pool;

import java.sql.Connection;
import java.util.LinkedList;
import java.util.MissingFormatArgumentException;

/**
 *@author Mark老师   享学课堂 https://enjoy.ke.qq.com 
 *
 *类说明：
 */
public class DBPool {

    //连接池-连接的容器
    private static LinkedList<Connection> pool = new LinkedList<Connection>();

    public DBPool(int initialSize) {
        if (initialSize > 0) {
            for (int i = 0; i < initialSize; i++) {
                pool.addLast(SqlConnectImpl.fetchConnection());
            }
        }
    }

    //释放连接，通知，等待数据库连接的线程
    public void releaseConnection(Connection connection) {
        //TODO
        if (connection != null){
            //添加后需要进行通知，这样其他消费者能够感知到连接池中已经归还了一个连接
            pool.addLast(connection);
            pool.notifyAll();
        }
    }

    //获取连接，通知，等待数据库连接的线程
    // 在mills内无法获取到连接，将会返回null
    public Connection fetchConnection(long mills)
            throws InterruptedException {
        synchronized (pool){
            //永不超时
            if (mills < 0){
                while (pool.isEmpty()){
                    pool.wait();
                }
                return pool.removeFirst();
            }else {
                /*超时时刻*/
                long future = System.currentTimeMillis() + mills;
                /*等待时长*/
                long remaining = mills;
                /*池为空且未超时，继续等待*/
                while (pool.isEmpty() && remaining > 0){
                    pool.wait(remaining);
                    /*每被唤醒一次，重新计算等待时长*/
                    remaining = future - System.currentTimeMillis();
                }
                Connection result = null;
                if (!pool.isEmpty()){
                    result = pool.removeFirst();
                }
                return result;
            }
        }
    }
}

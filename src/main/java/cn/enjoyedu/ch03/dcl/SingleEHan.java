package cn.enjoyedu.ch03.dcl;

/**
 * 饿汉式
 *
 */
public class SingleEHan {
    private SingleEHan(){}
    public static SingleEHan singleDcl = new SingleEHan();

}

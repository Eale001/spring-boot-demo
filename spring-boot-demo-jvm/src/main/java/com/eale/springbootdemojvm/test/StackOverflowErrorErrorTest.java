package com.eale.springbootdemojvm.test;

/**
 * @Author Admin
 * @Date 2021/3/11
 * @Description //StackOverflowErrorErrorTest
 *  java 虚拟机栈和本地方法栈内存溢出测试
 *  * <p>
 *  * VM Args: -Xss128k
 * @Version 1.0
 **/
public class StackOverflowErrorErrorTest {

    private int stackLength = 0;

    public void stackLeak() {
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args) {
        StackOverflowErrorErrorTest sof = new StackOverflowErrorErrorTest();
        try {
            sof.stackLeak();
        } catch (Exception e) {
            System.out.println(sof.stackLength);
            e.printStackTrace();
        }
    }

}

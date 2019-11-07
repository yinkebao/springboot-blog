package com.test;

/**
 * @ClassName LambdaThread
 * @Description
 * @Author ykb
 * @Date 2019/11/3
 **/
public class LambdaThread {
    public static void main(String[] args) {
        createThread();
    }

    /**
     * 创建一个线程
     */
    private static void createThread(){
        Thread t = new Thread(() -> {
            for(int i=0;i<100;i++){
                System.out.println(i);
            }
        });
        t.start();
    }
}
package com.princeli.demo.bio;

import java.util.Random;
/**
 * @program: netty-demo
 * @description: ${description}
 * @author: ly
 * @create: 2018-12-26 15:13
 **/
public class Test {
    public static void main(String[] args) throws InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Server.start();
                }catch (Exception e){
                    e.printStackTrace();
                }finally {

                }
            }
        }).start();


        Thread.sleep(100);
        final char [] op = {'+','-','*','/'};
        Random random = new Random(System.currentTimeMillis());
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    String expression = random.nextInt(10)+""+op[random.nextInt(4)]+(random.nextInt(10)+1);
                    Client.send(expression);

                    try {
                        Thread.sleep(random.nextInt(1000));
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}

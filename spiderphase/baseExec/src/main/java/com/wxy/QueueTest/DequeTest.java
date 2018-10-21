package com.wxy.QueueTest;

import java.util.ArrayDeque;

public class DequeTest {
    public static void main(String[] args) {
        ArrayDeque<String> arrayDeque = new ArrayDeque<>();

        for (int i = 0; i < 100; i++) {
            arrayDeque.offer("element" + i);
        }
        for (String str : arrayDeque){
            System.out.println(str);
        }
        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("thread:" + Thread.currentThread().getName() + " element:" + arrayDeque.poll());
                    }
                }
            }).start();
        }
    }
}

package com.wxy.QueueTest;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * Hello world!
 */
public class BlockingQueue {
    public static void main(String[] args) {

        ArrayBlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue<String>(100);

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true)
                {
                    try {
//                        arrayBlockingQueue.offer("element" + System.currentTimeMillis());
                        arrayBlockingQueue.put("element" + System.currentTimeMillis());
                        Thread.sleep(3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true)
                    {
                        try {
//                        System.out.println(arrayBlockingQueue.poll());
                            System.out.println(arrayBlockingQueue.take());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }

    }


}

package com.yp.demo.test.python;

import org.apache.lucene.util.NamedThreadFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Demo4 {

    public static void main(String[] args) {
        TestBlockQueue<String> testBlockQueue = new TestBlockQueue<>();

        ExecutorService producerService = Executors.newSingleThreadExecutor(new NamedThreadFactory("producer"));

        ExecutorService consumerService = Executors.newFixedThreadPool(10, new NamedThreadFactory("consumer"));

        producerService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        Thread.sleep(1);
                        testBlockQueue.push("Hello");
                        System.out.println(Thread.currentThread().getName() + "Hello");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        while (true) {
            consumerService.execute(new Runnable() {
                @Override
                public void run() {
                    testBlockQueue.get();
                    System.out.println(Thread.currentThread().getName() + "Hello");
                }
            });
        }
    }
}

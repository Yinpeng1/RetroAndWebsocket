package com.yp.demo.test.python;

import com.sun.org.apache.xml.internal.utils.ObjectPool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Demo2 {

    public BlockingQueue<String> blockingQueue = new LinkedBlockingQueue<>(10);

    private Lock producerLock = new ReentrantLock();
    private Condition notFull = producerLock.newCondition();

    private Lock consumerLock = new ReentrantLock();
    private Condition notEmpty = consumerLock.newCondition();

    private List<String> list = new ArrayList<>();

    private class Producer implements Runnable{

        private BlockingQueue<String> blockingQueue = new LinkedBlockingQueue<>();

        public Producer(BlockingQueue<String> blockingQueue) {
            this.blockingQueue = blockingQueue;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(1);
                blockingQueue.put("nihao");
                System.out.println("你好=="+Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private class Consumer implements Runnable{

        private BlockingQueue<String> blockingQueue;

        public Consumer(BlockingQueue<String> blockingQueue) {
            this.blockingQueue = blockingQueue;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(1);
                System.out.println(blockingQueue.take() + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Demo2 demo2 = new Demo2();
        for (int i = 0; i < 6; i++) {
            executorService.execute(demo2.new Producer(demo2.blockingQueue));
        }
        for (int i = 0; i < 10; i++) {
            executorService.execute(demo2.new Consumer(demo2.blockingQueue));
        }
    }
}

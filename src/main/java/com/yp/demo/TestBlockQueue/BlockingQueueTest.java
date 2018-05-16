package com.yp.demo.TestBlockQueue;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.Function;

public class BlockingQueueTest {

    public static class Producer  implements Runnable{

        private final BlockingQueue<Integer> blockingQueue;
        private volatile boolean flag;
//        private Random random;
        private AtomicInteger atomicInteger;

        public Producer(BlockingQueue<Integer> blockingQueue) {
            this.blockingQueue = blockingQueue;
            this.flag = false;
//            this.random = new Random();
            atomicInteger = new AtomicInteger();
        }

        @Override
        public void run() {
            while (!flag){
//                int info = this.random.nextInt(100);
                int info = atomicInteger.getAndIncrement();
                try {
                    blockingQueue.put(info);
                    System.out.println(Thread.currentThread().getName()+"producer" + info);
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public void  shutDown(){
            flag = true;
        }
    }

    //消费者
    public static class Consumer implements Runnable{

        private final BlockingQueue<Integer> blockingQueue;
        private volatile boolean flag;
//        private Random random;

        public Consumer(BlockingQueue<Integer> blockingQueue) {
            this.blockingQueue = blockingQueue;
            this.flag = false;
        }

        @Override
        public void run() {
            while(!flag){
                int info;
                try {
                    info = blockingQueue.take();
                    System.out.println(Thread.currentThread().getName()+" consumer "+info);
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

        public void shutDown(){
            flag=true;
        }
    }

//    public static void main(String[] args){
//        BlockingQueue<Integer> blockingQueue = new LinkedBlockingQueue<Integer>(10);
//        Producer producer=new Producer(blockingQueue);
//        Consumer consumer=new Consumer(blockingQueue);
//        //创建5个生产者，5个消费者
//        AtomicInteger atomicInteger = new AtomicInteger();
//        for(int i=0;i<10;i++){
//            if(i<5){
//                new Thread(producer,"producer"+atomicInteger.getAndIncrement()).start();
//            }else{
//                new Thread(consumer,"consumer"+atomicInteger.getAndIncrement()).start();
//            }
//        }
//
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        producer.shutDown();
//        consumer.shutDown();
//    }
    public static void main(String[] args) {
        Function<String,String> function = (x) -> {
            System.out.print(x+": ");
            return "Function";};

        System.out.println(function.apply("hello world"));
    }
}

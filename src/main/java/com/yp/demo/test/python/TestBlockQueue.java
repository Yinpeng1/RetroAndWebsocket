package com.yp.demo.test.python;

import java.util.Stack;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestBlockQueue<T> {

    private Lock producerLock = new ReentrantLock();
    private Condition notFull = producerLock.newCondition();

    private Lock consumerLock = new ReentrantLock();
    private Condition notEmpty = consumerLock.newCondition();

    private Stack<T> stack;

    private static int MAX_SIZE = 10;

    public TestBlockQueue() {
        stack = new Stack<>();
    }

    public void push(T t){
        final Lock lock = this.producerLock;
        lock.lock();
        try {
            while (stack.size() >= MAX_SIZE){
                System.out.println(Thread.currentThread().getName() + "正在阻塞");
                notFull.await();
            }
            stack.push(t);
            signalNotEmpty();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public T get(){
        T t = null;
        final Lock lock = this.consumerLock;
        lock.lock();
        try {
            while (stack.size() == 0){
                System.out.println(Thread.currentThread().getName() + "正在阻塞");
                notEmpty.await();
            }
            t = stack.pop();
            signalNotFull();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return t;
    }

    private void signalNotEmpty(){
        final Lock lock = this.consumerLock;
        lock.lock();
        try {
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    private void signalNotFull(){
        final Lock lock = this.producerLock;
        lock.lock();
        try {
            notFull.signal();
        } finally {
            lock.unlock();
        }
    }
}

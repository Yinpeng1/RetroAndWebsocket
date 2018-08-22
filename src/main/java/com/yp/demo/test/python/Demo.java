package com.yp.demo.test.python;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;

public class Demo implements BeanNameAware, BeanFactoryPostProcessor, BeanFactoryAware, InitializingBean, ApplicationContextAware {

    @Override
    public void setBeanName(String s) {

    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {

    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {

    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

    }

    private static void quick_sort(int[] arr, int startIndex, int endIndex) {
        if (startIndex >= endIndex) {
            return;
        }
        int pivotIndex = partition(arr, startIndex, endIndex);

        quick_sort(arr, startIndex, pivotIndex);
        quick_sort(arr, pivotIndex + 1, endIndex);

    }

    private static int partition(int[] arr, int startIndex, int endIndex) {
        int pivot = arr[startIndex];
        int left = startIndex;
        int right = endIndex;

        while (left != right) {
            while (left < right && arr[right] > pivot) {
                right--;
            }
            while (left < right && arr[left] <= pivot) {
                left++;
            }
            if (left < right) {
                int p = arr[left];
                arr[left] = arr[right];
                arr[right] = p;
            }
        }
        int p = arr[left];
        arr[left] = arr[startIndex];
        arr[startIndex] = p;

        return left;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{2, 1, 10, 7, 8, 9, 4, 6, 5};
        quick_sort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

//    public class LeakyDemo {
//        public long timeStamp = System.currentTimeMillis();
//        public int capacity; // 桶的容量
//        public int rate; // 水漏出的速度
//        public int water; // 当前水量(当前累积请求数)
//
//        public boolean grant() {
//            long now = System.currentTimeMillis();
//            water = max(0, water - (now - timeStamp) * rate); // 先执行漏水，计算剩余水量
//            timeStamp = now;
//            if ((water + 1) < capacity) {
//                // 尝试加水,并且水还未满
//                water += 1;
//                return true;
//            } else {
//                // 水满，拒绝加水
//                return false;
//            }
//        }
//    }
}

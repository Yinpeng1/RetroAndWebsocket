package com.yp.demo.test.python;

import org.springframework.util.ObjectUtils;

public class MiddleSearch {

    public static void main(String[] args) {
        //有序排列数组（大到小，小到大无所谓）
        int[] array = {1,2,3,4,5,6,7,8,9,10};
        //打印二分法的返回值
        System.out.println(searchMidden(array,0,array.length-1,9));
    }

    private static int searchMidden(int[] arr, int start,int end,int val){
        if (ObjectUtils.isEmpty(arr)){
            return -1;
        }
        if (start <= end){
            int middle = (start + end)/2;
            int middleValue = arr[middle];
            if (val == middleValue){
                return middle;
            } else if (middleValue > val){
                return searchMidden(arr, start, middle -1, val);
            } else {
                return searchMidden(arr, middle + 1, end, val);
            }
        } else {
            return -1;
        }
    }
}

package com.yp.demo.test.python;

public class Demo5 {

    private void quick_sort(int[] arr, int startIndex, int endIndex){
        if (startIndex >= endIndex){
            return;
        }
        int pivotIndex = partition(arr, startIndex, endIndex);

        quick_sort(arr, startIndex, pivotIndex);
        quick_sort(arr, pivotIndex + 1, endIndex);
    }

    private int partition(int[] arr, int startIndex, int endIndex){
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
}

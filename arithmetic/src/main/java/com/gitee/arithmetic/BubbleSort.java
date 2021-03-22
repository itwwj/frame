package com.gitee.arithmetic;


/**
 * @author jie
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = new int[]{11, 25, 79, 65, 34, 92, 18, 45, 83, 29, 76, 38};
        bubble(arr);
    }


    /**
     * 冒泡排序
     */
    private static void bubble(int[] arr) {

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - 1; j++) {
                if (arr[i] < arr[j]) {
                    int tem = arr[j];
                    arr[j] = arr[i];
                    arr[i] = tem;
                }
            }
        }
        for (int i : arr) {
            System.out.println(i);
        }
    }
}

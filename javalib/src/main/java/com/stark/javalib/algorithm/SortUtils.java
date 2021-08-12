package com.stark.javalib.algorithm;

import com.stark.javalib.utils.LogUtils;

public class SortUtils {

    private static final String TAG = "SortUtils";

    public static void quickSort(int[] nums) {
        LogUtils.d(TAG + "lpq", "mergeSort: 原始数据");
        LogUtils.d(TAG + "lpq", "mergeSort: nums.length = " + nums.length);
        printArray(nums);
        LogUtils.d(TAG + "lpq", "mergeSort: 排序过程");
        quickSortRecursive(nums, 0, nums.length - 1);
        LogUtils.d(TAG + "lpq", "mergeSort: 结果数据");
        LogUtils.d(TAG + "lpq", "mergeSort: nums.length = " + nums.length);
        printArray(nums);
    }

    private static void quickSortRecursive(int[] nums, int start, int end) {
        if (start < end) {
            int position = arrange(nums, start, end);
            quickSortRecursive(nums, start, position - 1);
            quickSortRecursive(nums, position + 1, end);
        }

    }

    private static int arrange(int[] nums, int start, int end) {
        /**
         * 3 个步骤：
         * 1、小于值的数向左移动;
         * 2、大于值得数向右移动；
         * 3、把值放在空格位置；
         */
//        LogUtils.d(TAG + "lpq", "arrange: 一次归并 start = " + start + " end = " + end);
//        printArray(nums);
        int value = nums[end];
        int k = start;
        for (int i = start; i < end; ++i) {
            if (nums[i] < value) {
                int temp = nums[i];
                nums[i] = nums[k];
                nums[k] = temp;
                ++k;
            }
        }
        int temp = nums[end];
        nums[end] = nums[k];
        nums[k] = temp;
//        LogUtils.d(TAG + "lpq", "arrange: 一次归并结束 value = " + nums[k]);
//        printArray(nums);
        return k;
    }

    // 归并排序
    public static void mergeSort(int[] nums) {
        LogUtils.d(TAG + "lpq", "mergeSort: 原始数据");
        LogUtils.d(TAG + "lpq", "mergeSort: nums.length = " + nums.length);
        printArray(nums);
        LogUtils.d(TAG + "lpq", "mergeSort: 排序过程");
        mergeSortRecursive(nums, 0, nums.length - 1);
        LogUtils.d(TAG + "lpq", "mergeSort: 结果数据");
        LogUtils.d(TAG + "lpq", "mergeSort: nums.length = " + nums.length);
        printArray(nums);
    }

    public static void mergeSortRecursive(int[] nums, int start, int end) {
//        printArray(nums);
//        LogUtils.d(TAG + "lpq", "mergeSortRecursive: start = " + start + " end = " + end);
        if (start >= end) {
            return;
        }
        int temp = (start + end) / 2;
//        LogUtils.d(TAG + "lpq", "mergeSortRecursive: 分开");
        mergeSortRecursive(nums, start, temp);
        mergeSortRecursive(nums, temp + 1, end);
        merge(nums, start, temp, end);
    }

    private static void merge(int[] nums, int start, int mid, int end) {
//        LogUtils.d(TAG + "lpq", "merge: 合并");
//        LogUtils.d(TAG + "lpq", "merge: start = " + start + " mid = " + mid + " end = " + end);
//        printArray(nums);
        int[] tempNums = new int[end - start + 1];
        int i = start;
        int j = mid + 1;
        int k = 0;
        while (i <= mid && j <= end) {
            if (nums[i] < nums[j]) {
                tempNums[k++] = nums[i++];
            } else {
                tempNums[k++] = nums[j++];
            }
        }
        while (i <= mid) {
            tempNums[k++] = nums[i++];
        }
        while (j <= end) {
            tempNums[k++] = nums[j++];
        }
        for (int z = 0; z < k; ++z) {
            nums[start + z] = tempNums[z];
        }
//        LogUtils.d(TAG + "lpq", "merge: 本次合并后");
//        printArray(nums);
    }

    // 冒泡算法
    public static void bubbleSort(int[] nums) {
        LogUtils.d(TAG + "lpq", "bubbleSort: 原始数据");
        LogUtils.d(TAG + "lpq", "bubbleSort: nums.length = " + nums.length);
        printArray(nums);
        LogUtils.d(TAG + "lpq", "bubbleSort: 排序过程");
        for (int i = 0; i < nums.length; ++i) {
            boolean hasChange = false;
            for (int j = 0; j < nums.length - i- 1; ++j) {
                if (nums[j] > nums[j + 1]) {
                    int temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;
                    hasChange = true;
                }
            }
//            printArray(nums);
            if (!hasChange && i < nums.length - 1) {
//                LogUtils.d(TAG + "lpq", "bubbleSort: 排序提前完成");
                break;
            }
        }
        LogUtils.d(TAG + "lpq", "bubbleSort: 结果数据");
        LogUtils.d(TAG + "lpq", "bubbleSort: nums.length = " + nums.length);
        printArray(nums);
    }

    /**
     * 插入排序
     * @param nums
     */
    public static void insertSort(int[] nums) {
        LogUtils.d(TAG + "lpq", "insertSort: 原始数据");
        LogUtils.d(TAG + "lpq", "insertSort: nums.length = " + nums.length);
        printArray(nums);
        LogUtils.d(TAG + "lpq", "insertSort: 排序过程");

        for (int i = 1; i < nums.length; ++i) {
            // 效率较低的做法，需要交换的次数过多
//            for (int j = i; j > 0; --j) {
//                if (nums[j - 1] > nums[j]) {
//                    int temp = nums[j - 1];
//                    nums[j - 1] = nums[j];
//                    nums[j] = temp;
//                } else {
//                    break;
//                }
//            }
            int value = nums[i];
            int j = i - 1;
            for (; j >= 0; --j) {
                if (nums[j] > value) {
                    nums[j+1] = nums[j];
                } else {
                    break;
                }
            }
            nums[j + 1] = value;
            printArray(nums);
        }

        LogUtils.d(TAG + "lpq", "insertSort: 结果数据");
        LogUtils.d(TAG + "lpq", "insertSort: nums.length = " + nums.length);
        printArray(nums);
    }

    public static void printArray(int[] nums) {
//        StringBuilder builder = new StringBuilder();
//        for (int i = 0; i < nums.length; ++i) {
//            builder.append("[").append(nums[i]).append("] ");
//        }
//        LogUtils.d(TAG + "lpq", "printArray: nums = " + builder.toString());
    }
}

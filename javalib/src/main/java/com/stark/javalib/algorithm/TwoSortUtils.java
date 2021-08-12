package com.stark.javalib.algorithm;

import com.stark.javalib.utils.LogUtils;

public class TwoSortUtils {

    private static final String TAG = "TwoSortUtils";

    public static int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int outLow = 0;
        int outHigh = nums.length - 1;
        int outMid = (outLow + outHigh) / 2;
        while(outLow <= outHigh) {
            if (nums[outMid] == target) {
                return outMid;
            }
            if (nums[outMid] >= nums[outLow]) {
                // 这里有序,使用二分查找
                if (target >= nums[outLow] && target <= nums[outMid]) {
                    int low = outLow;
                    int high = outMid;
                    while (low <= high) {
                        int mid = (low + high)/2;
                        if (nums[mid] > target) {
                            high = mid - 1;
                        } else if (nums[mid] < target) {
                            low = mid + 1;
                        } else {
                            return mid;
                        }
                    }
                    LogUtils.d(TAG + "lpq", "search: 算法错误1");
                    break;
                } else {
                    outLow = outMid + 1;
                    outMid = (outLow + outHigh) / 2;;
                }
            } else {
                if (target >= nums[outMid] && target<= nums[outHigh]) {
                    // 二分查找
                    int low = outMid;
                    int high = outHigh;
                    while (low <= high) {
                        int mid = (low + high)/2;
                        if (nums[mid] > target) {
                            high = mid - 1;
                        } else if (nums[mid] < target) {
                            low = mid + 1;
                        } else {
                            return mid;
                        }
                    }
                    LogUtils.d(TAG + "lpq", "search: 算法错误2");
                    break;
                } else {
                    outHigh = outMid - 1;
                    outMid = (outLow + outHigh) / 2;;
                }
            }
        }
        LogUtils.d(TAG + "lpq", "search: 未找到对应值");
        return -1;
    }

    /**
     * 求一个数的平方根
     * 使用二分法查找
     */
    public static double MySqrt(double target) {
        LogUtils.d(TAG + "lpq", "MySqrt: 要求平方根的数为：" + target);
        LogUtils.d(TAG + "lpq", "MySqrt: 正确结果是：" + Math.sqrt(target));
        if (target <= 0) {
            return -1;
        }
        double low = 0;
        double high = target;
        double mid = (low + high) / 2;
        double result = mid * mid;
        while ( Math.abs(result - target) > 0.000001) {
            if (result > target) {
                high = mid;
            } else {
                low = mid;
            }
            mid = (low + high) / 2;
            result = mid * mid;
        }
        LogUtils.d(TAG + "lpq", "MySqrt: 算法计算结果是：" + mid);
        return mid;
    }
}

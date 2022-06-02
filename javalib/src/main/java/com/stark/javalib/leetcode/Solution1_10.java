/**
 * Copyright (C), 2007-2022, 未来穿戴有限公司
 * FileName: Solution
 * Author: lpq
 * Date: 2022/6/2 9:14
 * Description: 用一句话描述下
 */
package com.stark.javalib.leetcode;

import java.util.HashMap;

/**
 * @ProjectName: MyPratice
 * @Package: com.stark.javalib.algorithm
 * @ClassName: Solution
 * @Description: 用一句话描述下
 * @Author: lpq
 * @CreateDate: 2022/6/2 9:14
 */
public class Solution1_10 {

    // 题1
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; ++i) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }
        return null;
    }
}

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

    public static void main(String[] args) {

    }

    // 题1
    public static int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; ++i) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }
        return null;
    }

    /**
     * 给你两个非空 的链表，表示两个非负的整数。它们每位数字都是按照逆序的方式存储的，并且每个节点只能存储一位数字。
     * <p>
     * 请你将两个数相加，并以相同形式返回一个表示和的链表。
     * <p>
     * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
     * <p>
     * 链接：https://leetcode.cn/problems/add-two-numbers
     *
     * 执行用时：
     * 1 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：41.2 MB, 在所有 Java 提交中击败了84.98%的用户
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode aHead = null;
        ListNode aTail = null;
        ListNode curNode1 = l1;
        ListNode curNode2 = l2;
        int carry = 0;
        while (curNode1 != null || curNode2 != null) {
            int val1 = 0;
            int val2 = 0;
            if (curNode1 != null) {
                val1 = curNode1.val;
            }
            if (curNode2 != null) {
                val2 = curNode2.val;
            }
            // 循环计算结果
            int temp = curNode1.val + curNode2.val + carry;
            if (temp >= 10) {
                carry = 1;
                temp -= 10;
            } else {
                carry = 0;
            }
            if (aHead == null) {
                aHead = new ListNode(temp);
            } else {
                aTail = new ListNode(temp);
                if (aHead.next == null) {
                    aHead.next = aTail;
                }
                aTail = aTail.next;
            }
            curNode1 = curNode1.next;
            curNode2 = curNode2.next;
        }
        return aHead;
    }


    //    Definition for singly-linked list.
    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}

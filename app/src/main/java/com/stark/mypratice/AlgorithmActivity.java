package com.stark.mypratice;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.stark.javalib.algorithm.SortUtils;
import com.stark.javalib.algorithm.TwoSortUtils;
import com.stark.javalib.utils.LogUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class AlgorithmActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";
    private Button btnBubbleSort;
    private Button btnInsertSort;
    private Button btnMergeSort;
    private Button btnQuickSort;
    private Button btnCreateNewArray;
    private Button btnSystemArraySort;
    private Button btnRequestSqrt;
    private Button btnQueryTarget;
    private Button btnHashMap;
    private Button btnMd5Test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.algorithm);
        initView();
    }

    private void initView() {
        btnBubbleSort = (Button) findViewById(R.id.btn_bubble_sort);
        btnBubbleSort.setOnClickListener(this);
        btnInsertSort = (Button) findViewById(R.id.btn_insert_sort);
        btnInsertSort.setOnClickListener(this);
        btnMergeSort = (Button) findViewById(R.id.btn_merge_sort);
        btnMergeSort.setOnClickListener(this);
        btnQuickSort = (Button) findViewById(R.id.btn_quick_sort);
        btnQuickSort.setOnClickListener(this);
        current = getIntNums(count);
        btnCreateNewArray = (Button) findViewById(R.id.btn_create_new_array);
        btnCreateNewArray.setOnClickListener(this);

//        Collections.sort();
        btnSystemArraySort = (Button) findViewById(R.id.btn_system_array_sort);
        btnSystemArraySort.setOnClickListener(this);
        btnRequestSqrt = (Button) findViewById(R.id.btn_request_sqrt);
        btnRequestSqrt.setOnClickListener(this);
        btnQueryTarget = (Button) findViewById(R.id.btn_query_target);
        btnQueryTarget.setOnClickListener(this);
        btnHashMap = (Button) findViewById(R.id.btn_hash_map);
        btnHashMap.setOnClickListener(this);
        btnMd5Test = (Button) findViewById(R.id.btn_md5_test);
        btnMd5Test.setOnClickListener(this);
    }

    int count = 1000;
    int[] current;

    @Override
    public void onClick(View v) {
//        int[] nums = new int[]{ 2, 6, 3, 1, 4, 6, 8, 5, 7, 3};
        int[] nums = current.clone();
//        int[] nums = new int[]{2, 6, 3, 1};
        long startTime = System.currentTimeMillis();
        LogUtils.d(TAG + "lpq", "onClick: 开始：" + startTime);
        switch (v.getId()) {
            case R.id.btn_bubble_sort:
                SortUtils.bubbleSort(nums);
                break;
            case R.id.btn_insert_sort:
                SortUtils.insertSort(nums);
                break;
            case R.id.btn_merge_sort:
                SortUtils.mergeSort(nums);
                break;
            case R.id.btn_quick_sort:
                SortUtils.quickSort(nums);
                break;
            case R.id.btn_system_array_sort:
                Arrays.sort(nums);
                break;
            case R.id.btn_create_new_array:
                count *= 10;
                current = getIntNums(count);
                break;
            case R.id.btn_request_sqrt:
                TwoSortUtils.MySqrt(6);
                break;
            case R.id.btn_query_target:
                TwoSortUtils.search(new int[]{1, 3, 5}, 2);
                break;
            case R.id.btn_hash_map:
                LogUtils.d(TAG + "lpq", "onClick: HashMap");
                HashMap<Integer, Integer> hashMap = new HashMap<>();
                hashMap.put(3, 11);
                hashMap.put(1, 12);
                hashMap.put(5, 23);
                hashMap.put(2, 22);

                for (Map.Entry entry : hashMap.entrySet()) {
                    LogUtils.d(TAG + "lpq", "onClick: entry.key = " + entry.getKey() + " value = " + entry.getValue());
                }

                LogUtils.d(TAG + "lpq", "onClick: LinkedHashMap");
//                HashMap<Integer, Integer> m = new LinkedHashMap<>();
//                m.put(3, 11);
//                m.put(1, 12);
//                m.put(5, 23);
//                m.put(2, 22);
//
//                for (Map.Entry e : m.entrySet()) {
//                    LogUtils.d(TAG + "lpq", "onClick: entry.key = " + e.getKey() + " value = " + e.getValue());
//                }

                // 10 是初始大小，0.75 是装载因子，true 是表示按照访问时间排序
                HashMap<Integer, Integer> m = new LinkedHashMap<>(10, 0.75f, true);
                m.put(3, 11);
                m.put(1, 12);
                m.put(5, 23);
                m.put(2, 22);


                m.put(3, 26);
                m.get(5);

                for (Map.Entry e : m.entrySet()) {
                    LogUtils.d(TAG + "lpq", "onClick: entry.key = " + e.getKey() + " value = " + e.getValue());
                }

                break;
            case R.id.btn_md5_test:
                String password = "jiangluo22";
                String encode = md5(password);
                LogUtils.d(TAG + "lpq", "onClick: password = " + password);
                LogUtils.d(TAG + "lpq", "onClick: 加密后 = " + encode);
                break;
        }
        long endTime = System.currentTimeMillis();
        LogUtils.d(TAG + "lpq", "onClick: 结束：" + endTime);
        LogUtils.d(TAG + "lpq", "onClick: 时间差：" + (endTime - startTime));
    }

    public static String md5(String string) {
        if (TextUtils.isEmpty(string)) {
            return "";
        }
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(string.getBytes());
            String result = "";
            for (byte b : bytes) {
                String temp = Integer.toHexString(b & 0xff);
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }
                result += temp;
            }
            return result;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    public int[] getIntNums(int n) {
        int[] newData = new int[n];
        for (int i = 0; i < n; ++i) {
            newData[i] = (int) Math.round(Math.random() * n);
        }
        return newData;
    }
}

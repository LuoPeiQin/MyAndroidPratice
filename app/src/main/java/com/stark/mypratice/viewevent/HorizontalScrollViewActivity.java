package com.stark.mypratice.viewevent;

import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.stark.mypratice.R;
import com.stark.mypratice.utils.ScreenUtils;
import com.stark.mypratice.viewevent.custom_view.HorizontalScrollViewEx;

import java.util.ArrayList;

public class HorizontalScrollViewActivity extends AppCompatActivity {


    private HorizontalScrollViewEx horizontalScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horizontal_scroll_view);
        initView();
        initData();
    }

    private void initView() {
        horizontalScrollView = (HorizontalScrollViewEx) findViewById(R.id.horizontal_scroll_view);
    }

    private void initData() {
        LayoutInflater layoutInflater = getLayoutInflater();

        DisplayMetrics screenMetrics = ScreenUtils.getScreenMetrics(this);
        final int widthPixels = screenMetrics.widthPixels;
        final int heightPixels = screenMetrics.heightPixels;
        for (int i = 0; i < 3; ++i) {
            ViewGroup layout = (ViewGroup) layoutInflater.inflate(R.layout.content_layout, horizontalScrollView, false);
            layout.getLayoutParams().width = widthPixels;
            TextView tvTitle = layout.findViewById(R.id.tv_title);
            tvTitle.setText("标题" + (i + 1));
            tvTitle.setBackgroundColor(Color.rgb(255/(i + 1), 255/(i + 1), 0));
            setListView(layout);
            horizontalScrollView.addView(layout);
        }
    }

    private void setListView(ViewGroup layout) {
        ListView listView = layout.findViewById(R.id.lv_content);
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0; i < 50; ++i) {
            arrayList.add("item " + (i+1));
        }
        ArrayAdapter arrayAdapter = new ArrayAdapter<>(this, R.layout.content_list_item, R.id.name, arrayList);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(HorizontalScrollViewActivity.this, "click item",
                        Toast.LENGTH_SHORT).show();

            }
        });
    }
}

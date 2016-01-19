package com.kevin.utils.samples.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kevin.utils.samples.R;

/**
 * Created by zhouwk on 2016/1/19 0019.
 */
public class BaseActivity extends AppCompatActivity {
    protected Context mContent;

    /** 嵌套在ScrollView中的内容容器 */
    protected LinearLayout linearLayoutContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        mContent = this.getApplicationContext();

        linearLayoutContent = (LinearLayout) this.findViewById(R.id.act_base_ll_content);
    }

    /**
     * 添加TextView到内容容器
     * @param info
     */
    protected void addInfo(String info) {
        LayoutInflater inflater = LayoutInflater.from(this);
        RelativeLayout layout = (RelativeLayout) inflater.inflate(R.layout.item_function, null);
        TextView infoTextView = (TextView) layout.findViewById(R.id.function_item_text);
        infoTextView.setText(info);
        linearLayoutContent.addView(layout);
    }

    /**
     * 添加TextView到内容容器
     * @param info
     */
    protected void addInfo(String info, View.OnClickListener l) {
        LayoutInflater inflater = LayoutInflater.from(this);
        RelativeLayout layout = (RelativeLayout) inflater.inflate(R.layout.item_function, null);
        TextView infoTextView = (TextView) layout.findViewById(R.id.function_item_text);
        infoTextView.setText(info);
        layout.setOnClickListener(l);
        linearLayoutContent.addView(layout);
    }
}

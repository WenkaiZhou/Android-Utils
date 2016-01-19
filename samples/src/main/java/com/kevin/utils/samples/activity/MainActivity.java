package com.kevin.utils.samples.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.kevin.utils.samples.FunctionItem;
import com.kevin.utils.samples.R;
import com.kevin.utils.samples.adapter.ContentAdapter;
import com.kevin.wraprecyclerview.BaseRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 版权所有：----有限公司
 *
 * MainActivity
 *
 * @author zhou.wenkai ,Created on 2016-1-19 09:30:53
 * Major Function：帮助工具实例Demo
 *
 * 注:如果您修改了本类请填写以下内容作为记录，如非本人操作劳烦通知，谢谢！！！
 * @author mender，Modified Date Modify Content:
 */
public class MainActivity extends AppCompatActivity {

    private Context mContent;
    private RecyclerView mRecyclerView;
    private ContentAdapter mAdapter;

    private String[] functionNames = {"系统信息帮助类", "CPU信息帮助类", "应用信息帮助类", "应用包内数据读取帮助类", "屏幕信息帮助类"};
    private Class[] classNames = {AndroidOSActivity.class, CPUInfoActivity.class, ApplicationActivity.class, LocalFileActivity.class, ScreenActivity.class};
    private List<FunctionItem> mFunctionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContent = this.getApplicationContext();

        initViews();
        initFunctionList();
        initEvent();
    }

    /**
     * 初始化View
     */
    private void initViews() {
        mRecyclerView = (RecyclerView)this.findViewById(R.id.main_act_recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContent));
        mAdapter = new ContentAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
    }

    /**
     * 初始化功能条目
     */
    private void initFunctionList() {
        mFunctionList = new ArrayList<>(functionNames.length);
        for(String functionName: functionNames) {
            mFunctionList.add(new FunctionItem(functionName));
        }
        mAdapter.setItemLists(mFunctionList);
    }

    /**
     * 初始化事件
     */
    private void initEvent() {
        mAdapter.setOnRecyclerViewListener(new BaseRecyclerAdapter.OnRecyclerViewListener() {
            @Override
            public void onItemClick(View view, int position) {
                startActivity(new Intent(MainActivity.this, classNames[position]));
            }

            @Override
            public boolean onItemLongClick(int position) {
                return false;
            }
        });
    }

}

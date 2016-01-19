package com.kevin.utils.samples.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.kevin.utils.LocalFileUtil;
import com.kevin.utils.samples.R;

/**
 * Created by zhouwk on 2016/1/19 0019.
 */
public class LocalFileActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContent = this.getApplicationContext();

        initViews();
    }

    /**
     * View初始化
     */
    private void initViews() {
        addInfo("获取Asset下文本内容", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fileText = LocalFileUtil.getStringFormAsset(mContent, "test_assets.txt");
                if (!TextUtils.isEmpty(fileText)) {
                    Toast.makeText(mContent, fileText, Toast.LENGTH_LONG).show();
                }
            }
        });
        addInfo("获取raw下文本内容", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fileText = LocalFileUtil.getStringFormRaw(mContent, R.raw.test_raw);
                if (!TextUtils.isEmpty(fileText)) {
                    Toast.makeText(mContent, fileText, Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}

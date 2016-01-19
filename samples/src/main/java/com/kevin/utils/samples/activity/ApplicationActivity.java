package com.kevin.utils.samples.activity;

import android.os.Bundle;
import android.view.View;

import com.kevin.utils.ApplicationUtil;

/**
 * Created by zhouwk on 2016/1/19 0019.
 */
public class ApplicationActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initViews();
    }

    /**
     * View初始化
     */
    private void initViews() {
        addInfo("应用名称：" + ApplicationUtil.getAppName(mContent));
        addInfo("应用版本名称：" + ApplicationUtil.getVersionName(mContent));
        addInfo("应用版本号：" + ApplicationUtil.getVersionCode(mContent));
        addInfo("打开商店进行评分", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApplicationUtil.openStore(mContent, "您的手机未安装市场");
            }
        });
    }
}

package com.kevin.utils.samples.activity;

import android.os.Bundle;

import com.kevin.utils.ScreenUtil;

/**
 * Created by zhouwk on 2016/1/19 0019.
 */
public class ScreenActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
    }

    /**
     * View初始化
     */
    private void initViews() {
        addInfo("屏幕可操作区域宽度：" + ScreenUtil.getScreenWidth(this) + "px");
        addInfo("屏幕可操作区域高度：" + ScreenUtil.getScreenHeight(this) + "px");
        addInfo("屏幕可操作区域大小：" + ScreenUtil.getScreenSize(this) + "px");
        addInfo("屏幕真实宽度：" + ScreenUtil.getRealWidth(this) + "px");
        addInfo("屏幕真实高度：" + ScreenUtil.getRealHeight(this) + "px");
        addInfo("屏幕真实大小：" + ScreenUtil.getRealSize(this) + "px");
        addInfo("屏幕宽度ppi：" + ScreenUtil.getWidthPpi(this) + "px/inch");
        addInfo("屏幕高度ppi：" + ScreenUtil.getHeightPpi(this) + "px/inch");
        addInfo("屏幕ppi：" + ScreenUtil.getScreenPpi(this) + "px/inch");
        addInfo("屏幕物理宽度尺寸：" + ScreenUtil.getWidthInch(this) + "英寸");
        addInfo("屏幕物理宽度尺寸：" + ScreenUtil.getHeightInch(this) + "英寸");
        addInfo("屏幕物理尺寸：" + ScreenUtil.getScreenInch(this) + "英寸");
    }
}

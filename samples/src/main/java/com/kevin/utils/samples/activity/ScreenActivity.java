package com.kevin.utils.samples.activity;

import android.os.Bundle;

import com.kevin.utils.CPUInfo;
import com.kevin.utils.ScreenUtil;

/**
 * Created by zhouwk on 2016/1/19 0019.
 */
public class ScreenActivity extends BaseActivity {

    private ScreenUtil mScreenUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mScreenUtil = ScreenUtil.getInstance(this);

        initViews();
    }

    /**
     * View初始化
     */
    private void initViews() {
        addInfo("屏幕宽度：" + mScreenUtil.getScreenWidth() + "px");
        addInfo("屏幕高度：" + mScreenUtil.getScreenHeight() + "px");
        addInfo("屏幕宽度比例("+mScreenUtil.getScreenWidth() +"px / 320px"+")：" + mScreenUtil.getWidthRatio());
        addInfo("屏幕高度比例("+mScreenUtil.getScreenHeight() +"px / 480px"+")：" + mScreenUtil.getHeightRatio());
        addInfo("屏幕密度：" + mScreenUtil.getScreenDensity());
        addInfo("像素点密度：" + mScreenUtil.getDensityDpi());
        addInfo("状态栏高度：" + mScreenUtil.getStatusBarHeight() + "px");
        addInfo("屏幕尺寸：" + mScreenUtil.getScreenSize() + "英寸");
    }
}

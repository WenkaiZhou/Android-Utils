package com.kevin.utils.samples.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ActionMenuView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kevin.utils.ScreenUtil;
import com.kevin.utils.samples.R;

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
        addInfo("是不是平板：" + (ScreenUtil.isTablet(this) ? "是" : "否"));
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
        addInfo("屏幕密度：" + ScreenUtil.getScreenDensity(this));
        addInfo("屏幕DPI(PPI)：" + ScreenUtil.getDensityDpi(this));
        addInfo("屏幕可操作区域宽度dp数目：" + ScreenUtil.getScreenWidthDp(this) + "dp");
        addInfo("屏幕可操作区域高度dp数目：" + ScreenUtil.getScreenHeightDp(this) + "dp");
        addInfo("屏幕真实宽度dp数目：" + ScreenUtil.getRealWidthDp(this) + "dp");
        addInfo("屏幕真实高度dp数目：" + ScreenUtil.getRealHeightDp(this) + "dp");
        addInfo("屏幕状态栏的高度：" + ScreenUtil.getStatusBarHeight(this) + "dp");
        addInfo("屏幕截图(包含状态栏)", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addPicture(ScreenUtil.snapShotWithStatusBar(ScreenActivity.this));
            }
        });
        addInfo("屏幕截图(不包含状态栏)", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addPicture(ScreenUtil.snapShotWithoutStatusBar(ScreenActivity.this));
            }
        });
    }

    /**
     * 添加TextView到内容容器
     * @param bitmap
     */
    protected void addPicture(Bitmap bitmap) {
        ImageView imageView = new ImageView(this);
        imageView.setImageBitmap(bitmap);
        int width = ScreenUtil.getRealWidth(this) ;
        int height = ScreenUtil.getRealHeight(this) ;
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, height);
        linearLayoutContent.addView(imageView, params);
    }

}

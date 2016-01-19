package com.kevin.utils.samples.activity;

import android.os.Bundle;
import android.text.format.Formatter;

import com.kevin.utils.AndroidOSInfo;

/**
 * Created by zhouwk on 2016/1/19 0019.
 */
public class AndroidOSActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initViews();
    }

    /**
     * View初始化
     */
    private void initViews() {
        addInfo("是否是MIUI系统：" + (AndroidOSInfo.isMIUI() ? "是" : "否"));
        addInfo("是否是Flyme系统：" + (AndroidOSInfo.isFlyme() ? "是" : "否"));
        addInfo("当前系统平台："+ AndroidOSInfo.getPlatform());
        addInfo("手机品牌："+ AndroidOSInfo.getDeviceName());
        addInfo("操作系统版本号："+ AndroidOSInfo.getVersion());
        addInfo("手机型号："+ AndroidOSInfo.getModel());
        addInfo("手机机主名称："+ AndroidOSInfo.getName());
//        addInfo("CPU架构 "+ AndroidOSInfo.getCpuAbi());
        addInfo("设备IMEI："+ AndroidOSInfo.getIMEI(this));
        addInfo("设备UUID："+ AndroidOSInfo.getUUID(this));
        addInfo("是否Root："+ (AndroidOSInfo.hasRoot() ? "是" : "否"));
        addInfo("手机总内存：" + AndroidOSInfo.getTotalRam(mContent) + "b\n" +
                Formatter.formatFileSize(mContent, AndroidOSInfo.getTotalRam(mContent)));
        addInfo("手机可用内存：" + AndroidOSInfo.getAvailRam(mContent) + "b\n" +
                Formatter.formatFileSize(mContent, AndroidOSInfo.getAvailRam(mContent)));
    }

}

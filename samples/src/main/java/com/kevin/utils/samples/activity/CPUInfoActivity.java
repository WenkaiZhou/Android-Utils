package com.kevin.utils.samples.activity;

import android.os.Bundle;

import com.kevin.utils.CPUInfo;

/**
 * Created by zhouwk on 2016/1/19 0019.
 */
public class CPUInfoActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initViews();
    }

    /**
     * View初始化
     */
    private void initViews() {
        addInfo("CPU名称：" + CPUInfo.getCpuName());
        addInfo("CPU核数：" + CPUInfo.getCPUCores() + "核");
        addInfo("CPU最小频率：" + CPUInfo.getMinCpuFreq() + "KHZ");
        addInfo("CPU当前频率：" + CPUInfo.getCurCpuFreq() + "KHZ");
        addInfo("CPU最大频率：" + CPUInfo.getMaxCpuFreq() + "KHZ");
    }
}

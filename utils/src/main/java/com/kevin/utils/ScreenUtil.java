package com.kevin.utils;

import java.lang.reflect.Field;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

/**
 * 版权所有：XXX有限公司</br>
 * 
 * ScreenUtil </br>
 * 
 * @author zhou.wenkai ,Created on 2014-12-2 11:09:58</br>
 * @Description Major Function：ScreenUtils </br>
 * 
 * 注:如果您修改了本类请填写以下内容作为记录，如非本人操作劳烦通知，谢谢！！！</br>
 * @author mender，Modified Date Modify Content:
 */
public class ScreenUtil {
	
	/** 上下文 */
	private static Context mContext = null;
    /** 基准分辨率的宽 */
	private double STANDARD_SCREEN_WIDTH  = 320;
    /** 基准分辨率的高 */
    protected double STANDARD_SCREEN_HEIGHT = 480;
	
	// 单例模式
	private static ScreenUtil SingleInstance = null;
	private ScreenUtil() {}
	public static ScreenUtil getInstance(Context context) {
		if (context instanceof Activity) {
			mContext = context;
		} else {
			throw new IllegalArgumentException("context must be instanceof Activity");
		}
		if(SingleInstance == null) {
			SingleInstance = new ScreenUtil();
		}
		return SingleInstance;
	}
	
    /**
     * 获取屏幕宽度
     * @return
     */
    public double getScreenWidth() {
	    DisplayMetrics displayMetrics = new DisplayMetrics();
	    ((Activity) mContext).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
    	return displayMetrics.widthPixels;
    }
	
    /**
     * 获取屏幕高度
     * @return
     */
    public double getScreenHeight() {
	    DisplayMetrics displayMetrics = new DisplayMetrics();
	    ((Activity) mContext).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
    	return displayMetrics.heightPixels;
    }

    /**
     * 计算屏幕宽度比例  (Mi3测试 3.375 )
     * @return
     */
    public double getWidthRatio() {
    	return getScreenWidth() / STANDARD_SCREEN_WIDTH;
    }
    
    /**
     * 计算屏幕高度比例  (Mi3测试 4.0 )
     * @return
     */
    public double getHeightRatio() {
    	return getScreenHeight() / STANDARD_SCREEN_HEIGHT;
    }

    /**
     * 获取屏幕密度
     *
     * @return
     */
    public float getScreenDensity() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) mContext).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.density;
    }

    /**
     * 获取每英寸的点数(打印分辨率,区别PPI)
     *
     * @return
     */
    public float getDensityDpi() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) mContext).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.densityDpi;
    }
    
    /**
     * 获取通知栏的高度
     *
     * @return
     */
    public int getStatusBarHeight() {
        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0, statusBarHeight = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            statusBarHeight = mContext.getResources().getDimensionPixelSize(x);
        } catch (Exception e1) {
            e1.printStackTrace();
        } 
        return statusBarHeight;
    }
    
    /** 
     * 获取当前屏幕截图，包含状态栏 
     *
     * @return 
     */  
    public Bitmap snapShotWithStatusBar() {  
        View view = ((Activity) mContext).getWindow().getDecorView();  
        view.setDrawingCacheEnabled(true);  
        view.buildDrawingCache();
        Bitmap bmp = view.getDrawingCache();
        int width = (int) getScreenWidth();
        int height = (int) getScreenHeight();
        Bitmap bp = null;
        bp = Bitmap.createBitmap(bmp, 0, 0, width, height);  
        view.destroyDrawingCache();  
        return bp;
    }
    
    /** 
     * 获取当前屏幕截图，不包含状态栏 
     *
     * @return 
     */  
    public Bitmap snapShotWithoutStatusBar() {  
        View view = ((Activity) mContext).getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bmp = view.getDrawingCache();
        Rect frame = new Rect();
        ((Activity) mContext).getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        int statusBarHeight = frame.top;
  
        int width = (int) getScreenWidth();
        int height = (int) getScreenHeight();
        Bitmap bp = null;
        bp = Bitmap.createBitmap(bmp, 0, statusBarHeight, width, height - statusBarHeight);
        view.destroyDrawingCache();  
        return bp;
    }

    /**
     * 获取屏幕尺寸
     * dpi＝（√（横向分辨率^2+纵向分辨率^2））/屏幕尺寸）
     *
     * @return
     */
    public double getScreenSize() {
//        double x = Math.pow(getScreenWidth(), 2);
//        double y = Math.pow(getScreenHeight(), 2);
//
//        DisplayMetrics displayMetrics = new DisplayMetrics();
//        ((Activity) mContext).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
//        return Math.sqrt(x + y) / displayMetrics.densityDpi;

        Point point = new Point();
        ((Activity) mContext).getWindowManager().getDefaultDisplay().getRealSize(point);
        DisplayMetrics dm = mContext.getResources().getDisplayMetrics();
        double x = Math.pow(point.x/ dm.xdpi, 2);
        double y = Math.pow(point.y / dm.ydpi, 2);
        double screenInches = Math.sqrt(x + y);
        return screenInches;
    }
    
}

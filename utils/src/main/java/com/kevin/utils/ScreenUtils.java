package com.kevin.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * 版权所有：XXX有限公司</br>
 * 
 * ScreenUtils </br>
 * 
 * @author zhou.wenkai ,Created on 2014-12-2 11:09:58</br>
 * @Description Major Function：ScreenUtils </br>
 * 
 * 注:如果您修改了本类请填写以下内容作为记录，如非本人操作劳烦通知，谢谢！！！</br>
 * @author mender，Modified Date Modify Content:
 */
public class ScreenUtils {

    /** 基准分辨率的宽 */
	private double STANDARD_SCREEN_WIDTH  = 320;
    /** 基准分辨率的高 */
    protected double STANDARD_SCREEN_HEIGHT = 480;

    /**
     * 获取屏幕可操作区域宽度
     *
     * @param activity
     * @return 屏幕可操作区域宽度
     */
    public static int getScreenWidth(Activity activity) {
	    DisplayMetrics displayMetrics = new DisplayMetrics();
	    activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
    	return displayMetrics.widthPixels;
    }

    /**
     * 获取屏幕可操作区域高度
     *
     * @param activity
     * @return 屏幕可操作区域高度
     */
    public static int getScreenHeight(Activity activity) {
	    DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
    	return displayMetrics.heightPixels;
    }

    /**
     * 计算屏幕宽度比例  (Mi3测试 3.375 )
     * @return
     */
    public double getWidthRatio(Activity context) {
    	return getScreenWidth(context) / STANDARD_SCREEN_WIDTH;
    }
    
    /**
     * 计算屏幕高度比例  (Mi3测试 4.0 )
     * @return
     */
    public double getHeightRatio(Activity context) {
    	return getScreenHeight(context) / STANDARD_SCREEN_HEIGHT;
    }

    /**
     * 获取屏幕密度
     *
     * @return
     */
    public float getScreenDensity(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.density;
    }

    /**
     * 获取每英寸的点数(打印分辨率,区别PPI)
     *
     * @return
     */
    public float getDensityDpi(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.densityDpi;
    }
    
    /**
     * 获取通知栏的高度
     *
     * @return
     */
    public int getStatusBarHeight(Context context) {
        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0, statusBarHeight = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            statusBarHeight = context.getResources().getDimensionPixelSize(x);
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
    public Bitmap snapShotWithStatusBar(Activity context) {
        View view = ((Activity) context).getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);  
        view.buildDrawingCache();
        Bitmap bmp = view.getDrawingCache();
        int width = (int) getScreenWidth(context);
        int height = (int) getScreenHeight(context);
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
    public Bitmap snapShotWithoutStatusBar(Activity context) {
        View view = ((Activity) context).getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bmp = view.getDrawingCache();
        Rect frame = new Rect();
        ((Activity) context).getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        int statusBarHeight = frame.top;
  
        int width = (int) getScreenWidth(context);
        int height = (int) getScreenHeight(context);
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
    public double getScreenSize(Context context) {
//        double x = Math.pow(getScreenWidth(), 2);
//        double y = Math.pow(getScreenHeight(), 2);
//
//        DisplayMetrics displayMetrics = new DisplayMetrics();
//        ((Activity) mContext).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
//        return Math.sqrt(x + y) / displayMetrics.densityDpi;

        Point point = new Point();
        ((Activity) context).getWindowManager().getDefaultDisplay().getRealSize(point);
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        double x = Math.pow(point.x/ dm.xdpi, 2);
        double y = Math.pow(point.y / dm.ydpi, 2);
        double screenInches = Math.sqrt(x + y);
        return screenInches;
    }

    /**
     * 获取屏幕高度
     *
     * @param activity
     * @return
     */
    public static int getScreenHeight1(Activity activity) {
        return activity.getWindowManager().getDefaultDisplay().getHeight();
    }

    /**
     * 获取屏幕高度
     *
     * @param activity
     * @return
     */
    public static int getScreenHeight2(Activity activity) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    /**
     * 获取屏幕高度
     *
     * @param activity
     * @return
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    public static int getScreenHeight3(Activity activity) {
        Point point = new Point();
        activity.getWindowManager().getDefaultDisplay().getSize(point);
        return point.y;
    }


    /**
     * 获取屏幕高度
     *
     * @param activity
     * @return
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static int getScreenHeight4(Activity activity) {
        Point realSize = new Point();
        activity.getWindowManager().getDefaultDisplay().getRealSize(realSize);
        return realSize.y;
    }

    /**
     * 获取屏幕高度
     *
     * @param activity
     * @return
     */
    public static int getScreenHeight5(Activity activity) {
        Point realSize = new Point();
        Display display = activity.getWindowManager().getDefaultDisplay();
        try {
            Display.class.getMethod("getRealSize", Point.class).invoke(display, realSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return realSize.y;
    }

    /**
     * 获取屏幕高度
     *
     * @param activity
     * @return
     */
    public static int getScreenHeight6(Activity activity) {
        int heightPixels = 0;
        Display display = activity.getWindowManager().getDefaultDisplay();
        try {
            heightPixels = (Integer) Display.class.getMethod("getRawHeight").invoke(display);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return heightPixels;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public int getRealScreenHeight(Activity activity) {
        int heightPixels = 0;
        Display display = activity.getWindowManager().getDefaultDisplay();
        final int VERSION = Build.VERSION.SDK_INT;

        if(VERSION < 13) {
            display.getHeight();
        }else if (VERSION == 13) {
            try {
                heightPixels = (Integer) Display.class.getMethod("getRawHeight").invoke(display);
            } catch (Exception e) {
            }
        } else if (VERSION >= 14 && VERSION < 17) {
            Point realSize = new Point();
            try {
                Display.class.getMethod("getRealSize", Point.class).invoke(display, realSize);
                heightPixels = realSize.y;
            } catch (Exception e) {
            }
        } else {
            Point realSize = new Point();
            display.getRealSize(realSize);
            heightPixels = realSize.y;
        }
        return heightPixels;
    }


    public int getRealScreenHeight2(Activity activity) {
        int heightPixels = 0;
        Display display = activity.getWindowManager().getDefaultDisplay();
        final int VERSION = Build.VERSION.SDK_INT;

        if(VERSION < 13) {
            display.getHeight();
        }else if (VERSION == 13) {
            try {
                heightPixels = (Integer) Display.class.getMethod("getRawHeight").invoke(display);
            } catch (Exception e) {
            }
        } else {
            Point realSize = new Point();
            try {
                Display.class.getMethod("getRealSize", Point.class).invoke(display, realSize);
                heightPixels = realSize.y;
            } catch (Exception e) {
            }
        }
        return heightPixels;
    }

}

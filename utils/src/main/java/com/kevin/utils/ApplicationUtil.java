package com.kevin.utils;

import java.io.File;
import java.lang.reflect.Method;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Handler;
import android.widget.Toast;

/**
 * 版权所有：XXX有限公司</br>
 * 
 * ApplicationUtil </br>
 * 
 * @author zhou.wenkai ,Created on 2014-12-2 11:29:38</br>
 * @Description Major Function：应用程序工具类 </br>
 * 
 * 注:如果您修改了本类请填写以下内容作为记录，如非本人操作劳烦通知，谢谢！！！</br>
 * @author mender，Modified Date Modify Content:
 */
public class ApplicationUtil {

	/**
	 * 获取应用程序名称
	 */
	public static String getAppName(Context context) {
		try {
			PackageManager packageManager = context.getPackageManager();
			PackageInfo packageInfo = packageManager.getPackageInfo(
					context.getPackageName(), 0);
			int labelRes = packageInfo.applicationInfo.labelRes;
			return context.getResources().getString(labelRes);
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 *  获取当前应用的版本名称
	 * @param context
	 * @return
	 */
	public static String getVersionName(Context context) {
		PackageManager pm = context.getPackageManager();// 获取包信息管理器
		try {	// 代表清单文件信息
			PackageInfo packageInfo = pm.getPackageInfo(context.getPackageName(), 0);
			return packageInfo.versionName;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
				// 肯定不会发生
			return null;
		}
	}
	
	/**
	 *  获取当前应用的版本号
	 * @param context
	 * @return
	 */
	public static int getVersionCode(Context context) {
		PackageManager pm = context.getPackageManager();// 获取包信息管理器
		try {	// 代表清单文件信息
			PackageInfo packageInfo = pm.getPackageInfo(context.getPackageName(), 0);
			return packageInfo.versionCode;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
				// 肯定不会发生
			return 1;
		}
	}
	
	/**
	 * 安装一个apk文件
	 * @param context
	 * @param file
	 */
	public static void installApk(Context context, File file) {
		Intent intent = new Intent();
		intent.setAction("android.intent.action.VIEW");
		intent.addCategory("android.intent.category.DEFAULT");
		intent.setDataAndType(Uri.fromFile(file),
				"application/vnd.android.package-archive");
		context.startActivity(intent);
	}
	
	/**
	 * @description 打开商店进行评分
	 * 
	 * @param context 上下文
	 * @date 2015-6-8 17:17:40
	 */
	public static void openStore(Context context) {
		try {
			Uri uri = Uri.parse("market://details?id=" + context.getPackageName());
			Intent intent = new Intent(Intent.ACTION_VIEW, uri);
			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			context.startActivity(intent);
		} catch (ActivityNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @description 打开商店进行评分
	 *
	 * @param context 上下文
	 * @param noStoreWarning 未安装市场时的提醒
	 */
	public static void openStore(Context context, String noStoreWarning) {
		try {
			Uri uri = Uri.parse("market://details?id=" + context.getPackageName());
			Intent intent = new Intent(Intent.ACTION_VIEW, uri);
			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			context.startActivity(intent);
		} catch (ActivityNotFoundException e) {
			e.printStackTrace();
			Toast.makeText(context, noStoreWarning, Toast.LENGTH_SHORT).show();
		}
	}
	
	/**
	 * @description 打开通知栏
	 * 
	 * @param context
	 * @date 2015-5-22 23:43:50
	 */
	public static void expandNotify(Context context) {
		
    	int currentApiVersion = android.os.Build.VERSION.SDK_INT;
		try {
			Object service = context.getSystemService("statusbar");
			Class<?> statusbarManager = Class
					.forName("android.app.StatusBarManager");
			Method expand = null;
			if (service != null) {
				if (currentApiVersion <= 16) {
					expand = statusbarManager.getMethod("expand");
					expand.setAccessible(true);
					expand.invoke(service);
				} else {
					expand = statusbarManager
							.getMethod("expandNotificationsPanel");
				}
				expand.setAccessible(true);
				expand.invoke(service);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}

package com.kevin.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.Properties;
import java.util.UUID;

import android.app.ActivityManager;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

/**
 * 版权所有：----有限公司</br>
 * 
 * AndroidOSInfo </br>
 * 
 * <p>
 * @author zhou.wenkai ,Created on 2014-11-12 00:43:28</br>
 * Major Function：Android系统信息工具类 </br>
 * </p>
 * 注:如果您修改了本类请填写以下内容作为记录，如非本人操作劳烦通知，谢谢！！！</br>
 * @author mender，Modified Date Modify Content:
 */
public class AndroidOSInfo {
	private static final String KEY_MIUI_VERSION_CODE = "ro.miui.ui.version.code";
	private static final String KEY_MIUI_VERSION_NAME = "ro.miui.ui.version.name";
	private static final String KEY_MIUI_INTERNAL_STORAGE = "ro.miui.internal.storage";

	/**
	 * 检测 MIUI
	 * 
	 * @return
	 */
	public static boolean isMIUI() {
		try {
			final BuildProperties prop = BuildProperties.newInstance();
			return prop.getProperty(KEY_MIUI_VERSION_CODE, null) != null
					|| prop.getProperty(KEY_MIUI_VERSION_NAME, null) != null
					|| prop.getProperty(KEY_MIUI_INTERNAL_STORAGE, null) != null;
		} catch (final IOException e) {
			return false;
		}
	}

	/**
	 * 检测flyme
	 * 
	 * @return
	 */
	public static boolean isFlyme() {
		try {
			// Invoke Build.hasSmartBar()
			final Method method = Build.class.getMethod("hasSmartBar");
			return method != null;
		} catch (final Exception e) {
			return false;
		}
	}
	
	/**
	 * 获取手机品牌
	 *
	 * @return 手机品牌
	 */
	public static final String getDeviceName() {
		return Build.DEVICE;
	}
	
	/**
	 * 当前系统平台
	 *
	 * @return "Android"
	 */
	public static final String getPlatform() {
		return "Android";
	}
	
	/**
	 * 获取手机操作系统版本号
	 *
	 * @return 手机操作系统版本号
	 */
	public static final String getVersion() {
		return Build.VERSION.RELEASE;
	}
	
	/**
	 * 获取手机型号
	 *
	 * @return 手机型号
	 */
	public static final String getModel() {
		return Build.MODEL;
	}

	/**
	 * 手机机主名称
	 *
	 * @return 机主名称
	 */
	public static final String getName() {
		String user = Build.USER;
		if (user == null) {
			user = "unknown";
		}
		return user;
	}
	
	/**
	 * 获取设备IMEI
	 *
	 * @return 设备IMEI
	 */
	public static String getIMEI(Context context) {
		final TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		String imei = tm.getDeviceId();
		if (!TextUtils.isEmpty(imei)) {
			return imei;
		} else {
			// 获取mac地址
			WifiManager wifiMan = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
			if (wifiMan != null) {
				WifiInfo wifiInf = wifiMan.getConnectionInfo();
				if (wifiInf != null && wifiInf.getMacAddress() != null) {// 48位，如FA:34:7C:6D:E4:D7
					imei = wifiInf.getMacAddress().replaceAll(":", "");
					return imei;
				}
			}
		}
		return "";
	}
	
	/**
	 * 获取设备UUID，由设备信息产生
	 *
	 * @return 设备UUID
	 */
	public static final String getUUID(Context context) {
		final TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		final String tmDevice, tmSerial, androidId;
		tmDevice = "" + tm.getDeviceId();
		tmSerial = "" + tm.getSimSerialNumber();
		androidId = "" + android.provider.Settings.Secure.getString(context.getContentResolver(), 
				android.provider.Settings.Secure.ANDROID_ID);
		UUID deviceUuid = new UUID(androidId.hashCode(), 
				((long) tmDevice.hashCode() << 32) | tmSerial.hashCode());
		return deviceUuid.toString();
	}
	
	/**
	 * 判断手机是否Root
	 *
	 * @return
	 */
	public static boolean hasRoot() {
		try {  
	        return (Runtime.getRuntime().exec("su").getOutputStream() != null);
	    } catch (IOException e) {  
	        e.printStackTrace();
	    }
		return false;
	}

	/**
	 * 获取手机的总内存信息
	 *
	 * @return 单位byte
	 */
	public static long getTotalRam(Context context) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
			ActivityManager am = (ActivityManager) context
					.getSystemService(Context.ACTIVITY_SERVICE);
			ActivityManager.MemoryInfo outInfo = new ActivityManager.MemoryInfo();
			am.getMemoryInfo(outInfo);// 将获取的信息放入outInfo
			return outInfo.totalMem;  // byte为单位的long类型的总内存大小
		} else {
			try {
				File file = new File("proc/meminfo");
				FileInputStream fis;
				fis = new FileInputStream(file);
				BufferedReader br = new BufferedReader(new InputStreamReader(fis));
				String line = br.readLine();
				//MemTotal:         990208 kB
				StringBuffer sb = new StringBuffer();
				for (char c : line.toCharArray()) {
					if(c>='0'&&c<='9') {
						sb.append(c);
					}
				}
				return Integer.parseInt(sb.toString())*1024l;
			} catch (Exception e) {
				e.printStackTrace();
				return 0;
			}
		}
	}

	/**
	 * 获取手机的可用内存信息
	 *
	 * @param context
	 * @return 单位byte
	 */
	public static long getAvailRam(Context context) {
		ActivityManager am = (ActivityManager) context
				.getSystemService(Context.ACTIVITY_SERVICE);
		ActivityManager.MemoryInfo outInfo = new ActivityManager.MemoryInfo();
		am.getMemoryInfo(outInfo);	// 将获取的信息放入outInfo
		return outInfo.availMem; 	// byte为单位的long类型的可用内存大小
	}

	private static class BuildProperties {

		private final Properties properties;

		private BuildProperties() throws IOException {
			properties = new Properties();
			properties.load(new FileInputStream(new File(Environment
					.getRootDirectory(), "build.prop")));
		}

		public String getProperty(final String name, final String defaultValue) {
			return properties.getProperty(name, defaultValue);
		}

		public static BuildProperties newInstance() throws IOException {
			return new BuildProperties();
		}

	}
}

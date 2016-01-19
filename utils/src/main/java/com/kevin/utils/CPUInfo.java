package com.kevin.utils;

import android.os.Build;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

/**
 * 版权所有：----有限公司</br>
 * 
 * CPUInfo </br>
 * 
 * <p>
 * @author zhou.wenkai ,Created on 2016-1-19 14:26:06</br>
 * Major Function：Android CPU信息获取帮助类 </br>
 * </p>
 * 注:如果您修改了本类请填写以下内容作为记录，如非本人操作劳烦通知，谢谢！！！</br>
 * @author mender，Modified Date Modify Content:
 */
public class CPUInfo {

	/**
	 * 获取CPU名称
	 *
	 * @return cpuName or null
	 */
	public static String getCpuName() {
		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader("/proc/cpuinfo");
			br = new BufferedReader(fr);
			String text = br.readLine();
			String[] array = text.split(":\\s+", 2);
			for (int i = 0; i < array.length; i++) {
			}
			return array[1];
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			close(fr, br);
		}
		return null;
	}

	/**
	 * 实时获取CPU当前频率（单位KHZ）
	 *
	 * @return
	 */
	public static int getCurCpuFreq() {
		int result = 0;
		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader("/sys/devices/system/cpu/cpu0/cpufreq/scaling_cur_freq");
			br = new BufferedReader(fr);
			String text = br.readLine();
			result = Integer.parseInt(text.trim());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			close(fr, br);
		}
		return result;
	}

	/**
	 * 获取指定CPU最小频率（单位KHZ）
	 *
	 * @return 最小频率（单位KHZ）
	 */
	public static String getMinCpuFreq() {
		String result = "";
		ProcessBuilder cmd;
		try {
			String[] args = {"/system/bin/cat",
					"/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_min_freq"};
			cmd = new ProcessBuilder(args);
			Process process = cmd.start();
			InputStream in = process.getInputStream();
			byte[] re = new byte[24];
			while (in.read(re) != -1) {
				result = result + new String(re);
			}
			in.close();
		} catch (IOException ex) {
			ex.printStackTrace();
			result = "N/A";
		}
		return result.trim();
	}

	/**
	 * 实时CPU最大频率（单位KHZ）
	 *
	 * @return
	 */
	public static int getMaxCpuFreq() {
		int result = 0;
		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader("/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq");
			br = new BufferedReader(fr);
			String text = br.readLine();
			result = Integer.parseInt(text.trim());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			close(fr, br);
		}

		return result;
	}

	/**
	 * 获取CPU核数
	 *
	 * @return CPU核数 or 0 when exception
	 */
	public static int getCPUCores() {
		if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.GINGERBREAD_MR1) {
			// Gingerbread doesn't support giving a single application access to both cores, but a
			// handful of devices (Atrix 4G and Droid X2 for example) were released with a dual-core
			// chipset and Gingerbread; that can let an app in the background run without impacting
			// the foreground application. But for our purposes, it makes them single core.
			return 1;
		}
		int cores = 0;
		try {
			cores = new File("/sys/devices/system/cpu/").listFiles(CPU_FILTER).length;
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		return cores;
	}

	private static final FileFilter CPU_FILTER = new FileFilter() {
		@Override
		public boolean accept(File pathname) {
			String path = pathname.getName();
			//regex is slow, so checking char by char.
			if (path.startsWith("cpu")) {
				for (int i = 3; i < path.length(); i++) {
					if (path.charAt(i) < '0' || path.charAt(i) > '9') {
						return false;
					}
				}
				return true;
			}
			return false;
		}
	};

	/**
	 * 释放资源
	 *
	 * @param fr
	 * @param br
	 */
	private static void close(FileReader fr, BufferedReader br) {
		if (fr != null) {
			try {
				fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (br != null) {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

package com.kevin.utils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.content.Context;
/**
 * 版权所有：----有限公司</br>
 * 
 * LocalFileUtil </br>
 * 
 * <p>
 * @author zhou.wenkai ,Created on 2015-5-9 13:11:48</br>
 * @Description Major Function：读取本地包内文件</br>
 * </p>
 * 注:如果您修改了本类请填写以下内容作为记录，如非本人操作劳烦通知，谢谢！！！</br>
 * @author mender，Modified Date Modify Content:
 */
public class LocalFileUtil {
	
	/**
	 * 获取Asset下文本内容
     *
	 * @param context 上下文
	 * @param str 文件名称
	 * @return
	 */
    public final static String getStringFormAsset(Context context, String str) {
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(context.getAssets().open(str)), 8 * 1024);
            String line;
            StringBuilder buffer = new StringBuilder();
            while ((line = in.readLine()) != null)
                buffer.append(line).append('\n');
            return buffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                	e.printStackTrace();
                }
            }
        }
    }
    
    /**
     * 获取Raw下文本内容
     * @param context
     * @param rawId
     * @return
     */
    public final static String getStringFormRaw(Context context, int rawId) {
    	ByteArrayOutputStream baos = null;
	    InputStream in = context.getResources().openRawResource(rawId);
		try {
			baos = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = in.read(buffer)) != -1) {
				baos.write(buffer, 0, len);
			}
			baos.close();
			return baos.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		} finally {
            if (baos != null) {
                try {
                	baos.close();
                } catch (IOException e) {
                	e.printStackTrace();
                }
            }
        }
    }
    
}

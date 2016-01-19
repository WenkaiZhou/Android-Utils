package com.kevin.utils.samples.view;

import android.content.Context;
import android.graphics.Rect;
import android.text.TextUtils.TruncateAt;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * 版权所有：XXX有限公司</br>
 * 
 * ScrollTextView </br>
 * 
 * @author zhou.wenkai ,Created on 2014-12-22 21:56:35</br> 
 * 		   Major Function：会自动滚动的TextView</br>
 * 
 * 思路：获取焦点，并设置滚动
 * 
 *         注:如果您修改了本类请填写以下内容作为记录，如非本人操作劳烦通知，谢谢！！！</br>
 * @author mender，Modified Date Modify Content:
 */
public class ScrollTextView extends TextView {
	public ScrollTextView(Context context) {
		super(context);
		init();
	}

	public ScrollTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public ScrollTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}
	
	private void init() {
		setSingleLine();
		setEllipsize(TruncateAt.MARQUEE);	// 对应android:ellipsize="marquee"
		setMarqueeRepeatLimit(-1);			// marquee_forever
	}
	
	@Override
	protected void onFocusChanged(boolean focused, int direction,
			Rect previouslyFocusedRect) {
		if (focused)
			super.onFocusChanged(focused, direction, previouslyFocusedRect);
	}

	@Override
	public void onWindowFocusChanged(boolean focused) {
		if (focused)
			super.onWindowFocusChanged(focused);
	}

	@Override
	public boolean isFocused() {
		return true;
	}
}

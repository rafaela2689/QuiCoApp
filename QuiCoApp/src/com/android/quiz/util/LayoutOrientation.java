package com.android.quiz.util;

import android.content.Context;
import android.content.res.Configuration;

public class LayoutOrientation {

	
	public static boolean isSmartPhone(Context context) {
		final int screenLayout = context.getResources().getConfiguration().screenLayout;
		return ((screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) < Configuration.SCREENLAYOUT_SIZE_LARGE);
	
	}
}

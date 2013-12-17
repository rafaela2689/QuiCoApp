/*
 * Copyright (c) 2012 Samsung Electronics Co., Ltd.
 * All rights reserved.
 *
 * This software is a confidential and proprietary information of Samsung
 * Electronics, Inc. ("Confidential Information").  You shall not disclose such
 * Confidential Information and shall use it only in accordance with the terms
 * of the license agreement you entered into with Samsung Electronics.
 */
package com.android.quiz.util;

import android.content.Context;
import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableString;

/**
 * Class that provides the application's default font
 * 
 * */
public final class TypeFaceUtils {

	/**
	 * Private constructor to avoid external instantiation.
	 */
	private TypeFaceUtils() {
	}

	/**
	 * @param context  The {@link Context}.
	 * @return the default font.
	 */
	public static Typeface getTypeFace(Context context, TypeFont typeface) {
		final Typeface fonte = Typeface.createFromAsset(context.getResources()
				.getAssets(), "fonts/" + typeface.fontName() + ".otf");
		return fonte;
	}

	/**
	 * Get font default for ActionBar title.
	 * @param context
	 * @param title
	 * @return
	 */
	public static SpannableString getTypeFaceDefault(Context context, String title, TypeFont typeface) {
		final SpannableString value = new SpannableString(title);
		value.setSpan(new TypefaceApplication(context, typeface.fontName()), 0, value.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		return value;
	}
}

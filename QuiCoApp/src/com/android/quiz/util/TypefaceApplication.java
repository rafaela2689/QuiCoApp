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
import android.support.v4.util.LruCache;
import android.text.Spannable;
import android.text.TextPaint;
import android.text.style.MetricAffectingSpan;

/**
 * class that provides the application's default font for titles ActionBar
 * */

public class TypefaceApplication extends MetricAffectingSpan {

	/** An <code>LruCache</code> for previously loaded TypeFaces. */
	private static LruCache<String, Typeface> sTypefaceCache = new LruCache<String, Typeface>(12);

	/**
	 * Instance of {@link Typeface}.
	 */
	private Typeface mTypeface;

	/**
	 * Load the {@link Typeface} and apply to a {@link Spannable}.
	 */
	public TypefaceApplication(Context context, String typefaceName) {
		mTypeface = sTypefaceCache.get(typefaceName);

		if (mTypeface == null) {
			mTypeface = Typeface.createFromAsset(context
					.getApplicationContext().getAssets(), String.format("fonts/%s.otf", typefaceName));
			sTypefaceCache.put(typefaceName, mTypeface);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.text.style.MetricAffectingSpan#updateMeasureState(android.text
	 * .TextPaint)
	 */
	@Override
	public void updateMeasureState(TextPaint p) {
		p.setTypeface(mTypeface);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.text.style.CharacterStyle#updateDrawState(android.text.TextPaint)
	 */
	@Override
	public void updateDrawState(TextPaint tp) {
		tp.setTypeface(mTypeface);
	}
}

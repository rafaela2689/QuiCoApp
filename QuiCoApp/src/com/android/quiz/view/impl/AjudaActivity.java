package com.android.quiz.view.impl;

import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;

import com.android.quiz.R;

@ContentView(R.layout.ajuda)
public class AjudaActivity extends RoboActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		if (isSmartPhone(getApplicationContext()))
			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		else
			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

	}

	public static boolean isSmartPhone(Context context) {
		final int screenLayout = context.getResources().getConfiguration().screenLayout;
		return ((screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) < Configuration.SCREENLAYOUT_SIZE_LARGE);
	
	}


}

package com.android.quiz.view.impl;

import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;

import com.android.quiz.R;

@ContentView(R.layout.splash)
public class SplashActivity extends RoboActivity implements Runnable {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Handler h = new Handler();
		h.postDelayed(this, 3000);// aqui é definido o delay do handler em
									// milisegundos
		
		if (isSmartPhone(getApplicationContext()))
			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		else
			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

	}

	public static boolean isSmartPhone(Context context) {
		final int screenLayout = context.getResources().getConfiguration().screenLayout;
		return ((screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) < Configuration.SCREENLAYOUT_SIZE_LARGE);
	
	}

	public void run() {
		startActivity(new Intent(this, IniciarActivity.class));// aqui é
																// iniciada
																// nossa 2
																// activity
		finish();// aqui é chamado o método finish pra finalizar a activity
					// atual no caso SplashScreen
	}
}

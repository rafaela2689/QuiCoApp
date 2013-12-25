package com.android.quiz.view.impl;

import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;

import com.android.quiz.R;
import com.android.quiz.util.LayoutOrientation;

@ContentView(R.layout.splash)
public class SplashActivity extends RoboActivity implements Runnable {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Handler h = new Handler();
		h.postDelayed(this, 3000);// aqui é definido o delay do handler em
									// milisegundos
		
		if (LayoutOrientation.isSmartPhone(getApplicationContext())){
			
		}else
			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

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

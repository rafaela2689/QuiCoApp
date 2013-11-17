package com.android.quiz;

import android.app.Application;

import com.android.quiz.db.DBHelper;

public class QuizCopaApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
		DBHelper.createInstance(this);
	}
	
}

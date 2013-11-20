package com.android.quiz.view;

import com.android.quiz.R;
import com.android.quiz.R.layout;
import com.android.quiz.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class SobreActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sobre);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sobre, menu);
		return true;
	}

}

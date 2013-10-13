package com.android.quizcopa;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class Ajuda extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tela_ajuda);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ajuda, menu);
		return true;
	}

}

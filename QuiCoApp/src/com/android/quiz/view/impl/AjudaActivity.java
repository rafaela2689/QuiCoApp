package com.android.quiz.view.impl;

import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.TextView;

import com.android.quiz.R;
import com.android.quiz.util.LayoutOrientation;
import com.android.quiz.util.TypeFaceUtils;
import com.android.quiz.util.TypeFont;

@ContentView(R.layout.ajuda)
public class AjudaActivity extends RoboActivity {
	
	@InjectView(R.id.txtAjuda1)
	private TextView txAjuda1;
	
	@InjectView(R.id.txtAjuda2)
	TextView txAjuda2;
	
	@InjectView(R.id.txtAjuda3)
	TextView txAjuda3;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		txAjuda1.setText(TypeFaceUtils.getTypeFaceDefault(this, txAjuda1.getText().toString(),
				TypeFont.MUSEO_SLAB));
		
		txAjuda2.setText(TypeFaceUtils.getTypeFaceDefault(this, txAjuda2.getText().toString(),
				TypeFont.MUSEO_SLAB));
		
		txAjuda3.setText(TypeFaceUtils.getTypeFaceDefault(this, txAjuda3.getText().toString(),
				TypeFont.MUSEO_SLAB));
		
		
		if (LayoutOrientation.isSmartPhone(getApplicationContext())){
			
		}else
			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

	
	}

	
}

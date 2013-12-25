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

@ContentView(R.layout.sobre)
public class SobreActivity extends RoboActivity {

	@InjectView(R.id.txtVersao)
	private TextView txVersao;
	
	@InjectView(R.id.txtSobreTeam)
	private TextView txTeam;
	
	@InjectView(R.id.txtDanilo)
	private TextView txDanilo;
	
	@InjectView(R.id.txtAdilio)
	private TextView txAdilio;
	
	@InjectView(R.id.txtRafaela)
	private TextView txRafaela;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		txVersao.setText(TypeFaceUtils.getTypeFaceDefault(this, txVersao.getText().toString(),
				TypeFont.MUSEO_SLAB));
		
		txTeam.setText(TypeFaceUtils.getTypeFaceDefault(this, txTeam.getText().toString(),
				TypeFont.MUSEO_SLAB));
		
		txAdilio.setText(TypeFaceUtils.getTypeFaceDefault(this, txAdilio.getText().toString(),
				TypeFont.MUSEO_SLAB));
		
		txDanilo.setText(TypeFaceUtils.getTypeFaceDefault(this, txDanilo.getText().toString(),
				TypeFont.MUSEO_SLAB));
		
		txRafaela.setText(TypeFaceUtils.getTypeFaceDefault(this, txRafaela.getText().toString(),
				TypeFont.MUSEO_SLAB));
		
		if (LayoutOrientation.isSmartPhone(getApplicationContext())){
	
		}else
			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

	}

}

package com.android.quiz.view.impl;

import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.TextView;

import com.android.quiz.R;
import com.android.quiz.util.LayoutOrientation;
import com.android.quiz.util.TypeFaceUtils;
import com.android.quiz.util.TypeFont;

//@ContentView(R.layout.sobre)
public class SobreActivity extends RoboActivity {

	/*@InjectView(R.id.txtVersao)
	private TextView txVersao;
	
	@InjectView(R.id.txtSobreTeam)
	private TextView txTeam;
	
	@InjectView(R.id.txtDanilo)
	private TextView txDanilo;
	
	@InjectView(R.id.txtAdilio)
	private TextView txAdilio;
	
	@InjectView(R.id.txtRafaela)
	private TextView txRafaela;
	
	public static final String VERSAO = "QuizCopa Versão 1.0";
	
	public static final String SOBRE_TEAM = "Desenvolvido por:";
	
	public static final String SOBRE_ADILIO = "Adílio Figueiredo";
	
	public static final String SOBRE_DANILO = "Danilo Cezar Araújo";
	
	public static final String SOBRE_RAFAELA = "Rafaela Cavalcante";*/
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sobre);
		
		/*txVersao.setText(TypeFaceUtils.getTypeFaceDefault(this, VERSAO,
				TypeFont.MUSEO_SLAB));
		
		txTeam.setText(TypeFaceUtils.getTypeFaceDefault(this, SOBRE_TEAM,
				TypeFont.MUSEO_SLAB));
		
		txAdilio.setText(TypeFaceUtils.getTypeFaceDefault(this, SOBRE_ADILIO,
				TypeFont.MUSEO_SLAB));
		
		txDanilo.setText(TypeFaceUtils.getTypeFaceDefault(this, SOBRE_DANILO,
				TypeFont.MUSEO_SLAB));
		
		txRafaela.setText(TypeFaceUtils.getTypeFaceDefault(this, SOBRE_RAFAELA,
				TypeFont.MUSEO_SLAB));*/
		
		if (LayoutOrientation.isSmartPhone(getApplicationContext()))
			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		else
			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

	}

}

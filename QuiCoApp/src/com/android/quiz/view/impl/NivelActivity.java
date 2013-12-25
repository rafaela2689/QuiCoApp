package com.android.quiz.view.impl;

import java.util.ArrayList;
import java.util.List;

import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

import com.android.quiz.R;
import com.android.quiz.enumerado.NivelEnum;
import com.android.quiz.presenter.INivelPresenter;
import com.android.quiz.util.Constantes;
import com.android.quiz.util.LayoutOrientation;
import com.android.quiz.view.INivelView;
import com.google.inject.Inject;

@ContentView(R.layout.nivel)
public class NivelActivity extends RoboActivity implements OnClickListener, INivelView {
	
	@InjectView(R.id.imgBtnNivel1)
	private ImageButton btnNivel1; 
	
	@InjectView(R.id.imgBtnNivel2)
	private ImageButton btnNivel2;
	
	@InjectView(R.id.imgBtnNivel3)
	private ImageButton btnNivel3;
	
	@InjectView(R.id.imgBtnNivel4)
	private ImageButton btnNivel4;
	
	@InjectView(R.id.imgBtnNivel5)
	private ImageButton btnNivel5;
	
	@InjectView(R.id.imgBtnNivel6)
	private ImageButton btnNivel6;
	
	private int idCategoria;

	List<ImageButton> botoes = new ArrayList<ImageButton>();
	
	@Inject
	private INivelPresenter presenter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// recebe o parametros da Activity Categoria
		Intent nivel = getIntent();
		Bundle params = nivel.getExtras();
		if (params != null) {
			idCategoria = params.getInt(Constantes.CATEGORIA);
		}

		btnNivel1.setOnClickListener(this);
		btnNivel2.setOnClickListener(this);
		btnNivel3.setOnClickListener(this);
		btnNivel4.setOnClickListener(this);
		btnNivel5.setOnClickListener(this);
		btnNivel6.setOnClickListener(this);

		botoes.add(btnNivel1);
		botoes.add(btnNivel2);
		botoes.add(btnNivel3);
		botoes.add(btnNivel4);
		botoes.add(btnNivel5);
		botoes.add(btnNivel6);

		presenter.setNivelView(this);
		presenter.verificaStatusCategoriaNivel(idCategoria);
		
		if (LayoutOrientation.isSmartPhone(getApplicationContext())){
			 //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		}else
			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		
	}


	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.imgBtnNivel1:
			chamaTelaQuestao(NivelEnum.NIVEL1);
			break;
		case R.id.imgBtnNivel2:
			chamaTelaQuestao(NivelEnum.NIVEL2);
			finish();
			break;
		case R.id.imgBtnNivel3:
			chamaTelaQuestao(NivelEnum.NIVEL3);
			finish();
			break;
		case R.id.imgBtnNivel4:
			chamaTelaQuestao(NivelEnum.NIVEL4);
			finish();
			break;
		case R.id.imgBtnNivel5:
			chamaTelaQuestao(NivelEnum.NIVEL5);
			break;
		case R.id.imgBtnNivel6:
			chamaTelaQuestao(NivelEnum.NIVEL6);
			break;

		}

	}

	public void chamaTelaQuestao(NivelEnum niv) {

		Intent nivel = new Intent(this, QuestaoActivity.class);
		Bundle param = new Bundle();
		param.putInt(Constantes.CATEGORIA, idCategoria);
		param.putInt(Constantes.NIVEL, niv.getNivel());
		nivel.putExtras(param);
		startActivity(nivel);
		finish();

	}
	
	@Override
	public List<ImageButton> getBotoes() {
		return botoes;
	}

}

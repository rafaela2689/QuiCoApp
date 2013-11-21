package com.android.quiz.view.impl;

import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

import com.android.quiz.R;
import com.android.quiz.enumerado.CategoriaEnum;
import com.android.quiz.util.Constantes;

@ContentView(R.layout.categoria)
public class CategoriaActivity extends RoboActivity implements OnClickListener {

	@InjectView(R.id.imgbtnSelecao)
	private ImageButton btnSelecao;
	
	@InjectView(R.id.imgbtnJogador)
	private ImageButton btnJogadores;
	
	@InjectView(R.id.imgbtnCidadeSede)
	private ImageButton btnCidadeSede;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		btnSelecao.setOnClickListener(this);
		btnJogadores.setOnClickListener(this);
		btnCidadeSede.setOnClickListener(this);
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.imgbtnSelecao:
			chamarTelaNivel(CategoriaEnum.SELECOES);
			break;
		case R.id.imgbtnJogador:
			chamarTelaNivel(CategoriaEnum.JOGADORES);
			break;
		case R.id.imgbtnCidadeSede:
			chamarTelaNivel(CategoriaEnum.CURIOSIDADES);
			break;
		}
	}
	
	public void chamarTelaNivel(CategoriaEnum cat) {
		Intent catCidadeSede = new Intent(this, NivelActivity.class);
		Bundle params = new Bundle();
		params.putInt(Constantes.CATEGORIA, cat.getCategoria());
		catCidadeSede.putExtras(params);
		startActivity(catCidadeSede);
		finish();
	}

}

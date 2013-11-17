package com.android.quiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class CategoriaActivity extends Activity implements OnClickListener {
	
	public static final String CATEGORIA = "categoria";

	ImageButton btnSelecao, btnJogadores, btnCidadeSede;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.categoria);

		btnSelecao = (ImageButton) findViewById(R.id.imgbtnSelecao);
		btnJogadores = (ImageButton) findViewById(R.id.imgbtnJogador);
		btnCidadeSede = (ImageButton) findViewById(R.id.imgbtnCidadeSede);

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
		Bundle params3 = new Bundle();
		params3.putInt(CATEGORIA, cat.getCategoria());
		catCidadeSede.putExtras(params3);
		startActivity(catCidadeSede);
		finish();
	}

}

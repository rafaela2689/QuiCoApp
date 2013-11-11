package com.android.quiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.quiz.db.DBHelper;
import com.android.quiz.modelo.Categoria;

public class CategoriaActivity extends Activity implements OnClickListener {

	Categoria catAtual = new Categoria();

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
			try{
			catAtual.setIdCategoria(1);
			Intent catSelecao = new Intent(this, NivelActivity.class);
			Bundle params = new Bundle();
			params.putInt("categoria", catAtual.getIdCategoria());
			catSelecao.putExtras(params);
			startActivity(catSelecao);
			}catch(Exception e){
				String erro = e.getMessage();
				Toast.makeText(getApplicationContext(), erro, Toast.LENGTH_SHORT).show();
			}
			finish();
			break;
		case R.id.imgbtnJogador:
			catAtual.setIdCategoria(2);
			Intent catJogadores = new Intent(this, NivelActivity.class);
			Bundle params2 = new Bundle();
			params2.putInt("categoria", catAtual.getIdCategoria());
			catJogadores.putExtras(params2);
			startActivity(catJogadores);
			finish();
			break;
		case R.id.imgbtnCidadeSede:
			catAtual.setIdCategoria(3);
			Intent catCidadeSede = new Intent(this, NivelActivity.class);
			Bundle params3 = new Bundle();
			params3.putInt("categoria", catAtual.getIdCategoria());
			catCidadeSede.putExtras(params3);
			startActivity(catCidadeSede);
			finish();
			break;
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.categoria, menu);
		return true;
	}

}

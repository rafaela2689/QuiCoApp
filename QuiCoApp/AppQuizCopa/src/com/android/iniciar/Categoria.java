package com.android.iniciar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

import com.android.quizcopa.R;

public class Categoria extends Activity {
	
	ImageButton btnSelecao, btnJogadores, btnCidadeSede;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tela_categoria);
		
		btnSelecao = (ImageButton)findViewById(R.id.imgbtnSelecao);
		btnJogadores = (ImageButton)findViewById(R.id.imgbtnJogador);
		btnCidadeSede = (ImageButton)findViewById(R.id.imgbtnCidadeSede);
		
		btnSelecao.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				Intent catSelecao = new Intent(Categoria.this, Nivel.class);
				startActivity(catSelecao);
			}
		});
		
		btnJogadores.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				Intent catJogadores = new Intent(Categoria.this, Nivel.class);
				startActivity(catJogadores);
				
			}
		});
		
		btnCidadeSede.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				Intent catCidadeSede = new Intent(Categoria.this, Nivel.class);
				startActivity(catCidadeSede);
			}
		});
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.categoria, menu);
		return true;
	}

}

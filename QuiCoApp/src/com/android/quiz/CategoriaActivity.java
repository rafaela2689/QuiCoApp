package com.android.quiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

import com.android.quiz.db.DBHelper;
import com.android.quiz.modelo.Categoria;

public class CategoriaActivity extends Activity implements OnClickListener{
	
	
	
	Categoria catAtual = new Categoria();

	ImageButton btnSelecao, btnJogadores, btnCidadeSede;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.categoria);
		
		btnSelecao = (ImageButton)findViewById(R.id.imgbtnSelecao);
		btnJogadores = (ImageButton)findViewById(R.id.imgbtnJogador);
		btnCidadeSede = (ImageButton)findViewById(R.id.imgbtnCidadeSede);
		
		btnSelecao.setOnClickListener(this);
		btnJogadores.setOnClickListener(this);
		btnCidadeSede.setOnClickListener(this);
	}
	
		public void onClick(View v){
			switch(v.getId()){		
			case R.id.imgbtnSelecao:
				catAtual.setIdCategoria(1);
				/*DBHelper db = new DBHelper(this);
				try {
					catAtual.setNivelAtual(db.consultarCategoria(catAtual.getIdCategoria()));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				db.close();*/
				//System.out.println(catAtual.getNivelAtual());
				//catAtual.setNivelAtual();
				Intent catSelecao = new Intent(this, NivelActivity.class);
				Bundle params = new Bundle();
				params.putInt("categoria", catAtual.getIdCategoria());
				catSelecao.putExtras(params);
				startActivity(catSelecao);
				
				break;
			case R.id.imgbtnJogador:
				catAtual.setIdCategoria(2);
				Intent catJogadores = new Intent(CategoriaActivity.this, NivelActivity.class);
				startActivity(catJogadores);
				break;
			case R.id.imgbtnCidadeSede:
				catAtual.setIdCategoria(3);
				Intent catCidadeSede = new Intent(CategoriaActivity.this, NivelActivity.class);
				startActivity(catCidadeSede);
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

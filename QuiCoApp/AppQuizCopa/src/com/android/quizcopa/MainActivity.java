package com.android.quizcopa;

import com.android.iniciar.Categoria;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	
	Button btnAjuda, btnSobre, btnIniciar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		btnIniciar = (Button)findViewById(R.id.btnIniciar);
		btnAjuda = (Button)findViewById(R.id.btnAjuda);
		btnSobre = (Button)findViewById(R.id.btnSobre);
		
		//ao clicar no botao iniciar
		btnIniciar.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				carregaTelaIniciar();
			}
		});

		//ao clicar no botao ajuda
		btnAjuda.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				carregaTelaAjuda();
			}
		});
		
		//ao clicar no botao sobre
		btnSobre.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				carregaTelaSobre();
			}
		});
	}
	
	//chama a tela Iniciar
		public void carregaTelaIniciar(){
			Intent telaIniciar = new Intent (MainActivity.this, Categoria.class);
			startActivity(telaIniciar);
		}
	
	//chama a tela de Ajuda
	public void carregaTelaAjuda(){
		Intent telaAjuda = new Intent (MainActivity.this, Ajuda.class);
		startActivity(telaAjuda);
	}
	
	//chama a tela Sobre
	public void carregaTelaSobre(){
		Intent telaSobre = new Intent (MainActivity.this, Sobre.class);
		startActivity(telaSobre);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}

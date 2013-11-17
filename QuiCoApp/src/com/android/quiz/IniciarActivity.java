package com.android.quiz;

import com.android.quiz.db.DBHelper;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class IniciarActivity extends Activity {
	
	Button btnAjuda, btnSobre, btnIniciar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.iniciar);
		
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
			Intent telaIniciar = new Intent (IniciarActivity.this, CategoriaActivity.class);
			startActivity(telaIniciar);
		}
	
	//chama a tela de Ajuda
	public void carregaTelaAjuda(){
		Intent telaAjuda = new Intent (IniciarActivity.this, AjudaActivity.class);
		startActivity(telaAjuda);
	}
	
	//chama a tela Sobre
	public void carregaTelaSobre(){
		Intent telaSobre = new Intent (IniciarActivity.this, SobreActivity.class);
		startActivity(telaSobre);
	}
	
	@Override
	public void onBackPressed(){
		AlertDialog.Builder dialogo = new AlertDialog.Builder(this);
		dialogo.setTitle("Sair");
		dialogo.setMessage("Tem certeza que deseja sair?");
		dialogo.setCancelable(false);
		dialogo.setPositiveButton("Sim", new DialogInterface.OnClickListener() {   				
					public void onClick(DialogInterface dialog, int id){
						
						finish();
					}
			});
		dialogo.setNegativeButton("Não", new DialogInterface.OnClickListener(){ 
			public void onClick(DialogInterface dialog, int id) {
			dialog.cancel();
			}
		});
			dialogo.show();
	}

	@Override
	public void onDestroy(){
		DBHelper db = DBHelper.instance(getApplicationContext());
		db.close();
		super.onDestroy();
	}
	
	


}

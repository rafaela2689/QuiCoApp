package com.android.quiz.view.impl;

import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

import com.android.quiz.R;
import com.android.quiz.db.DBHelper;
import com.android.quiz.util.LayoutOrientation;

@ContentView(R.layout.iniciar)
public class IniciarActivity extends RoboActivity implements OnClickListener {
	
	@InjectView(R.id.btnIniciar)
	private ImageButton btnIniciar;
	
	@InjectView(R.id.btnAjuda)
	private ImageButton btnAjuda;
	
	@InjectView(R.id.btnSobre)
	private ImageButton btnSobre; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		btnIniciar.setOnClickListener(this);
		btnAjuda.setOnClickListener(this);
		btnSobre.setOnClickListener(this);
		
		if (LayoutOrientation.isSmartPhone(getApplicationContext()))
			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		else
			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);


	}


	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnIniciar:
			carregaTelaIniciar();
			break;
		case R.id.btnAjuda:
			carregaTelaAjuda();
			break;
		case R.id.btnSobre:
			carregaTelaSobre();
			break;
		}

	}

	// chama a tela Iniciar
	public void carregaTelaIniciar() {
		Intent telaIniciar = new Intent(IniciarActivity.this, CategoriaActivity.class);
		startActivity(telaIniciar);
	}

	// chama a tela de Ajuda
	public void carregaTelaAjuda() {
		Intent telaAjuda = new Intent(IniciarActivity.this, AjudaActivity.class);
		startActivity(telaAjuda);
	}

	// chama a tela Sobre
	public void carregaTelaSobre() {
		Intent telaSobre = new Intent(IniciarActivity.this, SobreActivity.class);
		startActivity(telaSobre);
	}

	@Override
	public void onBackPressed() {
		final Dialog dialog = new Dialog(this);
		
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

		dialog.setContentView(R.layout.dialog_sair);

		dialog.setCancelable(false);

		final Button btnSim = (Button) dialog
				.findViewById(R.id.btnSim);
		final Button btnNao = (Button) dialog
				.findViewById(R.id.btnNao);

		btnSim.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();

			}
		});

		btnNao.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				dialog.cancel();
				dialog.dismiss();
			}
		});

		dialog.show();

	}

	@Override
	public void onDestroy() {
		DBHelper.fechar();
		super.onDestroy();
	}

}

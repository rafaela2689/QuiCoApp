package com.android.quiz.view.impl;

import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.android.quiz.R;
import com.android.quiz.presenter.QuestaoPresenter;
import com.android.quiz.util.Constantes;
import com.android.quiz.view.IQuestaoView;

@ContentView(R.layout.questao)
public class QuestaoActivity extends RoboActivity implements IQuestaoView {

	private int nivelAtual;
	private int idCategoria;
	
	QuestaoPresenter presenter;
	
	@InjectView(R.id.txtQuestao)
	private TextView txQuestao;

	@InjectView(R.id.txtContador)
	private TextView txtContador;
	
	@InjectView(R.id.btnOpcao1)
	private Button btnOpcao1;

	@InjectView(R.id.btnOpcao2)
	private Button btnOpcao2; 
	
	@InjectView(R.id.btnOpcao3)
	private Button btnOpcao3;
	
	@InjectView(R.id.btnOpcao4)
	private Button btnOpcao4;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// recebe os parâmetros da Activity Nível
		Intent categoria = getIntent();
		Bundle params = categoria.getExtras();
		if (params != null) {
			idCategoria = params.getInt(Constantes.CATEGORIA);
			nivelAtual = params.getInt(Constantes.NIVEL);
		}

		presenter = new QuestaoPresenter(this, getApplicationContext(), idCategoria, nivelAtual);

		// consulta as questoes no banco de dados
		presenter.consultaQuestoesBD();

		// seta o primeiro registro no objeto da classe questao
		presenter.setQuestions();

		// método para evento click do botao
		btnOpcao1.setOnClickListener(presenter);
		btnOpcao2.setOnClickListener(presenter);
		btnOpcao3.setOnClickListener(presenter);
		btnOpcao4.setOnClickListener(presenter);

		if (isSmartPhone(getApplicationContext()))
			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		else
			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

	}

	public static boolean isSmartPhone(Context context) {
		final int screenLayout = context.getResources().getConfiguration().screenLayout;
		return ((screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) < Configuration.SCREENLAYOUT_SIZE_LARGE);
	}


	// método para mostrar a mensagem se ganhar
	@Override
	public void mostrarMsgGanhou() {

		final Dialog dialog = new Dialog(this);

		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(R.layout.resposta_certa);
		dialog.setCancelable(false);

		final Button btnProxNivel = (Button) dialog
				.findViewById(R.id.btnProximoNivel);
		final Button btnCategoria = (Button) dialog
				.findViewById(R.id.btnTelaCategoria);
		final Button btnNiveis = (Button) dialog.findViewById(R.id.btnNiveis);

		// clicar no botão categoria
		btnCategoria.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				chamarTelaCategoria();
				dialog.dismiss();

			}
		});

		// clicar no botão próximo nível
		btnProxNivel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				// verifica se o nível ainda é válido para passar para o próximo
				if (nivelAtual < 6) {
					
					presenter.setIndiceLista(0);
					presenter.consultaQuestoesBD();
					
					presenter.setQuestions();
					dialog.dismiss();

				} else {
					// senão chama a tela de categoria
					chamarTelaCategoria();
					dialog.dismiss();
				}

			}
		});

		// clicar no botão sair
		btnNiveis.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				chamarTelaNiveis();
				dialog.dismiss();

			}
		});

		dialog.show();
	}

	// método para mostrar mensagem se perder
	@Override
	public void mostrarMsgPerdeu() {

		final Dialog dialog = new Dialog(this);
		
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

		dialog.setContentView(R.layout.resposta_errada);
		dialog.setCancelable(false);

		final Button btnNivel = (Button) dialog.findViewById(R.id.btnSair);
		final Button btnCategoria = (Button) dialog
				.findViewById(R.id.btnTelaCategoria2);
		final Button btnNovoJogo = (Button) dialog
				.findViewById(R.id.btnJogarNovamente);

		// clicar no botão sair
		btnNivel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				chamarTelaNiveis();
				dialog.dismiss();

			}
		});

		// clicar no botão categoria
		btnCategoria.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				chamarTelaCategoria();
				dialog.dismiss();

			}
		});

		// clicar no botão jogar novamente
		btnNovoJogo.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				presenter.setIndiceLista(0);
				presenter.consultaQuestoesBD();
				presenter.setQuestions();
				dialog.dismiss();

			}
		});

		dialog.show();
	}

	//método chamado ao clicar no botão voltar
	@Override
	public void onBackPressed() {
		final Dialog dialog = new Dialog(this);

		dialog.setContentView(R.layout.dialog_questao);
		dialog.setTitle("Sair");
		dialog.setCancelable(false);

		final Button btnContinuar = (Button) dialog
				.findViewById(R.id.btnContinuar);
		final Button btnTerminar = (Button) dialog
				.findViewById(R.id.btnTerminar);

		btnContinuar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				dialog.cancel();
				dialog.dismiss();

			}
		});

		btnTerminar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				chamarTelaIniciar();
				dialog.dismiss();
			}
		});

		dialog.show();

	}
	
	public void chamarTelaNiveis(){
		Intent nivel = new Intent(QuestaoActivity.this,
				NivelActivity.class);
		Bundle params = new Bundle();
		params.putInt(Constantes.CATEGORIA, idCategoria);
		nivel.putExtras(params);
		startActivity(nivel);
		finish();
	}
	
	public void chamarTelaCategoria(){
		Intent cat = new Intent(QuestaoActivity.this,
				CategoriaActivity.class);
		startActivity(cat);
		finish();
	}
	
	public void chamarTelaIniciar(){
		Intent i = new Intent(QuestaoActivity.this,
				IniciarActivity.class);
		startActivity(i);
		finish();
	}
	
	
	@Override
	public void setQuestao(String questao) {
		this.txQuestao.setText(questao);
	}
	
	@Override
	public void setContador(String contador) {
		this.txtContador.setText(contador);
	}
	@Override
	public void setOpcao1(String opcao1) {
		this.btnOpcao1.setText(opcao1);
	}
	@Override
	public void setOpcao2(String opcao2) {
		this.btnOpcao2.setText(opcao2);
	}
	@Override
	public void setOpcao3(String opcao3) {
		this.btnOpcao3.setText(opcao3);
	}
	@Override
	public void setOpcao4(String opcao4) {
		this.btnOpcao4.setText(opcao4);
	}

	@Override
	public String getQuestao() {
		return txQuestao.getText().toString();
	}
	@Override
	public String getOpcao1() {
		return btnOpcao1.getText().toString();
	}
	@Override
	public String getOpcao2() {
		return btnOpcao2.getText().toString();
	}
	@Override
	public String getOpcao3() {
		return btnOpcao3.getText().toString();
	}
	@Override
	public String getOpcao4() {
		return btnOpcao4.getText().toString();
	}
}

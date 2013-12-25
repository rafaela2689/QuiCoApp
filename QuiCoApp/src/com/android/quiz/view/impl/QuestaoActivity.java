package com.android.quiz.view.impl;

import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.android.quiz.R;
import com.android.quiz.presenter.IQuestaoPresenter;
import com.android.quiz.util.Constantes;
import com.android.quiz.util.LayoutOrientation;
import com.android.quiz.util.TypeFaceUtils;
import com.android.quiz.util.TypeFont;
import com.android.quiz.view.IQuestaoView;
import com.google.inject.Inject;

@ContentView(R.layout.questao)
public class QuestaoActivity extends RoboActivity implements IQuestaoView {

	public static final String QUESTAO = "questao";
	public static final String OPCAO_1 = "opcao1";
	public static final String OPCAO_2 = "opcao2";
	public static final String OPCAO_3 = "opcao3";
	public static final String OPCAO_4 = "opcao4";
	public static final String RESPOSTA = "resposta";
	public static final String CONTADOR = "contador"; 
	public static final String INDICE_LISTA = "indiceLista";
	
	private int nivelAtual;
	private int idCategoria;
	
	@Inject
	IQuestaoPresenter presenter; 
	
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

		presenter.inicializar(this, getApplicationContext(), idCategoria, nivelAtual);
		// consulta as questoes no banco de dados
		presenter.consultaQuestoesBD();

		// seta o primeiro registro no objeto da classe questao
		presenter.loadQuestions();

		// método para evento click do botao
		btnOpcao1.setOnClickListener(presenter);
		btnOpcao2.setOnClickListener(presenter);
		btnOpcao3.setOnClickListener(presenter);
		btnOpcao4.setOnClickListener(presenter);

		if (LayoutOrientation.isSmartPhone(getApplicationContext())){
			
		}else
			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

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
					
					presenter.loadQuestions();
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
				presenter.loadQuestions();
				dialog.dismiss();

			}
		});

		dialog.show();
	}

	//método chamado ao clicar no botão voltar
	@Override
	public void onBackPressed() {
		final Dialog dialog = new Dialog(this);
		
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

		dialog.setContentView(R.layout.dialog_questao);

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
		this.txQuestao.setText(TypeFaceUtils.getTypeFaceDefault(this, questao,
				TypeFont.MUSEO_SLAB));
	}
	
	@Override
	public void setContador(String contador) {
		this.txtContador.setText(TypeFaceUtils.getTypeFaceDefault(this, contador,
				TypeFont.MUSEO_SLAB));
	}
	@Override
	public void setOpcao1(String opcao1) {
		this.btnOpcao1.setText(TypeFaceUtils.getTypeFaceDefault(this, opcao1,
				TypeFont.MUSEO_SLAB));
	}
	@Override
	public void setOpcao2(String opcao2) {
		this.btnOpcao2.setText(TypeFaceUtils.getTypeFaceDefault(this, opcao2,
				TypeFont.MUSEO_SLAB));
	}
	@Override
	public void setOpcao3(String opcao3) {
		this.btnOpcao3.setText(TypeFaceUtils.getTypeFaceDefault(this, opcao3,
				TypeFont.MUSEO_SLAB));
	}
	@Override
	public void setOpcao4(String opcao4) {
		this.btnOpcao4.setText(TypeFaceUtils.getTypeFaceDefault(this, opcao4,
				TypeFont.MUSEO_SLAB));
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
	
	@Override
	protected void onSaveInstanceState (Bundle outState) {
		
		super.onSaveInstanceState(outState);
		
		outState.putString(QUESTAO, getQuestao());
		outState.putString(OPCAO_1, getOpcao1());
		outState.putString(OPCAO_2, getOpcao2());
		outState.putString(OPCAO_3, getOpcao3());
		outState.putString(OPCAO_4, getOpcao4());
		outState.putString(RESPOSTA, presenter.getResposta());
		outState.putInt(CONTADOR, presenter.getContador());
		outState.putInt(INDICE_LISTA, presenter.getIndiceLista());
		
	}
	
	public void onRestoreInstanceState (Bundle outState) {
		
		setQuestao(outState.getString(QUESTAO));
		setOpcao1(outState.getString(OPCAO_1));
		setOpcao2(outState.getString(OPCAO_2));
		setOpcao3(outState.getString(OPCAO_3));
		setOpcao4(outState.getString(OPCAO_4));
		presenter.setResposta(outState.getString(RESPOSTA));
		presenter.setContador(outState.getInt(CONTADOR));
		presenter.setIndiceLista(outState.getInt(INDICE_LISTA));
		
		super.onRestoreInstanceState(outState);
	}
	
}

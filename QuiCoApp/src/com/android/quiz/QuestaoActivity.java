package com.android.quiz;

import java.util.List;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.android.quiz.dao.CategoriaNivelDao;
import com.android.quiz.dao.QuestaoDao;
import com.android.quiz.modelo.Categoria;
import com.android.quiz.modelo.Questao;


public class QuestaoActivity extends Activity implements OnClickListener {

	List<Questao> qLista;
	Categoria cat = new Categoria();
	int id_cat_niv=0;
	int nivel_atual=0;
	Questao qAtual;
	QuestaoDao qt_dao;
	CategoriaNivelDao cat_niv_dao;
	int qid = 0;
	TextView txQuestao;
	Button btnOpcao1, btnOpcao2, btnOpcao3, btnOpcao4;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.questao);
		Intent categoria = getIntent();
		Bundle params = categoria.getExtras();
		if(params != null){
			id_cat_niv = params.getInt("categoria_nivel");
			//cat.setIdCategoria(params.getInt("categoria"));
			//id_cat = cat.getIdCategoria();
			nivel_atual = params.getInt("nivel");
		}
		
		qt_dao = new QuestaoDao(getApplicationContext());
		cat_niv_dao = new CategoriaNivelDao(getApplicationContext());
		
		try {
			qLista = qt_dao.getQuestionSet(id_cat_niv, 5);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		qAtual = qLista.get(qid);
		
		//qAtual = qLista.get(qid);
		Button btnOpcao1 = (Button) findViewById(R.id.btnOpcao1);
		Button btnOpcao2 = (Button) findViewById(R.id.btnOpcao2);
		Button btnOpcao3 = (Button) findViewById(R.id.btnOpcao3);
		Button btnOpcao4 = (Button) findViewById(R.id.btnOpcao4);
		btnOpcao1.setOnClickListener(this);
		btnOpcao2.setOnClickListener(this);
		btnOpcao3.setOnClickListener(this);
		btnOpcao4.setOnClickListener(this);
		setQuestions();
		//ApplicationContextProvider.getBD().close();
		
	}
	
	private void setQuestions() {
		//set the question text from current question
		
		
		
		
		TextView txQuestao = (TextView) findViewById(R.id.txtQuestao);
        txQuestao.setText(qAtual.getQuestao());
        
        //set the available options
        TextView btnOpcao1 = (TextView) findViewById(R.id.btnOpcao1);
        btnOpcao1.setText(qAtual.getOpcao1());
        
        TextView btnOpcao2 = (TextView) findViewById(R.id.btnOpcao2);
        btnOpcao2.setText(qAtual.getOpcao2());
        
        TextView btnOpcao3 = (TextView) findViewById(R.id.btnOpcao3);
        btnOpcao3.setText(qAtual.getOpcao3());
        
        TextView btnOpcao4 = (TextView) findViewById(R.id.btnOpcao4);
        btnOpcao4.setText(qAtual.getOpcao4());
        
        qid++;
	}
    

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.questao, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()){
		case R.id.btnOpcao1:
			Button b1 = (Button)v;
			String respostab1 = b1.getText().toString();
			verificaResposta(respostab1);
			break;
		case R.id.btnOpcao2:
			Button b2 = (Button)v;
			String respostab2 = b2.getText().toString();
			verificaResposta(respostab2);
			break;
		case R.id.btnOpcao3:
			Button b3 = (Button)v;
			String respostab3 = b3.getText().toString();
			verificaResposta(respostab3);
			break;
		case R.id.btnOpcao4:
			Button b4 = (Button)v;
			String respostab4 = b4.getText().toString();
			verificaResposta(respostab4);
			break;
		
		}
	}

	/*método para verificar resposta certa e passar para a próxima
	e mostrar msg se perder ou responder todas as perguntas corretamente*/
	public void verificaResposta(String resposta){
		if(qAtual.getResposta().equals(resposta)){
			if(qid<5){
				qAtual = qLista.get(qid);
				setQuestions();
			}
			else{
				Log.e("ERRO", "Erro aqui!");
				nivel_atual = nivel_atual + 1;
				Log.d("DEBUG","Até aqui ok!");
				//verifica se o nivel ainda é valido para atualizar no banco, o status
				try{
					if(nivel_atual<=6){
						Log.d("DEBUG","Até aqui ok!");
						id_cat_niv = id_cat_niv + 1;
						Log.d("DEBUG",String.valueOf(id_cat_niv));
						cat_niv_dao.atualizaStatusNivel(id_cat_niv, 1);
						Log.d("DEBUG","Até aqui ok!");
					}
				}catch(Exception e){
					String err = (e.getMessage()==null)?"Atualização falhou":e.getMessage();
					 
					 Log.e("Erro", err);
				}
				mostrarMsgGanhou();
				
			} 
		}
		else {
			mostrarMsgPerdeu();
		}
		
	}
	
	//método para mostrar a mensagem se ganhar
	private void mostrarMsgGanhou(){
		
		final Dialog dialog = new Dialog(this);
		
		dialog.setContentView(R.layout.resposta_certa);
		
		dialog.setTitle("Mensagem");
		dialog.setCancelable(false);
		
		final Button btnProxNivel = (Button)dialog.findViewById(R.id.btnProximoNivel);
		final Button btnCategoria = (Button)dialog.findViewById(R.id.btnTelaCategoria);
		final Button btnSair = (Button)dialog.findViewById(R.id.btnSair);
		
		btnCategoria.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent cat = new Intent(QuestaoActivity.this, CategoriaActivity.class);
				 startActivity(cat);
				 //finish();
				 dialog.dismiss();
				
			}
		});
		btnProxNivel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(nivel_atual<=6){
					qid=0;
					try {
						qLista = qt_dao.getQuestionSet(id_cat_niv, 5);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					qAtual = qLista.get(qid);
					setQuestions();
					//finish();
				}else{
					Intent proxCategoria = new Intent(QuestaoActivity.this, CategoriaActivity.class);
					startActivity(proxCategoria);
					finish();
				}
				 
				
				 dialog.dismiss();
				
			}
		});
		btnSair.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				 Intent inicio = new Intent(QuestaoActivity.this, IniciarActivity.class);
				 startActivity(inicio);
				 finish();
				 dialog.dismiss();
				
			}
		});
		
		dialog.show();
	}
	
	//método para mostrar msg se perdeu
	private void mostrarMsgPerdeu(){
		
		final Dialog dialog = new Dialog(this);
		
		dialog.setContentView(R.layout.resposta_errada);
		dialog.setTitle("Mensagem");
		dialog.setCancelable(false);
		
		final Button btnSair = (Button)dialog.findViewById(R.id.btnSair);
		final Button btnCategoria = (Button)dialog.findViewById(R.id.btnTelaCategoria2);
		final Button btnNovoJogo = (Button)dialog.findViewById(R.id.btnJogarNovamente);
		
		btnSair.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent inicio = new Intent(QuestaoActivity.this, IniciarActivity.class);
				startActivity(inicio);
				finish();
				dialog.dismiss();
				
			}
		});
		btnCategoria.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent cat = new Intent(QuestaoActivity.this, CategoriaActivity.class);
				startActivity(cat);
				finish();
				dialog.dismiss();
				
			}
		});
		btnNovoJogo.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				qid=0;
				try {
					qLista = qt_dao.getQuestionSet(id_cat_niv, 5);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				qAtual = qLista.get(qid);
				setQuestions();
				dialog.dismiss();
				
			}
		});
		
		dialog.show();
	}
	
	@Override
	protected void onDestroy(){
		qt_dao.close();
		cat_niv_dao.close();
		super.onDestroy();
	}

}

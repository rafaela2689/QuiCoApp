package com.android.quiz.presenter;

import android.content.Context;
import android.view.View.OnClickListener;

import com.android.quiz.view.IQuestaoView;

public interface IQuestaoPresenter extends OnClickListener {

	public void setIndiceLista(int indiceLista);

	public void loadQuestions();
	
	public void consultaQuestoesBD();

	public void inicializar(IQuestaoView questaoView, Context context,
			int idCategoria, int idNivel);
	
	public int getContador();
	
	public void setContador(int contador);
	
	public String getResposta () ;
	
	public void setResposta (String resposta) ;
	
	public int getIndiceLista () ;
	
}

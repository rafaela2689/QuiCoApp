package com.android.quiz.presenter;

import android.content.Context;

import com.android.quiz.view.IQuestaoView;

public interface IQuestaoPresenter {

	public void setIndiceLista(int indiceLista);

	public void loadQuestions();
	
	public void consultaQuestoesBD();

	public void inicializar(IQuestaoView questaoView, Context context,
			int idCategoria, int idNivel);
}

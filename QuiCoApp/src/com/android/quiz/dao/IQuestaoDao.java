package com.android.quiz.dao;

import java.util.List;

import com.android.quiz.modelo.Questao;

public interface IQuestaoDao {

	/**
	 * Recuperar as questões do banco de dados.
	 * @param idCatNiv
	 * @param numQ
	 * @return
	 * @throws Exception
	 */
	public abstract List<Questao> getQuestionSet(int idCatNiv, int numQ) throws Exception;

}

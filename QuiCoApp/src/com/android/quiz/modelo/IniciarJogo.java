package com.android.quiz.modelo;

import java.util.ArrayList;
import java.util.List;

public class IniciarJogo {
	
private List<Questao> questions = new ArrayList<Questao>();
	
	public void setQuestions(List<Questao> questions) {
		this.questions = questions;
	}
	
	/**
	 * @param q the question to add
	 */
	public void addQuestions(Questao q) {
		this.questions.add(q);
	}
	
	/**
	 * @return the questions
	 */
	public List<Questao> getQuestions() {
		return questions;
	}

}

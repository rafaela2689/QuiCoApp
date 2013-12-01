package com.android.quiz.dao.impl;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.android.quiz.dao.IQuestaoDao;
import com.android.quiz.db.DBHelper;
import com.android.quiz.modelo.Questao;
import com.google.inject.Inject;

public class QuestaoDao implements IQuestaoDao {

	@Inject
	private DBHelper helper;
	
	private SQLiteDatabase db;
	
	private SQLiteDatabase getDb() {
		if (db == null) {
			db = helper.getWritableDatabase();
		}
		return db;
	}

	// Recuperar as questões do banco de dados
	public List<Questao> getQuestionSet(int idCatNiv, int numQ)
			throws Exception {

		String[] colunas = new String[] { Questao.COL_QUESTAO, Questao.COL_OPCAO_1, Questao.COL_OPCAO_2,
				Questao.COL_OPCAO_3, Questao.COL_OPCAO_4, Questao.COL_RESPOSTA };
		String where = Questao.COL_ID_CATEGORIA_NIVEL + " = ?";

		String orderBy = "RANDOM() LIMIT ?";

		String argumentos[] = new String[] { String.valueOf(idCatNiv),
				String.valueOf(numQ) };

		Cursor cursor = getDb().query(Questao.TAB_QUESTAO, colunas,
				where, argumentos, null, null, orderBy);

		List<Questao> questionSet = new ArrayList<Questao>();

		try {
			while (cursor.moveToNext()) {
				Questao q = new Questao();

				q.setQuestao(cursor.getString(0));
				q.setOpcao1(cursor.getString(1));
				q.setOpcao2(cursor.getString(2));
				q.setOpcao3(cursor.getString(3));
				q.setOpcao4(cursor.getString(4));
				q.setResposta(cursor.getString(5));

				questionSet.add(q);

			}
		} finally { 
			if (cursor != null && !cursor.isClosed()) {
				cursor.close();
			}
		}

		return questionSet;

	}
}

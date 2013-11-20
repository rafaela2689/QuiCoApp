package com.android.quiz.dao;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.android.quiz.db.DBHelper;
import com.android.quiz.modelo.Questao;

public class QuestaoDao {

	public static final String TAB_QUESTAO = "questao";
	public static final String COL_ID = "_id";
	public static final String COL_QUESTAO = "t_questao";
	public static final String COL_OPCAO_1 = "opcao1";
	public static final String COL_OPCAO_2 = "opcao2";
	public static final String COL_OPCAO_3 = "opcao3";
	public static final String COL_OPCAO_4 = "opcao4";
	public static final String COL_RESPOSTA = "resposta";
	public static final String COL_ID_CATEGORIA_NIVEL = "id_cat_nivel";
	
	
	private SQLiteDatabase getDb() {
		return DBHelper.getDataBase();
	}

	// Recuperar as questões do banco de dados
	public List<Questao> getQuestionSet(int idCatNiv, int numQ)
			throws Exception {

		String[] colunas = new String[] { COL_QUESTAO, COL_OPCAO_1, COL_OPCAO_2,
											COL_OPCAO_3, COL_OPCAO_4, COL_RESPOSTA };
		String where = COL_ID_CATEGORIA_NIVEL + " = ?";

		String orderBy = "RANDOM() LIMIT ?";

		String argumentos[] = new String[] { String.valueOf(idCatNiv),
				String.valueOf(numQ) };

		Cursor cursor = getDb().query(TAB_QUESTAO, colunas,
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

package com.android.quiz.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.android.quiz.db.DBHelper;
import com.android.quiz.modelo.Questao;

public class QuestaoDao {
	
	private DBHelper helper;
	private SQLiteDatabase db;
	
	public QuestaoDao(Context contexto){
		helper = DBHelper.instance(contexto.getApplicationContext());
	}
	
	private SQLiteDatabase getDb(){
		if (db==null){
			db = helper.getReadableDatabase();
		}
		return db;
	}

	public void close(){
		helper.close();
		db = null;
	}
	
	// Recuperar as quest�es do banco de dados
	public List<Questao> getQuestionSet(int idCatNiv, int numQ) throws Exception {
			
		String[] colunas = new String[] {DBHelper.Questao.COL_QUESTAO, DBHelper.Questao.COL_OPCAO_1, 
											DBHelper.Questao.COL_OPCAO_2, DBHelper.Questao.COL_OPCAO_3, 
											DBHelper.Questao.COL_OPCAO_4, DBHelper.Questao.COL_RESPOSTA};
		String where = DBHelper.Questao.COL_ID_CATEGORIA_NIVEL + " = ?";
			
		String orderBy = "RANDOM() LIMIT ?";
			
		String argumentos[] = new String[]{String.valueOf(idCatNiv), String.valueOf(numQ)};
			
		Cursor cursor = getDb().query(DBHelper.Questao.TAB_QUESTAO, colunas, where, argumentos, null, null, orderBy);
			
		List<Questao> questionSet = new ArrayList<Questao>();
			
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
		cursor.close();
		return questionSet;
	}
}
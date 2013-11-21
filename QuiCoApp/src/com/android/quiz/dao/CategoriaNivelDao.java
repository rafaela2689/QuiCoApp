package com.android.quiz.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.android.quiz.db.DBHelper;
import com.android.quiz.modelo.CategoriaNivel;

public class CategoriaNivelDao {

	private DBHelper helper;
	private SQLiteDatabase db;

	public CategoriaNivelDao(Context contexto) {
		helper = DBHelper.createInstance(contexto.getApplicationContext());
	}

	private SQLiteDatabase getDb() {
		if (db == null) {
			db = helper.getWritableDatabase();
		}
		return db;
	}

	// Consulta o status do nível.
	public int consultaStatusNivel(int id_categoria, int id_nivel) {

		CategoriaNivel catNiv = new CategoriaNivel();
		Cursor cursor = null;
		String where = CategoriaNivel.COL_ID_CATEGORIA + " = ? AND " + CategoriaNivel.COL_ID_NIVEL + " = ? ";
		String[] colunas = new String[] { CategoriaNivel.COL_STATUS_NIVEL };
		String argumentos[] = new String[] { String.valueOf(id_categoria),
				String.valueOf(id_nivel) };

		cursor = getDb().query(CategoriaNivel.TABELA, colunas, where, argumentos, null, null,
				null);

		cursor.moveToFirst();

		catNiv.setStatusNivel(cursor.getInt(cursor
				.getColumnIndex(CategoriaNivel.COL_STATUS_NIVEL)));

		cursor.close();

		return catNiv.getStatusNivel();
	}

	/*
	 * Atualiza o status do nível (bloqueado ou desbloqueado), recebendo como
	 * parâmetros: id da tabela categoria, id do nível e o status que será
	 * atualizado.
	 */
	public int atualizaStatusNivel(int id_categoria_nivel, int status) {
		Integer id;
		try {
			getDb().beginTransaction();
			getDb().execSQL("PRAGMA foreign_keys = on;");
			ContentValues values = new ContentValues();

			values.put(CategoriaNivel.COL_STATUS_NIVEL, status);

			String where = CategoriaNivel.COL_ID + " = ?";
			String argumentos[] = new String[] { String
					.valueOf(id_categoria_nivel) };

			id = getDb().update(CategoriaNivel.TABELA, values, where, argumentos);
			getDb().setTransactionSuccessful();
		} finally {
			getDb().endTransaction();
		}
		return id;

	}

	// consulta o id da tabela status_nivel para usar na consulta de seleção da
	// perguntas
	public int consultaIdCategoriaNivel(int id_categoria, int id_nivel) {
		CategoriaNivel catNiv = new CategoriaNivel();

		Cursor cursor = null;

		String where = CategoriaNivel.COL_ID_CATEGORIA + " = ? AND " + CategoriaNivel.COL_ID_NIVEL + " = ? ";
		String[] coluna = new String[] { CategoriaNivel.COL_ID };
		String argumentos[] = new String[] { String.valueOf(id_categoria),
				String.valueOf(id_nivel) };

		cursor = getDb().query(CategoriaNivel.TABELA, coluna, where, argumentos, null, null,
				null);

		cursor.moveToFirst();

		catNiv.setIdCatNiv(cursor.getInt(cursor.getColumnIndex(CategoriaNivel.COL_ID)));

		cursor.close();

		return catNiv.getIdCatNiv();
	}

	// Recuperar lista dos status por categoria
	public List<Integer> getListaStatusNivel(int idCategoria) throws Exception {

		String[] coluna = new String[] { CategoriaNivel.COL_STATUS_NIVEL };

		String where = CategoriaNivel.COL_ID_CATEGORIA + " = ? ";

		String orderBy = CategoriaNivel.COL_ID_NIVEL;

		String argumentos[] = new String[] { String.valueOf(idCategoria) };

		Cursor cursor = getDb().query(CategoriaNivel.TABELA, coluna, where, argumentos, null,
				null, orderBy);

		List<Integer> listaStatus = new ArrayList<Integer>();

		try {
			while (cursor.moveToNext()) {

				int status = cursor.getInt(cursor
						.getColumnIndex(CategoriaNivel.COL_STATUS_NIVEL));
				listaStatus.add(status);

			}
		} finally {
			if (cursor != null && !cursor.isClosed()) {
				cursor.close();
			}
		}

		return listaStatus;

	}

}

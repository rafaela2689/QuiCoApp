package com.android.quiz.db;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import com.android.quiz.modelo.Categoria;
import com.android.quiz.modelo.Questao;

public class DBHelper extends SQLiteOpenHelper {
	
	private static DBHelper sInstance;//variavel para única instancia da base de dados

	//The Android's default system path of your application database.
	private static String DB_PATH = "/data/data/com.android.quiz/databases/";
	private static String DB_NAME = "bdquiz";
	private SQLiteDatabase dbQuery; 
	//private static DBHelper sInstance = null;
	private static final int DATABASE_VERSAO = 1;
	private final Context dbContexto;

	public DBHelper(Context context){
		super(context, DB_NAME, null, DATABASE_VERSAO);
		this.dbContexto = context;
		
		try{
			createDataBase();
			openDataBase();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	 /**
     * Singleton for DataBase
     *
     * @return singleton instance
     */
	public static DBHelper instance(Context context){
		if (sInstance == null){
			sInstance = new DBHelper(context.getApplicationContext());
		}
		return sInstance;
	}

	/**
	 * Creates a empty database on the system and rewrites it with your own database.
	 * */
	public void createDataBase() throws IOException{

		boolean dbExist = checkDataBase();
		if(dbExist){
			
		} else{
			//By calling this method and empty database will be created into the default system path
			//of your application so we are gonna be able to overwrite that database with our database.
			this.getReadableDatabase();

			try {
				copyDataBase(); 
			} catch (IOException e) {
				throw new Error("Erro ao copiar base de dados!");
			}
		}
	}

	/**
	 * Check if the database already exist to avoid re-copying the file each time you open the application.
	 * @return true if it exists, false if it doesn't
	 */
	private boolean checkDataBase(){
		SQLiteDatabase checkDB = null;
		try{
			String myPath = DB_PATH + DB_NAME;
			checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
		}catch(SQLiteException e){
			//database does't exist yet.
		}
		if(checkDB != null){
			checkDB.close();
		}

		return checkDB != null ? true : false;
	}

	/**
	 * Copies your database from your local assets-folder to the just created empty database in the
	 * system folder, from where it can be accessed and handled.
	 * This is done by transfering bytestream.
	 * */
	private void copyDataBase() throws IOException{

		//Open your local db as the input stream
		InputStream myInput = dbContexto.getAssets().open(DB_NAME);//dbContexto.getAssets().open(DB_NAME);

		// Path to the just created empty db
		String outFileName = DB_PATH + DB_NAME;

		//Open the empty db as the output stream
		OutputStream myOutput = new FileOutputStream(outFileName);

		//transfer bytes from the inputfile to the outputfile
		byte[] buffer = new byte[1024];
		int length;
		while ((length = myInput.read(buffer))>0){
			myOutput.write(buffer, 0, length);
		}

		//Close the streams
		myOutput.flush();
		myOutput.close();
		myInput.close();

	}

	public void openDataBase() throws SQLException{
		//Open the database
		String myPath = DB_PATH + DB_NAME;
		dbQuery = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
	}

	@Override
	public synchronized void close() {
		if(dbQuery != null)
			dbQuery.close();
		super.close();
	}

	
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		openDataBase();
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}	
		
	// Métodos para acessar a base de dados
	
	//Recuperar as questões do banco de dados
	public List<Questao> getQuestionSet(int idCat, int idNiv, int numQ) throws Exception{//int id_categoria, int id_nivel, int numQ){
			List<Questao> questionSet = new ArrayList<Questao>();
			String selectQuery = "SELECT * FROM questao WHERE categoria_id = "+idCat+
					" AND nivel_id = "+idNiv+ " ORDER BY RANDOM() LIMIT " + numQ;
			dbQuery = this.getReadableDatabase();
			Cursor c = dbQuery.rawQuery(selectQuery, null);
			while (c.moveToNext()){
				Questao q = new Questao();
				q.setId(c.getInt(0));
				q.setQuestao(c.getString(1));
				q.setOpcao1(c.getString(2));
				q.setOpcao2(c.getString(3));
				q.setOpcao3(c.getString(4));
				q.setOpcao4(c.getString(5));
				q.setResposta(c.getString(6));
				q.setId_cat(c.getInt(7));
				q.setId_niv(c.getInt(8));
				questionSet.add(q);
			}
			return questionSet;
		}
	
	//Consultar tabela categoria 
	public int consultarCategoria(int idCategoria) throws Exception{
		
		Categoria cat = null;
		Cursor mCursor = null;
		dbQuery = this.getReadableDatabase();
		String tabela = "categoria";
		String where = "_id = ?";
		String[] coluna = new String[] {"_id", "nivel_atual"};
		String argumentos[] = new String[] {String.valueOf(idCategoria)} ;
		
		mCursor = dbQuery.query(tabela, coluna, where, argumentos, null, null, null);
		
		cat = new Categoria();
		mCursor.moveToFirst();
		cat.setNivelAtual(mCursor.getInt(mCursor.getColumnIndex("nivel_atual")));

		return cat.getNivelAtual();
		
		
	}
	
	//Atualiza o nível da categoria
	public boolean atualizaNivelCategoria(int id_categoria, int nivel){
			
			dbQuery = this.getWritableDatabase();
			ContentValues values = new ContentValues();
			
			values.put("nivel_atual", nivel);
			
			return dbQuery.update("categoria", values, "_id = ?", new String[] {String.valueOf(id_categoria)}) > 0;
			
		}

	//Consulta o status do nível e da categoria.
	public int consultaStatusNivel(int id_categoria, int id_nivel){
			int status;
			Cursor mCursor = null;
			dbQuery = this.getReadableDatabase();
			String tabela = "status_nivel";
			String where = "id_categoria=? AND id_nivel=?";
			String[] coluna = new String[] {"status_nivel"};
			String argumentos[] = new String[] {String.valueOf(id_categoria), String.valueOf(id_nivel)} ;
			
			mCursor = dbQuery.query(tabela, coluna, where, argumentos, null, null, null);
			
			mCursor.moveToFirst();
			
			status = mCursor.getInt(mCursor.getColumnIndex("status_nivel"));
			
			return status;
		}
		
	/*Atualiza o status do nível (bloqueado ou desbloqueado), recebendo como parâmetros:
	id da tabela categoria, id do nível e o status que será atualizado.	*/
	public boolean atualizaStatusNivel(int id_categoria, int id_nivel, int status){
			
			dbQuery = this.getWritableDatabase();
			ContentValues values = new ContentValues();
			
			values.put("status_nivel", status);
			
			String tabela = "status_nivel";
			String where = "id_categoria=? AND id_nivel=?";
			//String[] coluna = new String[] {"status_nivel"};
			String argumentos[] = new String[] {String.valueOf(id_categoria), String.valueOf(id_nivel)} ;
			
			return dbQuery.update(tabela, values, where, argumentos) > 0;
			
		}
}

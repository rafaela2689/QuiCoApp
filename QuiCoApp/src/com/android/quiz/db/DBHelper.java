package com.android.quiz.db;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {


	private static DBHelper sInstance;// variavel para única instancia da base de dados

	//The Android's default system path of your application database.
	private static String DB_PATH = "/data/data/com.android.quiz/databases/";
	private static String DB_NAME = "quicodb";
	private SQLiteDatabase dbQuery;
	private static final int DATABASE_VERSAO = 1;
	private final Context dbContexto;

	public DBHelper(Context context) {
		super(context, DB_NAME, null, DATABASE_VERSAO);
		this.dbContexto = context;

		try {
			createDataBase();
			openDataBase();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Singleton for DataBase
	 * 
	 * @return singleton instance
	 */
	public static synchronized DBHelper instance(Context context) {
		if (sInstance == null) {
			sInstance = new DBHelper(context.getApplicationContext());
		}
		return sInstance;
	}

	/**
	 * Creates a empty database on the system and rewrites it with your own
	 * database.
	 * */
	public void createDataBase() throws IOException {

		boolean dbExist = checkDataBase();
		//SQLiteDatabase db_read = null;
		if (dbExist) {

		} else {
			// By calling this method and empty database will be created into
			// the default system path
			// of your application so we are gonna be able to overwrite that
			// database with our database.
			this.getReadableDatabase();

			try {
				copyDataBase();
			} catch (IOException e) {
				throw new Error("Erro ao copiar base de dados!");
			}
		}
	} 

	/**
	 * Check if the database already exist to avoid re-copying the file each
	 * time you open the application.
	 * 
	 * @return true if it exists, false if it doesn't
	 */
	private boolean checkDataBase() {
		//SQLiteDatabase checkDB = null;
		
		File dbfile = new File(DB_PATH + DB_NAME);
		return dbfile.exists();
		/*try {
			//String myPath = dbContexto.getFilesDir().getPath() + DB_NAME;
			//String myPath = dbContexto.getDatabasePath(DB_NAME).getAbsolutePath();
			String myPath = DB_PATH + DB_NAME;
			checkDB = SQLiteDatabase.openDatabase(myPath, null,
					SQLiteDatabase.OPEN_READONLY);
		} catch (SQLiteException e) {
			// database does't exist yet.
		}
		if (checkDB != null) {
			checkDB.close();
		}

		return checkDB != null ? true : false;*/
	}

	/**
	 * Copies your database from your local assets-folder to the just created
	 * empty database in the system folder, from where it can be accessed and
	 * handled. This is done by transfering bytestream.
	 * */
	private void copyDataBase() throws IOException {

		// Open your local db as the input stream
		InputStream myInput = dbContexto.getAssets().open(DB_NAME);// dbContexto.getAssets().open(DB_NAME);

		// Path to the just created empty db
		String outFileName = DB_PATH + DB_NAME;
		//String outFileName = dbContexto.getDatabasePath(DB_NAME).getAbsolutePath();

		// Open the empty db as the output stream
		OutputStream myOutput = new FileOutputStream(outFileName);

		// transfer bytes from the inputfile to the outputfile
		byte[] buffer = new byte[1024];
		int length;
		while ((length = myInput.read(buffer)) > 0) {
			myOutput.write(buffer, 0, length);
		}

		// Close the streams
		myOutput.flush();
		myOutput.close();
		myInput.close();

	}

	public void openDataBase() throws SQLException {
		// Open the database
		String myPath = DB_PATH + DB_NAME;
		//String myPath = dbContexto.getDatabasePath(DB_NAME).getAbsolutePath();
		dbQuery = SQLiteDatabase.openDatabase(myPath, null,
				SQLiteDatabase.OPEN_READONLY);
	}

	@Override
	public synchronized void close() {
		if (dbQuery != null)
			dbQuery.close();
		super.close();
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

	public static class Questao{
		public static final String TAB_QUESTAO = "questao";
		public static final String COL_ID = "_id";
		public static final String COL_QUESTAO = "t_questao";
		public static final String COL_OPCAO_1 = "opcao1";
		public static final String COL_OPCAO_2 = "opcao2";
		public static final String COL_OPCAO_3 = "opcao3";
		public static final String COL_OPCAO_4 = "opcao4";
		public static final String COL_RESPOSTA = "resposta";
		public static final String COL_ID_CATEGORIA_NIVEL = "id_cat_nivel";
		
		public static final String[] COLUNAS_QUESTAO = new String[]{COL_ID, COL_OPCAO_1, COL_OPCAO_2,
														COL_OPCAO_3, COL_OPCAO_4, COL_RESPOSTA,
														COL_ID_CATEGORIA_NIVEL};
	}
}

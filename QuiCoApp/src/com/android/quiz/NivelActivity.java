package com.android.quiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class NivelActivity extends Activity implements OnClickListener{

ImageButton btnNivel1, btnNivel2, btnNivel3, btnNivel4, btnNivel5, btnNivel6;
int id_categoria;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		Intent nivel = getIntent();
		Bundle params = nivel.getExtras();
		if (params!= null){ 
			id_categoria = params.getInt("categoria");
			setContentView(R.layout.nivel);
		}
		
		
		ImageButton  btnNivel1 = (ImageButton)findViewById(R.id.imgBtnNivel1);
		ImageButton  btnNivel2 = (ImageButton)findViewById(R.id.imgBtnNivel2);
		ImageButton  btnNivel3 = (ImageButton)findViewById(R.id.imgBtnNivel3);
		ImageButton  btnNivel4 = (ImageButton)findViewById(R.id.imgBtnNivel4);
		ImageButton  btnNivel5 = (ImageButton)findViewById(R.id.imgBtnNivel5);
		ImageButton  btnNivel6 = (ImageButton)findViewById(R.id.imgBtnNivel6);
		
		btnNivel1.setOnClickListener(this);
		btnNivel2.setOnClickListener(this);
		btnNivel3.setOnClickListener(this);
		btnNivel4.setOnClickListener(this);
		btnNivel5.setOnClickListener(this);
		btnNivel6.setOnClickListener(this);
		
	}
		
		/*btnNivel1.setEnabled(true);
		btnNivel2.setEnabled(false);
		btnNivel3.setEnabled(false);
		btnNivel4.setEnabled(false);
		btnNivel5.setEnabled(false);
		btnNivel6.setEnabled(false);*/
		//clicar no nivel 1
		/*btnNivel1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//btnNivel1.setEnabled(true);
				
				//Get Question set //
				//List<Questao> questions = getQuestionSetFromDb();

				//Initialise Game with retrieved question set ///
				//IniciarJogo c = new IniciarJogo();
				//c.setQuestions(questions);
				//c.setNumRounds(getNumQuestions());
				//((ChuckApplication)getApplication()).setCurrentGame(c);  

				//Start Game Now.. //
				Intent i = new Intent(NivelActivity.this, QuestaoActivity.class);
				startActivity(i);

				
			}
		});
	
		
		//clicar no nivel 2
		btnNivel2.setOnClickListener(new OnClickListener() {
					
			@Override
			public void onClick(View v) {
				Intent i = new Intent(NivelActivity.this, QuestaoActivity.class);
				startActivity(i);
						
			}
		});
	}

	/*@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.nivel, menu);
		return true;
	}*/
	
	/**
	 * Method that retrieves a random set of questions from
	 * the database for the given difficulty
	 * @return
	 * @throws Error
	 */
	/*private List<Questao> getQuestionSetFromDb() throws Error {
		//int diff = getDifficultySettings();
		//int numQuestions = getNumQuestions();
		DBHelper myDbHelper = new DBHelper(this);
		try {
			myDbHelper.createDataBase();
		} catch (IOException ioe) {
			throw new Error("Não foi possível criar a base de dados!");
		}
		try {
			myDbHelper.openDataBase();
		}catch(SQLException sqle){
			throw sqle;
		}
		List<Questao> questions = myDbHelper.getQuestionSet(5);
		myDbHelper.close();
		return questions;
	}*/



	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.imgBtnNivel1:
			
			Intent nivel = new Intent(this, QuestaoActivity.class);
			Bundle params = new Bundle();
			params.putInt("categoria", id_categoria);
			nivel.putExtras(params);
			startActivity(nivel);
			break;
		case R.id.imgBtnNivel2:
			break;
		case R.id.imgBtnNivel3:
			break;
		case R.id.imgBtnNivel4:
			break;
		case R.id.imgBtnNivel5:
			break;
		case R.id.imgBtnNivel6:
			break;
		
		}
		
	}
}

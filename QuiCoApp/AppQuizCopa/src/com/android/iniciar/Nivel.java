package com.android.iniciar;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ImageButton;

import com.android.quizcopa.R;

public class Nivel extends Activity {

	ImageButton btnNivel1, btnNivel2, btnNivel3, btnNivel4, btnNivel5, btnNivel6;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tela_nivel);
		
		ImageButton  btnNivel1 = (ImageButton)findViewById(R.id.imgBtnNivel1);
		ImageButton  btnNivel2 = (ImageButton)findViewById(R.id.imgBtnNivel2);
		ImageButton  btnNivel3 = (ImageButton)findViewById(R.id.imgBtnNivel3);
		ImageButton  btnNivel4 = (ImageButton)findViewById(R.id.imgBtnNivel4);
		ImageButton  btnNivel5 = (ImageButton)findViewById(R.id.imgBtnNivel5);
		ImageButton  btnNivel6 = (ImageButton)findViewById(R.id.imgBtnNivel6);
		
		btnNivel1.setEnabled(true);
		btnNivel2.setEnabled(false);
		btnNivel3.setEnabled(false);
		btnNivel4.setEnabled(false);
		btnNivel5.setEnabled(false);
		btnNivel6.setEnabled(false);
		//clicar no nivel 1
		/*btnNivel1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				btnNivel1.setEnabled(true);
				
			}
		});*/
		
		//clicar no nivel 2
		/*btnNivel2.setOnClickListener(new OnClickListener() {
					
			@Override
			public void onClick(View v) {
				btnNivel2.setEnabled(false);
						
			}
		});*/
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.nivel, menu);
		return true;
	}

}

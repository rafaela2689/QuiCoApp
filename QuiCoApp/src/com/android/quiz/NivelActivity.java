package com.android.quiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.quiz.dao.CategoriaNivelDao;
import com.android.quiz.modelo.Categoria;

public class NivelActivity extends Activity implements OnClickListener {

	public static final String CATEGORIA_NIVEL = "categoria_nivel";
	public static final String NIVEL = "nivel";
	ImageButton btnNivel1, btnNivel2, btnNivel3, btnNivel4, btnNivel5, btnNivel6;
	Categoria cat;
	int id_categoria;
	int id_cat_niv;
	CategoriaNivelDao dao;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//recebe o parametros da Activity Categoria
		Intent nivel = getIntent();
		Bundle params = nivel.getExtras();
		if (params != null) {
			id_categoria = params.getInt("categoria");
			setContentView(R.layout.nivel);
		}
		
		btnNivel1 = (ImageButton) findViewById(R.id.imgBtnNivel1);
		btnNivel2 = (ImageButton) findViewById(R.id.imgBtnNivel2);
		btnNivel3 = (ImageButton) findViewById(R.id.imgBtnNivel3);
		btnNivel4 = (ImageButton) findViewById(R.id.imgBtnNivel4);
		btnNivel5 = (ImageButton) findViewById(R.id.imgBtnNivel5);
		btnNivel6 = (ImageButton) findViewById(R.id.imgBtnNivel6);
		
		btnNivel1.setOnClickListener(this);
		btnNivel2.setOnClickListener(this);
		btnNivel3.setOnClickListener(this);
		btnNivel4.setOnClickListener(this);
		btnNivel5.setOnClickListener(this);
		btnNivel6.setOnClickListener(this);

		verificaStatusCategoriaNivel(id_categoria);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		cat = new Categoria();
		switch (v.getId()) {
		case R.id.imgBtnNivel1:
			//consulta o id da tabela categoria_nivel
			id_cat_niv = dao.consultaIdCategoriaNIvel(id_categoria, 1);
			Intent nivel1 = new Intent(this, QuestaoActivity.class);
			Bundle param1 = new Bundle();
			param1.putInt("categoria_nivel", id_cat_niv);
			param1.putInt("nivel", 1);
			nivel1.putExtras(param1);
			startActivity(nivel1);
			break;
		case R.id.imgBtnNivel2:
			
			id_cat_niv = dao.consultaIdCategoriaNIvel(id_categoria, 2);
			Intent nivel2 = new Intent(this, QuestaoActivity.class);
			Bundle param2 = new Bundle();
			param2.putInt("categoria_nivel", id_cat_niv);
			param2.putInt("nivel", 2);
			nivel2.putExtras(param2);
			startActivity(nivel2);
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

	//verifica o status do nivel para cada categoria
	public void verificaStatusCategoriaNivel(int categoria) {
		if (categoria == 1) {
			verificaStatusNivel(categoria, 1);
			verificaStatusNivel(categoria, 2);
			verificaStatusNivel(categoria, 3);
			verificaStatusNivel(categoria, 4);
			verificaStatusNivel(categoria, 5);
			verificaStatusNivel(categoria, 6);
		}
		if (categoria == 2) {
			verificaStatusNivel(categoria, 1);
			verificaStatusNivel(categoria, 2);
			verificaStatusNivel(categoria, 3);
			verificaStatusNivel(categoria, 4);
			verificaStatusNivel(categoria, 5);
			verificaStatusNivel(categoria, 6);
		}
		if (categoria == 3) {
			verificaStatusNivel(categoria, 1);
			verificaStatusNivel(categoria, 2);
			verificaStatusNivel(categoria, 3);
			verificaStatusNivel(categoria, 4);
			verificaStatusNivel(categoria, 5);
			verificaStatusNivel(categoria, 6);
		}

	}

	//verifica o status do nível para habilitar ou desabilitar os botões 
	public void verificaStatusNivel(int categoria, int nivel) {

		dao = new CategoriaNivelDao(getApplicationContext());
		int status;

		if (nivel == 1) {
			status = dao.consultaStatusNivel(categoria, nivel);
			if (status == 1) {
				btnNivel1.setEnabled(true);
			} else {
				btnNivel1.setEnabled(false);
			}
		}
		if (nivel == 2) {
			status = dao.consultaStatusNivel(categoria, nivel);
			if (status == 1) {
				btnNivel2.setEnabled(true);
			} else {
				btnNivel2.setEnabled(false);
			}
		}
		if (nivel == 3) {
			status = dao.consultaStatusNivel(categoria, nivel);
			if (status == 1) {
				btnNivel3.setEnabled(true);
			} else {
				btnNivel3.setEnabled(false);
			}
		}
		if (nivel == 4) {
			status = dao.consultaStatusNivel(categoria, nivel);
			if (status == 1) {
				btnNivel4.setEnabled(true);
			} else {
				btnNivel4.setEnabled(false);
			}
		}
		if (nivel == 5) {
			status = dao.consultaStatusNivel(categoria, nivel);
			if (status == 1) {
				btnNivel5.setEnabled(true);
			} else {
				btnNivel5.setEnabled(false);
			}
		}
		if (nivel == 6) {
			status = dao.consultaStatusNivel(categoria, nivel);
			if (status == 1) {
				btnNivel6.setEnabled(true);
			} else {
				btnNivel6.setEnabled(false);
			}
		}
	}

	// fecha conexao do banco de dados
	@Override
	protected void onDestroy() {
		dao.close();
		super.onDestroy();
	}
}

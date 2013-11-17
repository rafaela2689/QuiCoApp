package com.android.quiz;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

import com.android.quiz.dao.CategoriaNivelDao;

public class NivelActivity extends Activity implements OnClickListener {

	public static final String CATEGORIA_NIVEL = "categoria_nivel";
	public static final String NIVEL = "nivel";
	public static final String CATEGORIA = "categoria";
	ImageButton btnNivel1, btnNivel2, btnNivel3, btnNivel4, btnNivel5, btnNivel6;
	int id_categoria;
	int id_cat_niv;
	CategoriaNivelDao dao;

	List<ImageButton> botoes = new ArrayList<ImageButton>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// recebe o parametros da Activity Categoria
		Intent nivel = getIntent();
		Bundle params = nivel.getExtras();
		if (params != null) {
			id_categoria = params.getInt(CATEGORIA);
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

		botoes.add(btnNivel1);
		botoes.add(btnNivel2);
		botoes.add(btnNivel3);
		botoes.add(btnNivel4);
		botoes.add(btnNivel5);
		botoes.add(btnNivel6);

		verificaStatusCategoriaNivel(id_categoria);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.imgBtnNivel1:
			chamaTelaQuestao(NivelEnum.NIVEL1);
			break;
		case R.id.imgBtnNivel2:
			chamaTelaQuestao(NivelEnum.NIVEL2);
			finish();
			break;
		case R.id.imgBtnNivel3:
			chamaTelaQuestao(NivelEnum.NIVEL3);
			finish();
			break;
		case R.id.imgBtnNivel4:
			chamaTelaQuestao(NivelEnum.NIVEL4);
			finish();
			break;
		case R.id.imgBtnNivel5:
			chamaTelaQuestao(NivelEnum.NIVEL5);
			break;
		case R.id.imgBtnNivel6:
			chamaTelaQuestao(NivelEnum.NIVEL6);
			break;

		}

	}

	public void chamaTelaQuestao(NivelEnum niv) {

		id_cat_niv = dao.consultaIdCategoriaNIvel(id_categoria, niv.getNivel());
		Intent nivel = new Intent(this, QuestaoActivity.class);
		Bundle param = new Bundle();
		param.putInt(CATEGORIA_NIVEL, id_cat_niv);
		param.putInt(NIVEL, niv.getNivel());
		nivel.putExtras(param);
		startActivity(nivel);
		finish();

	}

	// verifica o status do nivel para cada categoria
	public void verificaStatusCategoriaNivel(int categoria) {

		for (int i = 1; i <= 6; i++) {
			verificaStatusNivel(categoria, i);

		}
	}

	// verifica o status do nível para habilitar ou desabilitar os botões
	public void verificaStatusNivel(int categoria, int nivel) {

		dao = new CategoriaNivelDao(getApplicationContext());
		List<Integer> listStatus = null;
		int cont = 0;
		try {
			listStatus = dao.getListaStatusNivel(categoria);
		} catch (Exception e) {
			e.printStackTrace();
		}

		for (Integer status : listStatus) {
			if (status == 1) {
				botoes.get(cont).setEnabled(true);
			} else {
				botoes.get(cont).setEnabled(false);
			}
			cont++;
		}
	}
}

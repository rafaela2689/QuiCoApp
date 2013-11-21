package com.android.quiz.presenter;

import java.util.List;

import android.content.Context;

import com.android.quiz.dao.CategoriaNivelDao;
import com.android.quiz.view.INivelView;

public class NivelPresenter {

	private CategoriaNivelDao dao;
	
	private INivelView nivelView;
	
	private Context context;
	
	public NivelPresenter(INivelView nivelView, Context context) {
		this.nivelView = nivelView;
		this.context = context;
		
	}
	
	// verifica o status do nivel para cada categoria
	public void verificaStatusCategoriaNivel(int categoria) {
		for (int i = 1; i <= 6; i++) {
			verificaStatusNivel(categoria, i);

		}
	}
	
	// verifica o status do nível para habilitar ou desabilitar os botões
	private void verificaStatusNivel(int categoria, int nivel) {

		dao = new CategoriaNivelDao(context);
		List<Integer> listStatus = null;
		int cont = 0;
		try {
			listStatus = dao.getListaStatusNivel(categoria);
		} catch (Exception e) {
			e.printStackTrace();
		}

		for (Integer status : listStatus) {
			if (status == 1) {
				nivelView.getBotoes().get(cont).setEnabled(true);
			} else {
				nivelView.getBotoes().get(cont).setEnabled(false);
			}
			cont++;
		}
	}

}

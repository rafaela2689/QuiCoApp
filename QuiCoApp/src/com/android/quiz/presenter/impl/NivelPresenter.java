package com.android.quiz.presenter.impl;

import java.util.List;

import com.android.quiz.dao.ICategoriaNivelDao;
import com.android.quiz.presenter.INivelPresenter;
import com.android.quiz.view.INivelView;
import com.google.inject.Inject;

public class NivelPresenter implements INivelPresenter {

	@Inject
	private ICategoriaNivelDao dao;
	
	private INivelView nivelView;
	
	// verifica o status do nivel para cada categoria
	@Override
	public void verificaStatusCategoriaNivel(int categoria) {
		for (int i = 1; i <= 6; i++) {
			verificaStatusNivel(categoria, i);

		}
	}
	
	public void setNivelView(INivelView nivelView) {
		this.nivelView = nivelView;
		
	}
	
	// verifica o status do nível para habilitar ou desabilitar os botões
	private void verificaStatusNivel(int categoria, int nivel) {

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

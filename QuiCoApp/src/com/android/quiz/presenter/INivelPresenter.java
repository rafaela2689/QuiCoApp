package com.android.quiz.presenter;

import com.android.quiz.view.INivelView;

public interface INivelPresenter {

	// verifica o status do nivel para cada categoria
		public abstract void verificaStatusCategoriaNivel(int categoria);
		
		public void setNivelView(INivelView nivelView);
}

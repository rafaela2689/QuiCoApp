package com.android.quiz;

import com.android.quiz.dao.ICategoriaNivelDao;
import com.android.quiz.dao.IQuestaoDao;
import com.android.quiz.dao.impl.CategoriaNivelDao;
import com.android.quiz.dao.impl.QuestaoDao;
import com.android.quiz.presenter.INivelPresenter;
import com.android.quiz.presenter.IQuestaoPresenter;
import com.android.quiz.presenter.impl.NivelPresenter;
import com.android.quiz.presenter.impl.QuestaoPresenter;
import com.google.inject.AbstractModule;

public class QuizCopaModule extends AbstractModule {
	
	@Override
	protected void configure() {

		bind(INivelPresenter.class).to(NivelPresenter.class);
    	bind(ICategoriaNivelDao.class).to(CategoriaNivelDao.class);
    	
    	bind(IQuestaoPresenter.class).to(QuestaoPresenter.class);
    	bind(IQuestaoDao.class).to(QuestaoDao.class);
	}
	
}

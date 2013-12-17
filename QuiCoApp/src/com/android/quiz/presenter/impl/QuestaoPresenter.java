package com.android.quiz.presenter.impl;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;

import com.android.quiz.R;
import com.android.quiz.dao.ICategoriaNivelDao;
import com.android.quiz.dao.IQuestaoDao;
import com.android.quiz.dao.impl.QuestaoDao;
import com.android.quiz.modelo.Questao;
import com.android.quiz.presenter.IQuestaoPresenter;
import com.android.quiz.util.Constantes;
import com.android.quiz.view.IQuestaoView;
import com.google.inject.Inject;

public class QuestaoPresenter implements OnClickListener, IQuestaoPresenter{

	private Questao questaoAtual;
	
	private List<Questao> qLista;
	
	@Inject
	private IQuestaoDao questaoDao;
	
	@Inject
	private ICategoriaNivelDao categoriaNivelDao;
	
	private IQuestaoView questaoView;
	
	private int contador = 1;
	
	private int indiceLista = 0;
	
	public void setIndiceLista(int indiceLista) {
		this.indiceLista = indiceLista;
	}

	private int idCategoria;
	
	private int idNivel;
	
	private int idCatNiv;
	
	@Override
	public void inicializar(IQuestaoView questaoView, Context context,
			int idCategoria, int idNivel) {
		this.questaoView = questaoView;
		this.idCategoria = idCategoria;
		this.idNivel = idNivel;
		
	}
	
	// consulta questões no banco de dados
		public void consultaQuestoesBD() {
			
			idCatNiv = consultaIdCategoriaNivel();
			
			try {
				qLista = questaoDao.getQuestionSet(idCatNiv, Constantes.NUM_QUESTOES);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		public int consultaIdCategoriaNivel(){
			
			return categoriaNivelDao.consultaIdCategoriaNivel(idCategoria, idNivel);
		}
		
	
		@Override
		public void loadQuestions() {
			questaoAtual = new Questao();

			questaoAtual = qLista.get(indiceLista);
			// seta a questao no textView
			questaoView.setQuestao(questaoAtual.getQuestao());

			// seta as opções nos botoes
			questaoView.setOpcao1(questaoAtual.getOpcao1());

			questaoView.setOpcao2(questaoAtual.getOpcao2());

			questaoView.setOpcao3(questaoAtual.getOpcao3());

			questaoView.setOpcao4(questaoAtual.getOpcao4());

			questaoView.setContador(String.valueOf(contador) + "/"+ Constantes.NUM_QUESTOES);
			
			contador++;

			// incrementa o índice da lista
			indiceLista++;
			
		}

		/*
		 * método para verificar resposta certa e passar para a próxima e mostrar
		 * msg se perder ou responder todas as perguntas corretamente
		 */
		public void verificaResposta(String resposta) {
			if (questaoAtual.getResposta().equals(resposta)) {
				if (indiceLista < Constantes.NUM_QUESTOES) {
					questaoAtual = qLista.get(indiceLista);
					loadQuestions();

				} else {
					contador = 1;
					// incrementa o nivel atual
					idNivel = idNivel + 1;

					// verifica se o nivel ainda é valido para atualizar no banco, o
					// status do nível
					if (idNivel < 6) {

						// incrementa o id do proximo nível, para desbloquear o
						// botao
						idCatNiv = idCatNiv + 1;
						// atualiza o status do nivel para desbloqueado
						categoriaNivelDao.atualizaStatusNivel(idCatNiv, 1);

					}
					questaoView.mostrarMsgGanhou();

				}
			} else {
				contador = 1;
				questaoView.mostrarMsgPerdeu();
			}

		}
		
		// método para saber qual botão foi clicado
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.btnOpcao1:
				// pega o texto do botão
				// verifica se a resposta clicada é igual a cadastrada no banco
				verificaResposta(questaoView.getOpcao1());
				break;
			case R.id.btnOpcao2:
				verificaResposta(questaoView.getOpcao2());
				break;
			case R.id.btnOpcao3:
				verificaResposta(questaoView.getOpcao3());
				break;
			case R.id.btnOpcao4:
				verificaResposta(questaoView.getOpcao4());
				break;

			}
		}

}

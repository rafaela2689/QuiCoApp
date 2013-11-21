package com.android.quiz.presenter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;

import com.android.quiz.R;
import com.android.quiz.dao.CategoriaNivelDao;
import com.android.quiz.dao.QuestaoDao;
import com.android.quiz.modelo.Questao;
import com.android.quiz.util.Constantes;
import com.android.quiz.view.IQuestaoView;

public class QuestaoPresenter implements OnClickListener{

	private Questao questaoAtual;
	
	private List<Questao> qLista;
	
	private QuestaoDao questaoDao;
	
	private CategoriaNivelDao categoriaNivelDao;
	
	private IQuestaoView questaoView;
	
	private Context context;
	
	private int contador = 1;
	
	private int indiceLista = 0;
	
	public void setIndiceLista(int indiceLista) {
		this.indiceLista = indiceLista;
	}

	private int idCategoria;
	
	private int idNivel;
	
	private int idCatNiv;
	
	public QuestaoPresenter(IQuestaoView questaoView, Context context, int idCategoria, int idNivel) {
		this.questaoView = questaoView;
		this.context = context;
		this.idCategoria = idCategoria;
		this.idNivel = idNivel;
	}
	
	// consulta quest�es no banco de dados
		public void consultaQuestoesBD() {
			
			idCatNiv = consultaIdCategoriaNivel();
			
			questaoDao = new QuestaoDao();
			
			try {
				qLista = questaoDao.getQuestionSet(idCatNiv, Constantes.NUM_QUESTOES);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		public int consultaIdCategoriaNivel(){
			
			categoriaNivelDao = new CategoriaNivelDao (context);
			
			return categoriaNivelDao.consultaIdCategoriaNivel(idCategoria, idNivel);
		}
		
		public void setQuestions() {
			questaoAtual = new Questao();

			questaoAtual = qLista.get(indiceLista);
			// seta a questao no textView
			questaoView.setQuestao(questaoAtual.getQuestao());

			// seta as op��es nos botoes
			questaoView.setOpcao1(questaoAtual.getOpcao1());

			questaoView.setOpcao2(questaoAtual.getOpcao2());

			questaoView.setOpcao3(questaoAtual.getOpcao3());

			questaoView.setOpcao4(questaoAtual.getOpcao4());

			questaoView.setContador(String.valueOf(contador) + "/"+ Constantes.NUM_QUESTOES);
			
			contador++;

			// incrementa o �ndice da lista
			indiceLista++;
		}
		
		/*
		 * m�todo para verificar resposta certa e passar para a pr�xima e mostrar
		 * msg se perder ou responder todas as perguntas corretamente
		 */
		public void verificaResposta(String resposta) {
			if (questaoAtual.getResposta().equals(resposta)) {
				if (indiceLista < Constantes.NUM_QUESTOES) {
					questaoAtual = qLista.get(indiceLista);
					setQuestions();

				} else {
					contador = 1;
					// incrementa o nivel atual
					idNivel = idNivel + 1;

					// verifica se o nivel ainda � valido para atualizar no banco, o
					// status do n�vel
					if (idNivel < 6) {

						// incrementa o id do proximo n�vel, para desbloquear o
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
		
		// m�todo para saber qual bot�o foi clicado
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.btnOpcao1:
				// pega o texto do bot�o
				// verifica se a resposta clicada � igual a cadastrada no banco
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

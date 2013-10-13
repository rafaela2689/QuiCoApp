package com.android.quiz.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Questao {
	
	private int id;
	private String questao;
	private String opcao1;
	private String opcao2;
	private String opcao3;
	private String opcao4;
	private String resposta;
	private int id_cat;
	private int id_niv;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId_cat() {
		return id_cat;
	}
	public void setId_cat(int id_cat) {
		this.id_cat = id_cat;
	}
	public int getId_niv() {
		return id_niv;
	}
	public void setId_niv(int id_niv) {
		this.id_niv = id_niv;
	}
	
	public String getQuestao() {
		return questao;
	}
	public void setQuestao(String questao) {
		this.questao = questao;
	}
	public String getOpcao1() {
		return opcao1;
	}
	public void setOpcao1(String opcao1) {
		this.opcao1 = opcao1;
	}
	public String getOpcao2() {
		return opcao2;
	}
	public void setOpcao2(String opcao2) {
		this.opcao2 = opcao2;
	}
	public String getOpcao3() {
		return opcao3;
	}
	public void setOpcao3(String opcao3) {
		this.opcao3 = opcao3;
	}
	public String getOpcao4() {
		return opcao4;
	}
	public void setOpcao4(String opcao4) {
		this.opcao4 = opcao4;
	}
	public String getResposta() {
		return resposta;
	}
	public void setResposta(String resposta) {
		this.resposta = resposta;
	}
	
	public List<String> getQuestaoOpcoes(){
		List<String> shuffle = new ArrayList<String>();
		shuffle.add(resposta);
		shuffle.add(opcao1);
		shuffle.add(opcao2);
		shuffle.add(opcao3);
		shuffle.add(opcao4);
		Collections.shuffle(shuffle);
		return shuffle;
	}

}

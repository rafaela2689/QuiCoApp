package com.android.quiz.modelo;


public class Questao {
	
	public static final String TAB_QUESTAO = "questao";
	public static final String COL_ID = "_id";
	public static final String COL_QUESTAO = "t_questao";
	public static final String COL_OPCAO_1 = "opcao1";
	public static final String COL_OPCAO_2 = "opcao2";
	public static final String COL_OPCAO_3 = "opcao3";
	public static final String COL_OPCAO_4 = "opcao4";
	public static final String COL_RESPOSTA = "resposta";
	public static final String COL_ID_CATEGORIA_NIVEL = "id_cat_nivel";
	
	private int id;
	private String questao;
	private String opcao1;
	private String opcao2;
	private String opcao3;
	private String opcao4;
	private String resposta;
	private int idCcat;
	private int idNiv;
	
	public Questao(){
		
	}
	
	public Questao(int id, String questao, String opcao1, String opcao2,
			String opcao3, String opcao4, String resposta, int idCat,
			int idNiv) {
		super();
		this.id = id;
		this.questao = questao;
		this.opcao1 = opcao1;
		this.opcao2 = opcao2;
		this.opcao3 = opcao3;
		this.opcao4 = opcao4;
		this.resposta = resposta;
		this.idCcat = idCat;
		this.idNiv = idNiv;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdCat() {
		return idCcat;
	}
	public void setIdCat(int idCat) {
		this.idCcat = idCat;
	}
	public int getIdNiv() {
		return idNiv;
	}
	public void setId_niv(int idNiv) {
		this.idNiv = idNiv;
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

}

package com.android.quiz.view;


public interface IQuestaoView {
	
	public void setQuestao(String questao);
	
	public void setContador(String contador);
		
	public void setOpcao1(String opcao1);
		
	public void setOpcao2(String opcao2);
		
	public void setOpcao3(String opcao3);
		
	public void setOpcao4(String opcao4);
	
	public String getQuestao();
	
	public String getOpcao1();
	
	public String getOpcao2();
	
	public String getOpcao3();
	
	public String getOpcao4();
	
	public void mostrarMsgGanhou();
	
	public void mostrarMsgPerdeu();
}

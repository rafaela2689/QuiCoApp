package com.android.quiz.modelo;

public class CategoriaNivel {
	
	public static final String TABELA = "categoria_nivel";
	public static final String COL_ID_CATEGORIA = "id_categoria";
	public static final String COL_ID_NIVEL = "id_nivel";
	public static final String COL_STATUS_NIVEL = "status_nivel";
	public static final String COL_ID = "_id";
	
	private int idCatNiv;
	private int idCat;
	private int idNiv;
	private int statusNivel;
	
	public int getIdCatNiv() {
		return idCatNiv;
	}
	public void setIdCatNiv(int idCatNiv) {
		this.idCatNiv = idCatNiv;
	}
	public int getIdCat() {
		return idCat;
	}
	public void setIdCat(int idCat) {
		this.idCat = idCat;
	}
	public int getIdNiv() {
		return idNiv;
	}
	public void setIdNiv(int idNiv) {
		this.idNiv = idNiv;
	}
	public int getStatusNivel() {
		return statusNivel;
	}
	public void setStatusNivel(int statusNivel) {
		this.statusNivel = statusNivel;
	}
	

}

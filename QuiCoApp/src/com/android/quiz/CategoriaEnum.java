package com.android.quiz;

public enum CategoriaEnum {

	SELECOES(1), JOGADORES(2), CURIOSIDADES(3);
	
	private Integer categoria;
	
	CategoriaEnum(Integer categoria){
		this.categoria = categoria;
	}

	public Integer getCategoria() {
		return categoria;
	}

	public static CategoriaEnum getValor(Integer valor){
		for(CategoriaEnum cat :CategoriaEnum.values()){
			if(cat.getCategoria().intValue() == valor.intValue()){
				return cat;
			}
		}
		return null;
	}
	
}

package com.android.quiz.enumerado;

public enum NivelEnum {

	NIVEL1(1), NIVEL2(2), NIVEL3(3), NIVEL4(4), NIVEL5(5), NIVEL6(6);
	
	private Integer nivel;
	
	NivelEnum(Integer nivel){
		this.nivel = nivel;
	}

	public Integer getNivel() {
		return nivel;
	}

	public static NivelEnum getValor(Integer valor){
		for(NivelEnum nivel :NivelEnum.values()){
			if(nivel.getNivel().intValue() == valor.intValue()){
				return nivel;
			}
		}
		return null;
	}
}

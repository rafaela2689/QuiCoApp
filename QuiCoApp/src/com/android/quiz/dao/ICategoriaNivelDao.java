package com.android.quiz.dao;

import java.util.List;

public interface ICategoriaNivelDao {

	/**
	 * Consulta o status do nível.
	 * @param id_categoria
	 * @param id_nivel
	 * @return
	 */
	public int consultaStatusNivel(int id_categoria, int id_nivel);

	/**
	 * Atualiza o status do nível (bloqueado ou desbloqueado), recebendo como
	 * parâmetros: id da tabela categoria, id do nível e o status que será
	 * atualizado.
	 */
	public int atualizaStatusNivel(int id_categoria_nivel, int status);

	/**
	 * consulta o id da tabela status_nivel para usar na consulta de seleção da perguntas.
	 * @param id_categoria
	 * @param id_nivel
	 * @return
	 */
	public int consultaIdCategoriaNivel(int id_categoria, int id_nivel);

	/**
	 * Recuperar lista dos status por categoria
	 * @param idCategoria
	 * @return
	 * @throws Exception
	 */
	public List<Integer> getListaStatusNivel(int idCategoria) throws Exception;

}

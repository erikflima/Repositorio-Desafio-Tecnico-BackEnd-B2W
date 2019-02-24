package com.erik.desafiotecnico.service;
import java.util.List;
import java.util.Optional;
import com.erik.desafiotecnico.entities.Planeta;


public interface PlanetaService {

	
	/** Retorna um Planeta por ID.
	 * 
	 * @param id
	 * @return Optional<Planeta>
	 */
	Optional<Planeta> buscarPorId(Long id);

	
	/** Retorna uma lista de Planetas.
	 * 
	 * @return List<Planeta>
	 */
	List<Planeta> buscarTodos();
	
	
	/** Retorna um Planeta por Nome.
	 * 
	 * @param nome
	 * @return Optional<Planeta>
	 */
	Optional<Planeta> buscarPorNome(String nome);	
	
	
	/** Cadastra um novo planeta na base de dados.
	 * 
	 * @param Planeta
	 * @return Planeta
	 */
	Planeta adicionar(Planeta planeta);
	
	
	/** Remove um Planeta da base de dados pelo Id.
	 * 
	 * @param id
	 */
	void remover( Long id );
}
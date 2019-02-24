package com.erik.desafiotecnico.service.impl;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.erik.desafiotecnico.entities.Planeta;
import com.erik.desafiotecnico.repositories.PlanetaRepository;
import com.erik.desafiotecnico.service.PlanetaService;


@Service 
public class PlanetaServiceImpl implements PlanetaService {

	@Autowired
	private PlanetaRepository planetaRepository;

	
	@Override
	public Optional<Planeta> buscarPorId(Long id) {
		
		return planetaRepository.findById(id);
	}


	@Override
	public List<Planeta> buscarTodos() {
		
		return planetaRepository.findAll();
	}


	@Override
	public Optional<Planeta> buscarPorNome(String nome) {

		return Optional.ofNullable( planetaRepository.findByNome(nome) );
	}

	
	@Override
	public Planeta adicionar(Planeta planeta) {

		return planetaRepository.save(planeta);
	}
	

	@Override
	public void remover(Long id) {
		
		planetaRepository.deleteById(id);
		
	}

}
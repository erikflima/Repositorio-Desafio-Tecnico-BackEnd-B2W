package com.erik.desafiotecnico.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import com.erik.desafiotecnico.entities.Planeta;


public interface PlanetaRepository extends JpaRepository<Planeta, Long> {
	
	public Planeta findByNome(String nome);
}
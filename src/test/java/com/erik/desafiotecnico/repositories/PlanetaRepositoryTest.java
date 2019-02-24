package com.erik.desafiotecnico.repositories;
import static org.junit.Assert.assertEquals;
import java.util.List;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import com.erik.desafiotecnico.entities.Planeta;


@RunWith(SpringRunner.class) 
@SpringBootTest              
@ActiveProfiles("test")
public class PlanetaRepositoryTest {

	
	@Autowired
	private PlanetaRepository planetaRepository;	
	

	public Planeta gerarPlanetaParaTeste() {
		
		Planeta planeta = new Planeta();
		planeta.setId(1l);
		planeta.setNome("Alderaan");
		planeta.setClima("temperate");
		planeta.setTerreno("grasslands, mountains");
		planeta.setQuantidadeAparicoesEmFilmes(2);
		
		return planeta;
	}

	public Planeta gerarNovoPlanetaParaTeste() {
		
		Planeta planeta = new Planeta();
		planeta.setNome("Marte");
		planeta.setClima("Tropical");
		planeta.setTerreno("Montanhas");
		planeta.setQuantidadeAparicoesEmFilmes(10);
		
		return planeta;
	}
	
	
	@Test
	public void testBuscarPorIdOk() {
		
		Planeta planeta = gerarPlanetaParaTeste();
		
		Optional<Planeta> planetaRetornado =  this.planetaRepository.findById(1l);
		
		assertEquals(planeta.getId(), planetaRetornado.get().getId() );
		assertEquals(planeta.getNome(), planetaRetornado.get().getNome() );
		assertEquals(planeta.getClima(), planetaRetornado.get().getClima() );
		assertEquals(planeta.getTerreno(), planetaRetornado.get().getTerreno() );
		assertEquals(planeta.getQuantidadeAparicoesEmFilmes(), planetaRetornado.get().getQuantidadeAparicoesEmFilmes() );
	}
	
	
	@Test
	public void testBuscarTodosOk() {
				
		List<Planeta> planetas = planetaRepository.findAll();

		assertEquals( 26, planetas.size() );
	}	

	
	@Test
	public void testBuscarPorNomeOk() {
				
		Planeta planeta = gerarPlanetaParaTeste();
		
		Planeta planetaRecuperado = planetaRepository.findByNome(planeta.getNome());

		assertEquals( planeta.getId(), planetaRecuperado.getId() );
	}	
	
	
	@Test
	public void testRemoverOk() {

		planetaRepository.deleteById(1l);
		
		Optional<Planeta> planetaRecuperado = planetaRepository.findById(1L);
		
		
		assertEquals( false, planetaRecuperado.isPresent() );
	}	
	
	
	@Test
	public void testAdicionarOk() {
		
		Planeta planeta = gerarNovoPlanetaParaTeste();
		
		Planeta planetaSalvo = planetaRepository.save(planeta);
		
		assertEquals( planeta.getNome(), planetaSalvo.getNome() );
	}	
	
	
	
	
}
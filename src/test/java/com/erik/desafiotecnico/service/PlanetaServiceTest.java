package com.erik.desafiotecnico.service;
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
public class PlanetaServiceTest {

	@Autowired
	private PlanetaService planetaService;
	

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
		
		Optional<Planeta> planetaRetornado = planetaService.buscarPorId(1l);
		
		assertEquals( planeta.getId(), planetaRetornado.get().getId() );
		assertEquals( planeta.getNome(), planetaRetornado.get().getNome() );
		assertEquals( planeta.getClima(), planetaRetornado.get().getClima() );
		assertEquals( planeta.getTerreno(), planetaRetornado.get().getTerreno() );
		assertEquals( planeta.getQuantidadeAparicoesEmFilmes(), planetaRetornado.get().getQuantidadeAparicoesEmFilmes() );		
	}
	
	
	@Test
	public void testBuscarTodosOk() {
				
		List<Planeta> planetas = planetaService.buscarTodos();

		assertEquals( 26, planetas.size() );
	}	
	
	
	@Test
	public void testBuscarPorNomeOk() {
				
		Planeta planeta = gerarPlanetaParaTeste();
		
		Optional<Planeta> planetaRecuperado = planetaService.buscarPorNome( planeta.getNome() );

		assertEquals( planeta.getId(), planetaRecuperado.get().getId() );
	}	


	@Test
	public void testRemoverOk() {

		planetaService.remover(1L);
		
		Optional<Planeta> planetaRecuperado = planetaService.buscarPorId(1L);
		
		
		assertEquals( false, planetaRecuperado.isPresent() );
	}	


	@Test
	public void testAdicionarOk() {
		
		Planeta planeta = gerarNovoPlanetaParaTeste();
		
		Planeta planetaSalvo = planetaService.adicionar(planeta);
		
		assertEquals( planeta.getNome(), planetaSalvo.getNome() );
	}

}
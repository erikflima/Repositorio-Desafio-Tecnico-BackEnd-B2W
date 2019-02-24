package com.erik.desafiotecnico.controllers;
import java.net.URI;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import com.erik.desafiotecnico.dto.DadosDaApiStarWarsDto;
import com.erik.desafiotecnico.dto.PlanetaDto;
import com.erik.desafiotecnico.entities.Planeta;
import com.erik.desafiotecnico.response.ResponsePadronizado;
import com.erik.desafiotecnico.service.PlanetaService;


@RestController
@RequestMapping("/api/planeta")  
public class PlanetaController {
           

	@Autowired
	private PlanetaService planetaService;
	
	
	@GetMapping(value = "/listarPorId/{id}") 
	public ResponseEntity< ResponsePadronizado<PlanetaDto> > listarPorId( @PathVariable("id") Long id) {
		
		ResponsePadronizado<PlanetaDto> responsePadronizado = new ResponsePadronizado<PlanetaDto>();
		
		
		Optional<Planeta> planeta = this.planetaService.buscarPorId(id);
		
		if ( !planeta.isPresent() ){
		
			//204-No Content
			return ResponseEntity.noContent().build();
		}

		
		responsePadronizado.setConteudoDoResponse( this.converterParaPlanetaDto( planeta.get() ) );
		
		return ResponseEntity.ok().body(responsePadronizado);
	}	


	@GetMapping(value = "/listarTodos") 
	public ResponseEntity< ResponsePadronizado<List<PlanetaDto> > >listarTodos() {
		
		ResponsePadronizado<List<PlanetaDto>> responsePadronizado = new ResponsePadronizado<List<PlanetaDto>>();
		
		
		List<Planeta> planetas = this.planetaService.buscarTodos();

		if ( planetas.isEmpty() ){
					
			//204-No Content
			return ResponseEntity.noContent().build();
		}

		
		responsePadronizado.setConteudoDoResponse( converteParaListaDePlanetaDto(planetas) );
		
		return ResponseEntity.ok().body(responsePadronizado);
	}		

	
	@GetMapping(value = "/listarPorNome/{nome}") 
	public ResponseEntity< ResponsePadronizado<PlanetaDto> > listarPorNome( @PathVariable("nome") String nome) {
		
		ResponsePadronizado<PlanetaDto> responsePadronizado = new ResponsePadronizado<PlanetaDto>();
		
		
		Optional<Planeta> planeta = this.planetaService.buscarPorNome(nome);
		
		if ( !planeta.isPresent() ){
		
			//204-No Content
			return ResponseEntity.noContent().build();
		}

		
		responsePadronizado.setConteudoDoResponse( this.converterParaPlanetaDto( planeta.get() ) );
		
		return ResponseEntity.ok().body(responsePadronizado);
	}	
	
	
	@GetMapping(value = "/listarTodosApiStarWars/{numeroDaPagina}") 
	public ResponseEntity< ResponsePadronizado<DadosDaApiStarWarsDto> >listarTodosApiStarWars( @PathVariable("numeroDaPagina") int numeroDaPagina) {
		
		ResponsePadronizado<DadosDaApiStarWarsDto> responsePadronizado = new ResponsePadronizado<DadosDaApiStarWarsDto>();
		
		
		if(numeroDaPagina <= 0) {
			
			responsePadronizado.getErrors().add("Número da pagina deve ser maior que zero.");
			
			//+500
			return ResponseEntity.badRequest().body(responsePadronizado);	
		}
		
		
		//Preparando a uri da api "SWAPI The Star Wars API".
		UriComponents uri = UriComponentsBuilder.newInstance()
                                                .scheme("https")
                                                .host("swapi.co")
                                                .path("api/planets")
                                                .queryParam("page", numeroDaPagina)
                                                .build();
		
		//Preparando o header da requisicao.
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "SWAPI-Java-Client/1.0");
        
        
        HttpEntity<String> entity = new HttpEntity<String>("parametros", headers);		
		
        
		RestTemplate restTemplate = new RestTemplate();
	
		
		//Realizando uma request para api "SWAPI The Star Wars API".
		ResponseEntity<DadosDaApiStarWarsDto> resposta = restTemplate.exchange( uri.toString(), HttpMethod.GET, entity, DadosDaApiStarWarsDto.class );
		
		
		
		if ( resposta == null ){
			
			responsePadronizado.getErrors().add("Não possível consumir o serviço da api do endereço: " +uri.toString() );
			
			//+500
			return ResponseEntity.badRequest().body(responsePadronizado);
		}

		
		//Extraindo a lista de objetos.
		DadosDaApiStarWarsDto listaDadosDaApiStarWarsDto = resposta.getBody();
		
		
		//Etapa para informar a uri da pagina anterior para o cliente que fez a requisicao.
		if( listaDadosDaApiStarWarsDto.getUriParaPaginaAnterior() != null) {
			
			//Construindo a uri para realizar o "GET" do planeta salvo.
			URI uriPaginaAnterior = ServletUriComponentsBuilder.fromCurrentContextPath()
					                                           .path("/api/planeta/listarTodosApiStarWars/{numeroDaPagina}")
				                                               .buildAndExpand( numeroDaPagina - 1 )
				                                               .toUri();
			
			listaDadosDaApiStarWarsDto.setUriParaPaginaAnterior( uriPaginaAnterior.toString() );
		}
		
		//Etapa para informar a uri da pagina anterior para o cliente que fez a requisicao.
		if( listaDadosDaApiStarWarsDto.getUriParaProximaPagina() != null ){
			
			//Construindo a uri para realizar o "GET" do planeta salvo.
			URI uriProximaPagina = ServletUriComponentsBuilder.fromCurrentContextPath()
											                  .path("/api/planeta/listarTodosApiStarWars/{numeroDaPagina}")
											                  .buildAndExpand( numeroDaPagina + 1 )
											                  .toUri();
			
			listaDadosDaApiStarWarsDto.setUriParaProximaPagina( uriProximaPagina.toString() );
		}
		

		responsePadronizado.setConteudoDoResponse(listaDadosDaApiStarWarsDto);
		
		return ResponseEntity.ok().body(responsePadronizado);
	}
	
	
	@PostMapping(value = "/adicionar") 
	public ResponseEntity< ResponsePadronizado<PlanetaDto> > adicionar( @Valid @RequestBody PlanetaDto          planetaDto,
			                                                                                BindingResult       resultadoDaValidacao,
			                                                                                HttpServletResponse response             ) throws NoSuchAlgorithmException {	
		
		ResponsePadronizado<PlanetaDto> responsePadronizado = new ResponsePadronizado<PlanetaDto>();		
		
		
		verificaSePlanetaJaExisteNoBanco( planetaDto, resultadoDaValidacao );
		
		
		Planeta planeta = this.converterParaPlaneta( planetaDto );
		
		
		if ( resultadoDaValidacao.hasErrors() ) {
			
			List<ObjectError> listaDeErros = resultadoDaValidacao.getAllErrors();
			
			for( ObjectError auxiliar : listaDeErros  ){
				
				//Coleto a mensagem de erro da posicao atual.
				String mensagemDeErroExtraida = auxiliar.getDefaultMessage();
						
				responsePadronizado.getErrors().add(mensagemDeErroExtraida);
			}	
			
			return ResponseEntity.badRequest().body(responsePadronizado);
		}
	
		
		//Adiciono o planeta no banco de dados.
		Planeta planetaAdicionado = planetaService.adicionar(planeta);
		
		
		//Construindo a uri para realizar o "GET" do planeta salvo.
		URI uriParaGetDoPlaneta = ServletUriComponentsBuilder.fromCurrentRequestUri()
				                                             .path("/listarPorId/{id}")
			                                                 .buildAndExpand( planetaAdicionado.getId() )
			                                                 .toUri();	

		//Inserindo a uri no "header" da resposta da requisicao.
		response.setHeader( "Location", uriParaGetDoPlaneta.toASCIIString() );
		
		
		responsePadronizado.setConteudoDoResponse( this.converterParaPlanetaDto(planetaAdicionado) );
		
		
		//Status "201-Created".
		return ResponseEntity.created( uriParaGetDoPlaneta ).body( responsePadronizado );
	
	}
	
	
	
	
	@DeleteMapping(value = "/remover/{id}")
	public ResponseEntity< ResponsePadronizado<String> > remover( @PathVariable("id") Long id) {	
		
		//Preparando o objeto de resposta padronizada que criei.
		ResponsePadronizado<String> responsePadronizado = new ResponsePadronizado<String>();
		
		
		Optional<Planeta> planeta = this.planetaService.buscarPorId(id);

		
		if ( !planeta.isPresent() ){
	
			
			responsePadronizado.getErrors().add("Erro ao remover planeta. Não foi encontrado um planeta com o id " + id);
			
			return ResponseEntity.badRequest().body( responsePadronizado );
		}

		
		//Remove o planeta do banco.
		this.planetaService.remover(id);
		
		responsePadronizado.setConteudoDoResponse("Remoção de planeta realizada com sucesso");
		
		return ResponseEntity.ok().body( responsePadronizado );
		
	}
	
	
	
	
	/* Verifica se um planeta ja esta no banco de dados.
	 * 
	 * @param planetaDto, BindingResult resultadoDaValidacao
	 * @return void
	 */
	private void verificaSePlanetaJaExisteNoBanco( PlanetaDto planetaDto, BindingResult resultadoDaValidacao ) {
		
		planetaService.buscarPorNome( planetaDto.getNome() )
				.ifPresent( planetaEncontrado -> resultadoDaValidacao.addError( new ObjectError("planeta", "Planeta já existente no banco de dados.") ) );
	}	
	

	/* Converte uma Planeta para seu respectivo DTO.
	 * 
	 * @param Planeta
	 * @return PlanetaDto
	 */
	private PlanetaDto converterParaPlanetaDto( Planeta planeta ){
		
		PlanetaDto planetaDto = new PlanetaDto();
		planetaDto.setId(planeta.getId());
		planetaDto.setNome(planeta.getNome());
		planetaDto.setClima(planeta.getClima());
		planetaDto.setTerreno(planeta.getTerreno());
		planetaDto.setQtdFilmes(planeta.getQuantidadeAparicoesEmFilmes());
		
		return planetaDto;
	}
	
	
	/* Converte uma PlanetaDto para um Planeta.
	 * 
	 * @param PlanetaDto
	 * @return Planeta
	 */
	private Planeta converterParaPlaneta( PlanetaDto planetadto ){
		
		Planeta planeta = new Planeta();
		planeta.setNome( planetadto.getNome() );
		planeta.setClima( planetadto.getClima() );
		planeta.setTerreno( planetadto.getTerreno() );
		planeta.setQuantidadeAparicoesEmFilmes( planetadto.getQtdFilmes());
		
		return planeta;
	}
	
	
	/* Converte uma lista de "Planeta" em uma lista de "PlanetaDto".
	 * 
	 * @param List<Planeta>
	 * @return List<PlanetaDto>
	 */	
	private List<PlanetaDto> converteParaListaDePlanetaDto(List<Planeta> planetas) {
		
		
		List<PlanetaDto> listaPlanetasDto = new ArrayList<PlanetaDto>();
		
		
		for (Planeta aux : planetas) {
		
			PlanetaDto planetaDto = converterParaPlanetaDto(aux);
			
			listaPlanetasDto.add(planetaDto);
			
		}
		
		return listaPlanetasDto;
	}
		
}
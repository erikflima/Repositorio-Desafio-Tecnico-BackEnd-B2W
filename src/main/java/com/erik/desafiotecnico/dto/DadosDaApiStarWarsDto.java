package com.erik.desafiotecnico.dto;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DadosDaApiStarWarsDto {

	@JsonProperty("count")
	private String quantidadeDePlanetas;
	
	@JsonProperty("next")
	private String uriParaProximaPagina;
	
	@JsonProperty("previous")
	private String uriParaPaginaAnterior;
	
	@JsonProperty("results")
	private List<PlanetaDtoApiStarWars> listaDePlanetas;
	
	
	DadosDaApiStarWarsDto(){
	}



	//-------------------------Getters and Setters----------------------//
	
	public String getQuantidadeDePlanetas() {
		return quantidadeDePlanetas;
	}


	public void setQuantidadeDePlanetas(String quantidadeDePlanetas) {
		this.quantidadeDePlanetas = quantidadeDePlanetas;
	}
	
	


	public String getUriParaProximaPagina() {
		return uriParaProximaPagina;
	}


	public void setUriParaProximaPagina(String uriParaProximaPagina) {
		this.uriParaProximaPagina = uriParaProximaPagina;
	}
	
	


	public List<PlanetaDtoApiStarWars> getListaDePlanetas() {
		return listaDePlanetas;
	}


	public void setListaDePlanetas(List<PlanetaDtoApiStarWars> listaDePlanetas) {
		this.listaDePlanetas = listaDePlanetas;
	}


	public String getUriParaPaginaAnterior() {
		return uriParaPaginaAnterior;
	}

	public void setUriParaPaginaAnterior(String uriParaPaginaAnterior) {
		this.uriParaPaginaAnterior = uriParaPaginaAnterior;
	}
		
}
package com.erik.desafiotecnico.dto;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;


public class PlanetaDtoApiStarWars {
		
	@JsonProperty("name")
    private String nome;

	@JsonProperty("climate")
    private String clima;
    
	@JsonProperty("terrain")
    private String terreno;
        
    @JsonProperty("films")
    private List<String> listaDeFilmes;	
	
    
	public PlanetaDtoApiStarWars() {
	}

	//-------------------------Getters and Setters----------------------//
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getClima() {
		return clima;
	}

	public void setClima(String clima) {
		this.clima = clima;
	}


	public String getTerreno() {
		return terreno;
	}

	public void setTerreno(String terreno) {
		this.terreno = terreno;
	}

	
	public List<String> getListaDeFilmes() {
		return listaDeFilmes;
	}

	public void setListaDeFilmes(List<String> listaDeFilmes) {
		this.listaDeFilmes = listaDeFilmes;
	}
	
}
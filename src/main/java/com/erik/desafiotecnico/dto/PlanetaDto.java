package com.erik.desafiotecnico.dto;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;


public class PlanetaDto {

	private Long id;	
	
	@NotEmpty(message = "O campo `nome` precisa ser obrigatoriamente preenchido.")
    private String nome;

	@NotEmpty(message = "O campo `clima` precisa ser obrigatoriamente preenchido.")
    private String clima;
    
	@NotEmpty(message = "O campo `terreno` precisa ser obrigatoriamente preenchido.")
    private String terreno;
	
	@Digits(fraction = 0, integer = 10, message ="O campo `quantidadeAparicoesEmFilmes` precisa ser obrigatoriamente preenchido com um valor do tipo 'inteiro' e com no máximo '10' posições.")
	private int qtdFilmes;
	
	
	public PlanetaDto() {
	}

	//-------------------------Getters and Setters----------------------//
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


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


	public int getQtdFilmes() {
		return qtdFilmes;
	}

	public void setQtdFilmes(int quantidadeAparicoesEmFilmes) {
		this.qtdFilmes = quantidadeAparicoesEmFilmes;
	}
	
}
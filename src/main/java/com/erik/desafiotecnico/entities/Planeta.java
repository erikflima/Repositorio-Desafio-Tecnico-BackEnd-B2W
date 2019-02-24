package com.erik.desafiotecnico.entities;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity                                        
@Table(name = "planeta")                       
public class Planeta implements Serializable {

	
	private static final long serialVersionUID = -3598596812984167692L; 
	
	
	@Id                                              
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;	
	
	@Column(name = "nome", nullable = false)
    private String nome;
	
	@Column(name = "clima", nullable = false)
    private String clima;
    
	@Column(name = "terreno", nullable = false)
    private String terreno;
    
	@Column(name = "quantidadeAparicoesEmFilmes", nullable = false)
    private int quantidadeAparicoesEmFilmes=0;
    
	
	public Planeta() {
	}

	
	//-------Getters and Setters-------//
	
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


	public int getQuantidadeAparicoesEmFilmes() {
		return quantidadeAparicoesEmFilmes;
	}

	public void setQuantidadeAparicoesEmFilmes(int quantidadeAparicoesEmFilmes) {
		this.quantidadeAparicoesEmFilmes = quantidadeAparicoesEmFilmes;
	}
	
}
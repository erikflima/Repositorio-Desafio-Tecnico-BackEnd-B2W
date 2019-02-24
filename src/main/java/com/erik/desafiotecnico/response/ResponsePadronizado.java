package com.erik.desafiotecnico.response;
import java.util.ArrayList;
import java.util.List;


public class ResponsePadronizado<ClasseRecebida> {

	private ClasseRecebida conteudoDoResponse;
	private List<String>   errors;

	
	public ResponsePadronizado(){
	}

	
	//-------------------------Getters and Setters----------------------//	

	public ClasseRecebida getConteudoDoResponse() {
		
		return conteudoDoResponse;
	}

	public void setConteudoDoResponse(ClasseRecebida conteudoDoResponse) {
		
		this.conteudoDoResponse = conteudoDoResponse;
	}
	
	
	public List<String> getErrors() {
		
		if (this.errors == null) {
			
			this.errors = new ArrayList<String>();
		}
		
		return errors;
	}

	public void setErrors( List<String> errors ) {
		
		this.errors = errors;
	}

}
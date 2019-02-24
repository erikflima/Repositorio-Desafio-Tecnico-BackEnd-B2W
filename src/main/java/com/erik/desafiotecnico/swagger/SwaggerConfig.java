package com.erik.desafiotecnico.swagger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration                         
@EnableSwagger2                        
public class SwaggerConfig {

	
	@Bean                 
	public Docket api() { 

		Docket docketASerRetornado = new Docket( DocumentationType.SWAGGER_2 );
		
		
		return docketASerRetornado.select()                                  
				                  .apis(RequestHandlerSelectors.basePackage("com.erik.desafiotecnico.controllers")) 
				                  .paths(PathSelectors.any()).build()
				                  .apiInfo( apiInfo() );
	}

	
	private ApiInfo apiInfo() {
		
		
		//Definicao do texto que vai aparecer na interface de documentacao visual do Swagger.
	    String  title             = "Documentação com Swagger API - Projeto Desafio-Tecnico-BackEnd-B2W";
	    String  description       = "Documentação do Erik feita com Swagger do projeto Desafio-Tecnico-BackEnd-B2W\n - Lista de endpoints da aplicação com detalhamento";
        String  version           = "1.0";
        String  termsOfServiceUrl = "https://www.linkedin.com/in/eriklima";
        String  license           = "Licensa";
        String  licenseUrl        = "https://www.linkedin.com/in/eriklima";
        Contact contact           = new Contact("Erik Lima", "https://www.linkedin.com/in/eriklima", "erik.f.alves.lima@gmail.com");

        
		ApiInfo apiInfo = new ApiInfo( title, description, version, termsOfServiceUrl, contact, license, licenseUrl );

		return apiInfo;		
		
	}

}
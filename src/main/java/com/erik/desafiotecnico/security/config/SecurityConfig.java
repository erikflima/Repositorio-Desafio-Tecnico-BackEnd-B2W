package com.erik.desafiotecnico.security.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	
	@Override 
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.inMemoryAuthentication().withUser("admin")
		                             .password("{noop}admin")		                             
		                             .roles("ADMIN");
	}
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		
		http.authorizeRequests()
	                            .antMatchers("/h2-console/**")                                      .permitAll()
								.antMatchers("/api/planeta/listarPorId/{id}")                       .permitAll() 
								.antMatchers("/api/planeta//listarPorId/{id}")                      .permitAll() 
								.antMatchers("/api/planeta/listarTodos")                            .permitAll() 
								.antMatchers("/api/planeta/listarPorNome/{nome}")                   .permitAll() 
								.antMatchers("/api/planeta/listarTodosApiStarWars/{numeroDaPagina}").permitAll() 
								.antMatchers("/api/planeta/adicionar")                              .hasRole("ADMIN")
								.antMatchers("api/planeta/remover/{id}")                            .hasRole("ADMIN")
								.antMatchers("/swagger-ui.html#")                                   .permitAll() 
								.anyRequest().authenticated()                                                       
								.and()
								.httpBasic()                                                                      
		                        .and()
		                        .sessionManagement().sessionCreationPolicy( SessionCreationPolicy.STATELESS )       
		                        .and()                                    
		                        .csrf().disable();                                                                 

								http.headers().cacheControl();                                                      
								http.headers().frameOptions().disable();                                          
	}	
	
}
/**
 * 
 */
package com.daniel.vertx.cnab.config;

import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.impl.VertxFactoryImpl;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Classe de configuração do container do Spring
 * @author daniel
 *
 */
@Configuration
@ComponentScan("com.daniel.vertx.cnab")
public class CNABConfig{
	
	  @Bean
	  public EventBus barramentoServicos() {
	    Vertx vertx = new VertxFactoryImpl().context().owner();
	    return vertx.eventBus();
	  }
	  
	  @Bean
	  public Router roteador() {
		Vertx vertx = new VertxFactoryImpl().context().owner();
		Router roteador = Router.router(vertx);
		roteador.route().handler(BodyHandler.create());//Necessario para habilitar a recuperação do corpo das requisições
		return roteador;
	  }

}

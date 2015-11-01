/**
 * 
 */
package com.daniel.vertx.cnab.routers;

import io.vertx.core.Handler;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Classe genérica para a geração das rotas
 * @author daniel
 *
 */
public abstract class AbstractGeradorDeRota {
	
	@Autowired
	protected Router roteador;
	
	@PostConstruct
	public abstract void postConstruct();
	
	public void gerarRota(String rota, Handler<RoutingContext> tratamento){
		roteador.route(rota).handler(tratamento);
	}
	
	public void gerarPost(String rota, Handler<RoutingContext> tratamento){
		roteador.post(rota).handler(tratamento);
	}

	

}

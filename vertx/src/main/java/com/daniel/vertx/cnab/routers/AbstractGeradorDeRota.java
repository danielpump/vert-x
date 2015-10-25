/**
 * 
 */
package com.daniel.vertx.cnab.routers;

import io.vertx.core.Handler;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

/**
 * Classe genérica para a geração das rotas
 * @author daniel
 *
 */
public abstract class AbstractGeradorDeRota {
	
	protected Router roteador;

	public AbstractGeradorDeRota(Router roteador) {
		super();
		this.roteador = roteador;		
	}
	
	public void gerarRota(String rota, Handler<RoutingContext> tratamento){
		roteador.route(rota).handler(tratamento);
	}
	
	public void gerarPost(String rota, Handler<RoutingContext> tratamento){
		roteador.post(rota).handler(tratamento);
	}

	

}

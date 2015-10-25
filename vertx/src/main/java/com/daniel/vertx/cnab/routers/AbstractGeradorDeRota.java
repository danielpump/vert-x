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
		gerarRota();
	}
	
	public void gerarRota(){
		roteador.route(getRota()).handler(getHandler());
	}

	protected abstract String getRota();
	
	protected abstract Handler<RoutingContext> getHandler();
	

}

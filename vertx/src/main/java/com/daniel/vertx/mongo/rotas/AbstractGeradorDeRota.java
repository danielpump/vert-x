/**
 * 
 */
package com.daniel.vertx.mongo.rotas;

import io.vertx.core.Handler;
import io.vertx.ext.mongo.MongoClient;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

/**
 * Classe genérica para a geração das rotas
 * @author daniel
 *
 */
public abstract class AbstractGeradorDeRota {
		
	protected Router roteador;	
	
	/**
	 * Método inicializador das rotas que serão definidas para o roteamento criado, deve chamar os métodos de rotas com os respectivos padrões
	 */
	public abstract void gerarRotas();
		
	/**
	 * Gera uma rota para o método get
	 * @param rota
	 * @param tratamento
	 */
	protected void gerarRota(String rota, Handler<RoutingContext> tratamento){
		roteador.route(rota).handler(tratamento);
	}
	
	/**
	 * Gera uma rota post
	 * @param rota
	 * @param tratamento
	 */
	protected void gerarPost(String rota, Handler<RoutingContext> tratamento){
		roteador.post(rota).handler(tratamento);
	}

	public void setRoteador(Router roteador) {
		this.roteador = roteador;
	}

}

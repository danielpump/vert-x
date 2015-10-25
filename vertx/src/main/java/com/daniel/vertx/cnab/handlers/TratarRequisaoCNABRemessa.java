/**
 * 
 */
package com.daniel.vertx.cnab.handlers;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.RoutingContext;

/**
 * Trata uma requisição para a pagina de geração do arquivo CNAB400
 * @author daniel
 *
 */
public class TratarRequisaoCNABRemessa implements Handler<RoutingContext>{
	
	public void handle(RoutingContext event) {
		HttpServerResponse response = event.response();
		response.end("Vamos gerar uma remessa!");	
	};

}

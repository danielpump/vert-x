/**
 * 
 */
package com.daniel.vertx.cnab.handlers.requisicoes;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.RoutingContext;

/**
 * Trata uma requisição para a pagina de geração do arquivo CNAB400
 * 
 * @author daniel
 *
 */
public class TratarRequisoesEstaticas extends HandlerPadrao {

	public Handler<RoutingContext> tratarRota() {
		return new Handler<RoutingContext>() {
			@Override
			public void handle(RoutingContext event) {
				event.response().sendFile('.' + (event.request().path()));
			}
		};
	};

}

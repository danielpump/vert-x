/**
 * 
 */
package com.daniel.vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpServerRequest;

/**
 * @author daniel
 *
 */
public class SimpleServer  extends AbstractVerticle{
	
	@Override
	public void start() throws Exception {

		vertx.createHttpServer().requestHandler(new Handler<HttpServerRequest>() {
			
			@Override
			public void handle(HttpServerRequest request) {
				String nome = request.getParam("nome");
				String mensagem = String.format("Vert-x iniciando com parametro: ", nome);
				request.response().setStatusCode(200).end(mensagem);
			}
		}).listen(8080);
		
	}

}

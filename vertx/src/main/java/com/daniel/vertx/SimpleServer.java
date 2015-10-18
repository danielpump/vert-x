/**
 * 
 */
package com.daniel.vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

/**
 * @author daniel
 *
 */
public class SimpleServer  extends AbstractVerticle{
	
	@Override
	public void start() throws Exception {
		
		HttpServer httpServer = vertx.createHttpServer();
		Router router = Router.router(vertx);
		
		pingHandler(router);		
		baseHander(router);
				
		httpServer.requestHandler(router::accept).listen(8080);
		
	}

	/**
	 * Trata requisiçoes na url /ping
	 * @param router
	 */
	private void pingHandler(Router router) {
		router.route("/ping").handler(new Handler<RoutingContext>() {
			public void handle(RoutingContext event) {
				HttpServerResponse response = event.response();
				response.end("pong");				
			};
		});
	}

	/**
	 * Trata requisições na URL /
	 * @param router
	 */
	private void baseHander(Router router) {
		router.route("/").handler(new Handler<RoutingContext>() {
			public void handle(RoutingContext event) {
				String nome = event.request().getParam("nome");
				String mensagem = String.format("Vert-x iniciando com parametro: %s", nome);
				HttpServerResponse response = event.response();
				response.setStatusCode(200).end(mensagem);				
			};
		});
	}

}

/**
 * 
 */
package com.daniel.vertx.cnab.verticles;

import com.daniel.vertx.cnab.routers.RotaRemessa;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;

/**
 * 
 * @author daniel
 *
 */
public class CNABServer  extends AbstractVerticle {
	
	@Override
	public void start() throws Exception {
		
		HttpServer servidor = vertx.createHttpServer();
		Router roteador = Router.router(vertx);
		
		new RotaRemessa(roteador);
		
		servidor.requestHandler(roteador::accept).listen(8080);
		
	}

}

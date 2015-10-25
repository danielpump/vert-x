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
public class TratarRequisoesCNABRemessa {
	
	private static final String DEFAULT_PAGE = "./resource/paginas/remessa.html";

	public static Handler<RoutingContext> tratarRota(){
		return new Handler<RoutingContext>() {
			@Override
			public void handle(RoutingContext context) {
				HttpServerResponse response = context.response();
				response.sendFile(DEFAULT_PAGE);				
			}
		};			
	};
	
	public static Handler<RoutingContext> tratarPost(){
		return new Handler<RoutingContext>() {
			@Override
			public void handle(RoutingContext context) {
				
				System.out.println(context.getBodyAsString());				
				context.response().sendFile(DEFAULT_PAGE);
			}
		};			
	};

}

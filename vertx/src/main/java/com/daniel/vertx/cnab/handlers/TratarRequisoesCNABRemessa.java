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
	
	public static Handler<RoutingContext> tratarRota(){
		return new Handler<RoutingContext>() {
			@Override
			public void handle(RoutingContext event) {
				HttpServerResponse response = event.response();
				response.sendFile("./resource/paginas/remessa.html");				
			}
		};			
	};
	
	public static Handler<RoutingContext> tratarPost(){
		return new Handler<RoutingContext>() {
			@Override
			public void handle(RoutingContext event) {
				System.out.println("Teste");
			}
		};			
	};

}

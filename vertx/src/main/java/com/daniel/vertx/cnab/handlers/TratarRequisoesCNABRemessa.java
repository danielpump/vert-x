/**
 * 
 */
package com.daniel.vertx.cnab.handlers;

import java.io.IOException;
import java.util.HashMap;

import com.daniel.vertx.cnab.entidades.Banco;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.Json;
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
				
				String corpoRequest = context.getBodyAsString();
				corpoRequest = tratarRequisicoesJQueryStringify(corpoRequest);
				
				try {
					HashMap mappedJson = new ObjectMapper().readValue(corpoRequest, HashMap.class);
					
				} catch (JsonParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JsonMappingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				context.response().sendFile(DEFAULT_PAGE);
			}

			/**
			 * Quando é utilizada a função do JQuery.stringify, ele adiciona as chaves no começo da lista e isso quebra a execução.
			 * @param corpoRequest
			 * @return
			 */
			private String tratarRequisicoesJQueryStringify(String corpoRequest) {
				if(corpoRequest.startsWith("[")){
					corpoRequest = corpoRequest.substring(1);
				}
				if(corpoRequest.endsWith("]")){
					corpoRequest = corpoRequest.substring(0, (corpoRequest.length()-1));
				}
				return corpoRequest;
			}
		};			
	};

}

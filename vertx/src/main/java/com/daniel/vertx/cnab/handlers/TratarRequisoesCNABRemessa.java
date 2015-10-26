/**
 * 
 */
package com.daniel.vertx.cnab.handlers;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.logging.JULLogDelegateFactory;
import io.vertx.core.logging.Logger;
import io.vertx.ext.web.RoutingContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Trata uma requisição para a pagina de geração do arquivo CNAB400
 * 
 * @author daniel
 *
 */
public class TratarRequisoesCNABRemessa extends HandlerPadrao {

	private static Logger logger = new Logger(new JULLogDelegateFactory().createDelegate("TratarRequisoesCNABRemessa"));

	private static final String DEFAULT_PAGE = "./resource/paginas/remessa.html";

	public Handler<RoutingContext> tratarRota() {
		return new Handler<RoutingContext>() {
			@Override
			public void handle(RoutingContext context) {
				HttpServerResponse response = context.response();
				response.sendFile(DEFAULT_PAGE);
			}
		};
	};

	public Handler<RoutingContext> tratarPost() {
		return new Handler<RoutingContext>() {
			@Override
			public void handle(RoutingContext context) {

				HashMap<String, String> mappedJson = converterBodyParaMapa(context);

				validarCamposDoMapa(mappedJson);

				context.response().sendFile(DEFAULT_PAGE);
			}
		};
	}
	
	@Override
	protected List<String> camposParaValidar() {
		return new ArrayList<String>(){
			{
				add("codigoBeneficiario");
				add("nomeBeneficiario");
				add("codigoAgencia");
				add("codigoBanco");
				add("nomeBanco");
			}
		};
	}

}

/**
 * 
 */
package com.daniel.vertx.cnab.handlers.requisicoes;

import io.vertx.core.Handler;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.logging.JULLogDelegateFactory;
import io.vertx.core.logging.Logger;
import io.vertx.ext.web.RoutingContext;

/**
 * Trata uma requisição para a pagina de geração do arquivo CNAB400
 * 
 * @author daniel
 *
 */
public class TratarRequisoesCNABRemessa extends HandlerPadrao {

	private static Logger logger = new Logger(new JULLogDelegateFactory().createDelegate("TratarRequisoesCNABRemessa"));

	private static final String DEFAULT_PAGE = "./resource/paginas/remessa.html";
	
	private EventBus barramentoDeServicos;
	
	/**
	 * Constroi o tratamento com o barramento de serviços do Vertx 
	 * @param barramentoDeServicos
	 */
	public TratarRequisoesCNABRemessa(EventBus barramentoDeServicos) {
		super();
		this.barramentoDeServicos = barramentoDeServicos;
	}

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
				barramentoDeServicos.send("gerarRemessa", context.getBodyAsJson());				
				context.response().sendFile(DEFAULT_PAGE);
			}
		};
	}


}

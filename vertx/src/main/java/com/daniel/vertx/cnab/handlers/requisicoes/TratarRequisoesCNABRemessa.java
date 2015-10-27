/**
 * 
 */
package com.daniel.vertx.cnab.handlers.requisicoes;

import io.vertx.core.Handler;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.JULLogDelegateFactory;
import io.vertx.core.logging.Logger;
import io.vertx.ext.web.RoutingContext;

import java.util.HashMap;
import java.util.function.Predicate;

import com.daniel.vertx.cnab.entidades.Banco;
import com.daniel.vertx.cnab.entidades.Beneficiario;
import com.daniel.vertx.cnab.remessa.VertxCNAB400Cabecalho;
import com.google.common.base.Strings;

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

				HashMap<String, Object> corpoRequisiscao = converterBodyParaMapa(context);			
				
				barramentoDeServicos.send("gerarRemessa", new JsonObject(corpoRequisiscao));
				
				context.response().sendFile(DEFAULT_PAGE);
			}
		};
	}


}
